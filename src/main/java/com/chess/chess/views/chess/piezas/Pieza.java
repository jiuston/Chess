package com.chess.chess.views.chess.piezas;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public abstract class Pieza {

    private String color;
    private String nombre;
    private String posicion;

    public boolean canEatPiece(Pieza piezaToEat) {
        return !piezaToEat.getColor().equals(this.getColor());
    }
    public abstract boolean canMoveToThatPosition(String currentPos, String destPos);
    public abstract List<String> getPosibleMovements(String currentPos, List<String> positionsOfFriendlyPieces, List<String> positionsOfEnemyPieces);

}
