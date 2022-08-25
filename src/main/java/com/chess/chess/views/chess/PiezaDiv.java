package com.chess.chess.views.chess;

import com.chess.chess.views.chess.piezas.*;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.dnd.DragSource;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.dom.ClassList;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PiezaDiv extends Div implements DragSource<PiezaDiv>, HasStyle {

    private Pieza pieza;

    public PiezaDiv(){
        setDraggable(true);
        addClassName("pieza");
        addDragStartListener(piezaDivDragStartEvent -> checkPiecePossibleMovements(pieza, (TableroDiv) getParent().get().getParent().get())); //El primer padre es el cuadrado, el segundo es el tablero
    }

    private void checkPiecePossibleMovements(Pieza pieza, TableroDiv tableroDiv) {
        String currentPos = pieza.getPosicion();
        List<Div> possibleMovements = new ArrayList<>();
        pieza.checkPossibleMovements(currentPos, tableroDiv.getCuadros(), possibleMovements);
        possibleMovements.removeIf(div -> div.getId().get().equals(currentPos));
        possibleMovements.forEach(div -> System.out.println(div.getId().get()));
    }


    public void setText(PiezasHtml piezasHtml){
        super.setText(new Html(piezasHtml.getPieza()).getInnerHtml());
        ClassList clases = this.getClassNames();
        String color = clases.contains("piezaNegra") ? "negro" : clases.contains("piezaBlanca") ? "blanco" : "";
        switch (piezasHtml){
            case REY -> this.pieza = new Rey(color);
            case PEON -> this.pieza = new Peon(color);
            case ALFIL -> this.pieza = new Alfil(color);
            case REINA -> this.pieza = new Reina(color);
            case TORRE -> this.pieza = new Torre(color);
            case CABALLO -> this.pieza = new Caballo(color);
        }
        Div cuadroDiv =  this.getParent().isPresent() ? (Div)this.getParent().get() : null;
        if (cuadroDiv!=null){
            String posInicio=cuadroDiv.getId().orElse(null);
            this.pieza.setPosicion(posInicio);
        }
    }



}
