package com.chess.chess.views.chess.piezas;

import lombok.Data;

@Data
public class Torre extends Pieza{

    public Torre(String color, String posInicio){
        this.setColor(color);
        this.setPosicion(posInicio);
    }
}
