package com.chess.chess.views.chess.piezas;

import lombok.Data;

@Data
public class Peon extends Pieza{

    public Peon(String color, String posInicio){
        this.setColor(color);
        this.setPosicion(posInicio);
    }
}
