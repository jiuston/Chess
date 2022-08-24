package com.chess.chess.views.chess.piezas;

import lombok.Data;

import java.util.List;

@Data
public class Torre extends Pieza{

    private boolean hasAlreadyMoved;

    public Torre(String color) {
        this.setNombre(getClass().getSimpleName());
        this.setColor(color);
        this.hasAlreadyMoved = false;
    }

    @Override
    public boolean canMoveToThatPosition(String currentPos, String destPos) {
        return false;
    }

    @Override
    public List<String> getPosibleMovements(String currentPos, List<String> positionsOfFriendlyPieces, List<String> positionsOfEnemyPieces) {
        return null;
    }

    public Boolean canEnroque(){
        return !hasAlreadyMoved;
    }
}
