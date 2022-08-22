package com.chess.chess.views.chess;

import com.chess.chess.utils.PiezasHtml;
import com.vaadin.flow.component.html.Div;

public class TableroDiv extends Div {

    public TableroDiv() {
        addClassName("tablero");
        generarCuadrosPiezas(this);
    }

    private void generarCuadrosPiezas(Div divTablero) {
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
                    divCuadroBlanco.addClassNames("cuadro", "cuadroBlanco", Character.toString(j) + posY);
                    divTablero.add(divCuadroBlanco);
                    addPieces(i, divCuadroBlanco, x);
                } else {
                    Div divCuadroNegro = new Div();
                    divCuadroNegro.addClassNames("cuadro", "cuadroNegro", Character.toString(j) + posY);
                    divTablero.add(divCuadroNegro);
                    addPieces(i, divCuadroNegro, x);
                }
                flag = !flag;
            }
            posY--;
            for (int i = 8; i < 16; i++) {
                if (flag) {
                    Div divCuadroNegro = new Div();
                    divCuadroNegro.addClassNames("cuadro", "cuadroNegro", Character.toString(j) + posY);
                    divTablero.add(divCuadroNegro);
                    addPieces(i, divCuadroNegro, x);
                } else {
                    Div divCuadroBlanco = new Div();
                    divCuadroBlanco.addClassNames("cuadro", "cuadroBlanco", Character.toString(j) + posY);
                    divTablero.add(divCuadroBlanco);
                    addPieces(i, divCuadroBlanco, x);
                }
                flag = !flag;
            }
        }
    }

    private void addPieces(int i, Div divCuadro, int x) {
        PiezaDiv piezaDiv = new PiezaDiv();
        if (x == 0) { //Si X es 0 está pintando las dos primeras filas del tableo que corresponden a las piezas negras
            piezaDiv.addClassNames("piezaNegra");
            divCuadro.add(piezaDiv);
            switch (i) {
                case 0, 7 -> piezaDiv.setText(PiezasHtml.torre.getInnerHtml());
                case 1, 6 -> piezaDiv.setText(PiezasHtml.caballo.getInnerHtml());
                case 2, 5 -> piezaDiv.setText(PiezasHtml.alfil.getInnerHtml());
                case 3 -> piezaDiv.setText(PiezasHtml.reina.getInnerHtml());
                case 4 -> piezaDiv.setText(PiezasHtml.rey.getInnerHtml());
                default -> piezaDiv.setText(PiezasHtml.peon.getInnerHtml());
            }
        } else if (x==3){ //Si X es 0 está pintando las dos primeras filas del tableo que corresponden a las piezas blancas
            piezaDiv.addClassNames("piezaBlanca");
            divCuadro.add(piezaDiv);
            switch (i) {
                case 8, 15 -> piezaDiv.setText(PiezasHtml.torre.getInnerHtml());
                case 9, 14 -> piezaDiv.setText(PiezasHtml.caballo.getInnerHtml());
                case 10, 13 -> piezaDiv.setText(PiezasHtml.alfil.getInnerHtml());
                case 11 -> piezaDiv.setText(PiezasHtml.reina.getInnerHtml());
                case 12 -> piezaDiv.setText(PiezasHtml.rey.getInnerHtml());
                default -> piezaDiv.setText(PiezasHtml.peon.getInnerHtml());
            }
        }
    }
}
