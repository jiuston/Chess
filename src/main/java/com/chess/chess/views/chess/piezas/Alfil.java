package com.chess.chess.views.chess.piezas;

import lombok.Data;

@Data
public class Alfil extends Pieza{

    public Alfil(String color, String posInicio){
        this.setColor(color);
        this.setPosicion(posInicio);
    }

}
