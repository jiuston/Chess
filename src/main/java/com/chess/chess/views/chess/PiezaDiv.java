package com.chess.chess.views.chess;

import com.chess.chess.views.chess.piezas.*;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.dnd.DragSource;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.dom.ClassList;

public class PiezaDiv extends Div implements DragSource<PiezaDiv>, HasStyle {

    private Pieza pieza;

    public PiezaDiv(){
        setDraggable(true);
        addClassName("pieza");
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
            String posInicio=cuadroDiv.getClassNames().stream().sorted().filter(clase -> clase.length() == 2).findFirst().orElse(null);
            this.pieza.setPosicion(posInicio);
        }
        System.out.println(pieza.getNombre());

    }



}
