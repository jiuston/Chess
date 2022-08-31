package com.chess.chess.views.chess;

import com.chess.chess.views.chess.piezas.*;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.dnd.DragSource;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.dom.ClassList;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class PiezaDiv extends Div implements DragSource<PiezaDiv>, HasStyle {

   @Getter
   private Pieza pieza;

   @Getter @Setter
   private String currentPos;

   @Getter
   private List<Div> possibleMovements;

    public PiezaDiv(){
        possibleMovements = new ArrayList<>();
        setDraggable(true);
        addClassName("pieza");
        addDragStartListener(piezaDivDragStartEvent -> checkPiecePossibleMovements((TableroDiv) getParent().get().getParent().get())); //El primer padre es el cuadrado, el segundo es el tablero
        addDragEndListener(piezaDivDragEndEvent -> removeClassNames((TableroDiv) getParent().get().getParent().get()));
    }

    private void removeClassNames(TableroDiv tableroDiv) {
        tableroDiv.getCuadros().forEach(div -> div.removeClassNames("puedeComerse", "puedeMoverse"));
        setPositionToPieza();
    }

    private void setPositionToPieza() {
        Div cuadroDiv =  this.getParent().isPresent() ? (Div)this.getParent().get() : null;
        if (cuadroDiv!=null){
            currentPos=cuadroDiv.getId().orElse(null);
            this.pieza.setPosicion(currentPos);
        }
    }

    private void checkPiecePossibleMovements(TableroDiv tableroDiv) {
        currentPos = pieza.getPosicion();
        possibleMovements = new ArrayList<>();
        pieza.checkPossibleMovements(currentPos, tableroDiv.getCuadros(), possibleMovements);
        possibleMovements.removeIf(div -> div.getId().get().equals(currentPos));
        possibleMovements.forEach(div -> div.addClassName("puedeMoverse"));
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
        setPositionToPieza();
    }



}
