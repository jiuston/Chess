package com.chess.chess.modelo.player;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Estado {
    @JsonProperty("Jugando")
    INGAME("Jugando"),

    @JsonProperty("Conectado")
    ONLINE("Conectado"),

    @JsonProperty("Desconectado")
    OFFLINE("Desconectado"),

    @JsonProperty("Ausente")
    AFK("Ausente");

    private final String estado;

    Estado(String estado) {
        this.estado = estado;
    }

    public String getEstado(){
        return this.estado;
    }
    }
