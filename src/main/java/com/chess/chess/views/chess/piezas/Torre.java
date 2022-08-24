package com.chess.chess.views.chess.piezas;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
public class Torre extends Pieza{

    public Torre(String color) {
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
