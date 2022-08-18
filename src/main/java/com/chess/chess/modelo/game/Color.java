package com.chess.chess.modelo.game;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Color {
    @JsonProperty("Blancas")
    BLANCAS("Blancas"),

    @JsonProperty("Negras")
    NEGRAS("Negras");


    private final String color;

    Color(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
