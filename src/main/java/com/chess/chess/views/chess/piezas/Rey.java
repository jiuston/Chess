package com.chess.chess.views.chess.piezas;

import java.util.List;

public class Rey extends Pieza{

    public Rey(String color, String posInicio){
        this.setColor(color);
        this.setPosicion(posInicio);
    }

    @Override
    public boolean canMoveToThatPosition(String currentPos, String destPos) {
        return false;
    }

    @Override
    public List<String> getPosibleMovements(String currentPos, List<String> positionsOfFriendlyPieces, List<String> positionsOfEnemyPieces) {
        return null;
    }
}
