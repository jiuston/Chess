package com.chess.chess.modelo.game;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Estado {
    @JsonProperty("Creado")
    CREADO("Creado"),

    @JsonProperty("En juego")
    ENJUEGO("En juego"),

    @JsonProperty("Terminado")
    TERMINADO("Terminado"),

    @JsonProperty("Abandonado")
    ABANDONADO("Abandonado");

    private final String estado;

    Estado(String estado) {
        this.estado = estado;
    }

    public String getEstado(){
        return this.estado;
    }
    }
