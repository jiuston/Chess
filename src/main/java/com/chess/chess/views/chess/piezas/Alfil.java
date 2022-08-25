package com.chess.chess.views.chess.piezas;

import com.vaadin.flow.component.html.Div;
import lombok.Data;

import java.util.List;

@Data
public class Alfil extends Pieza{

    public Alfil(String color) {
        this.setNombre(getClass().getSimpleName());
        this.setColor(color);
    }
    @Override
    public boolean canMoveToThatPosition(String currentPos, String destPos) {
        return false;
    }

    @Override
    public List<String> getPosibleMovements(String currentPos, List<String> positionsOfFriendlyPieces, List<String> positionsOfEnemyPieces) {
        return null;
    }

    @Override
    public void checkPossibleMovements(String currentPos, List<Div> cuadros, List<Div> possibleMovements) {

    }

}
