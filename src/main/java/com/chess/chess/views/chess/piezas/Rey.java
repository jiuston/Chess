package com.chess.chess.views.chess.piezas;

import lombok.Data;

import java.util.List;

@Data
public class Rey extends Pieza {

    private boolean hasAlreadyMoved;
    private boolean isInJaque;

    public Rey(String color) {
        this.setNombre(getClass().getSimpleName());
        this.setColor(color);
        this.hasAlreadyMoved = false;
        this.isInJaque = false;
    }

    @Override
    public boolean canMoveToThatPosition(String currentPos, String destPos) {
        return false;
    }

    @Override
    public List<String> getPosibleMovements(String currentPos, List<String> positionsOfFriendlyPieces, List<String> positionsOfEnemyPieces) {
        return null;
    }

    public Boolean canEnroque() {
        return !hasAlreadyMoved && !isInJaque;
    }

}
