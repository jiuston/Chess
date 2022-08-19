package com.chess.chess.views.chess.piezas;

import lombok.Data;

@Data
public class Caballo extends Pieza{

    public Caballo(String color, String posInicio){
        this.setColor(color);
        this.setPosicion(posInicio);
    }

}
