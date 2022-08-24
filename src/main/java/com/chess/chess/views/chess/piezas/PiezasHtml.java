package com.chess.chess.views.chess.piezas;

public enum PiezasHtml {
    PEON("<i>&#9823;</i>"),
    TORRE("<i>&#9820;</i>"),
    CABALLO("<i>&#9822;</i>"),
    ALFIL("<i>&#9821;</i>"),
    REINA("<i>&#9819;</i>"),
    REY("<i>&#9818;</i>");

    private final String pieza;

    PiezasHtml(String pieza) { this.pieza = pieza; }

    public String getPieza() {
        return pieza; }
}