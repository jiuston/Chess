package com.chess.chess.views.chess.piezas;

import lombok.Data;

import java.util.List;

@Data
public class Torre extends Pieza{

    public Torre(String color, String posInicio){
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
