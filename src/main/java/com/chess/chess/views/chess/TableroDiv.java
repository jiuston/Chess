package com.chess.chess.views.chess;

import com.chess.chess.views.chess.piezas.PiezasHtml;
import com.vaadin.flow.component.html.Div;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class TableroDiv extends Div {

    @Getter
    private List<Div> cuadros;

    @Getter
    private List<PiezaDiv> piezasBlancas;

    @Getter
    private List<PiezaDiv> piezasNegras;

    public TableroDiv() {
        piezasNegras = new ArrayList<>();
        piezasBlancas = new ArrayList<>();
        cuadros = new ArrayList<>();
        addClassName("tablero");
        generarCuadrosPiezas();
        darTurnoBlancas();
    }

    public void darTurnoBlancas() {
        piezasBlancas.forEach(piezaDiv -> {
            piezaDiv.setDraggable(true);
            piezaDiv.getPieza().setCanMove(true);
        });
        piezasNegras.forEach(piezaDiv -> {
            piezaDiv.setDraggable(false);
            piezaDiv.getPieza().setCanMove(false);
        });
    }

    public void darTurnoNegras() {
        piezasBlancas.forEach(piezaDiv -> {
            piezaDiv.setDraggable(false);
            piezaDiv.getPieza().setCanMove(false);
        });
        piezasNegras.forEach(piezaDiv -> {
            piezaDiv.setDraggable(true);
            piezaDiv.getPieza().setCanMove(true);
        });
    }

    private void generarCuadrosPiezas() {
        //flag determina cuando va un cuadrado negro y cuando uno blanco
        boolean flag = true;

        //posY es laposición en numero del tablero
        int posY = 8;

        for (int x = 0; x < 4; x++, posY--) {
            //J es la letra del tablero en código ASCII
            int j = 65;

            for (int i = 0; i < 8; i++, j++) {
                if (flag) {
                    Div divCuadroBlanco = new Div();
                    divCuadroBlanco.addClassNames("cuadro", "cuadroBlanco");
                    setIdAndPieces(posY, x, j, i, divCuadroBlanco);
                } else {
                    Div divCuadroNegro = new Div();
                    divCuadroNegro.addClassNames("cuadro", "cuadroNegro");
                    setIdAndPieces(posY, x, j, i, divCuadroNegro);
                }
                flag = !flag;
            }
            posY--;
            j = 65;
            for (int i = 8; i < 16; i++, j++) {
                if (flag) {
                    Div divCuadroNegro = new Div();
                    divCuadroNegro.addClassNames("cuadro", "cuadroNegro");
                    setIdAndPieces(posY, x, j, i, divCuadroNegro);
                } else {
                    Div divCuadroBlanco = new Div();
                    divCuadroBlanco.addClassNames("cuadro", "cuadroBlanco");
                    setIdAndPieces(posY, x, j, i, divCuadroBlanco);
                }
                flag = !flag;
            }
        }
    }

    private void setIdAndPieces(int posY, int x, int j, int i, Div cuadro) {
        cuadro.setId(Character.toString(j) + posY);
        add(cuadro);
        cuadros.add(cuadro);
        addPieces(i, cuadro, x);
    }

    private void addPieces(int i, Div divCuadro, int x) {
        PiezaDiv piezaDiv = new PiezaDiv();
        if (x == 0) { //Si X es 0 está pintando las dos primeras filas del tablero que corresponden a las piezas negras
            piezaDiv.addClassNames("piezaNegra");
            divCuadro.add(piezaDiv);
            switch (i) {
                case 0, 7 -> piezaDiv.setText(PiezasHtml.TORRE);
                case 1, 6 -> piezaDiv.setText(PiezasHtml.CABALLO);
                case 2, 5 -> piezaDiv.setText(PiezasHtml.ALFIL);
                case 3 -> piezaDiv.setText(PiezasHtml.REINA);
                case 4 -> piezaDiv.setText(PiezasHtml.REY);
                default -> piezaDiv.setText(PiezasHtml.PEON);
            }
            piezasNegras.add(piezaDiv);
            piezaDiv.getPieza().setCanMove(false);
        } else if (x==3){ //Si X es 0 está pintando las dos primeras filas del tablero que corresponden a las piezas blancas
            piezaDiv.addClassNames("piezaBlanca");
            divCuadro.add(piezaDiv);
            switch (i) {
                case 8, 15 -> piezaDiv.setText(PiezasHtml.TORRE);
                case 9, 14 -> piezaDiv.setText(PiezasHtml.CABALLO);
                case 10, 13 -> piezaDiv.setText(PiezasHtml.ALFIL);
                case 11 -> piezaDiv.setText(PiezasHtml.REINA);
                case 12 -> piezaDiv.setText(PiezasHtml.REY);
                default -> piezaDiv.setText(PiezasHtml.PEON);
            }
            piezasBlancas.add(piezaDiv);
            piezaDiv.getPieza().setCanMove(true);
        }
    }

    public void cambiarTurno(String color) {
        if (color.equals("blanco")){
            darTurnoNegras();
        }else{
            darTurnoBlancas();
        }
    }
}
