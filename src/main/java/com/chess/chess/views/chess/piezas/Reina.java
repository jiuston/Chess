package com.chess.chess.views.chess.piezas;

import lombok.Data;

@Data
public class Reina extends Pieza{

    public Reina(String color, String posInicio){
        this.setColor(color);
        this.setPosicion(posInicio);
    }
}
