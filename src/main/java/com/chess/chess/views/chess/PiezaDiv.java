package com.chess.chess.views.chess;

import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.dnd.DragSource;
import com.vaadin.flow.component.html.Div;

public class PiezaDiv extends Div implements DragSource<PiezaDiv>, HasStyle {
    public PiezaDiv(){
        setDraggable(true);
        addClassName("pieza");
    }

}
