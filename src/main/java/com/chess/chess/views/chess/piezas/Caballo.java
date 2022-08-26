package com.chess.chess.views.chess.piezas;

import com.vaadin.flow.component.html.Div;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class Caballo extends Pieza{

    public Caballo(String color) {
        this.setNombre(getClass().getSimpleName());
        this.setColor(color);
    }

    @Override
    public boolean canMoveToThatPosition(String currentPos, String destPos) {
        return false;
    }

    @Override
    protected void getPossibleDivsToEnd(List<Div> cuadrosTablero, int posX, int posY, List<Div> cuadrosPosibles) {

    }


    @Override
    public void checkPossibleMovements(String currentPos, List<Div> cuadros, Set<Div> possibleMovements) {

    }

}
