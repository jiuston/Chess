package com.chess.chess.views.chess.piezas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pieza {

    private String color;
    private String nombre;
    private String posicion;

    public boolean canEatPiece(Pieza piezaToEat) {
        return !piezaToEat.getColor().equals(this.getColor());
    }

}
