package com.chess.chess.views.chess.piezas;

import lombok.Data;

import java.util.List;

@Data
public class Reina extends Pieza{

    public Reina(String color) {
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
}
