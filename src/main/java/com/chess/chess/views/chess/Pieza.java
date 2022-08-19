package com.chess.chess.views.chess;

import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.dnd.DragSource;
import com.vaadin.flow.component.html.Div;

public class Pieza extends Div implements DragSource<Pieza>, HasStyle {
    public Pieza(){
        setDraggable(true);
        addClassName("pieza");
    }

}
