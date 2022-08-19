package com.chess.chess.views.chess;

import com.chess.chess.utils.PiezasHtml;
import com.chess.chess.views.MainLayout;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dnd.DragSource;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Chess Game")
@Route(value = "/chess", layout = MainLayout.class)
@CssImport("./styles/chessStyles.css")
public class ChessView extends VerticalLayout {

  public ChessView() {
    generarTablero();
  }

  private void generarTablero() {
    Div divTablero = new Div();
    divTablero.addClassName("contenedor");

    generarCuadrosPiezas(divTablero);

    VerticalLayout layout = new VerticalLayout(divTablero);
    add(layout);
  }

  private void generarCuadrosPiezas(Div divTablero) {
    boolean flag = true;
    for (int x = 0; x < 4; x++) {
      for (int i = 0; i < 8; i++) {
        Div divCuadroBlanco = new Div();
        divCuadroBlanco.addClassNames("cuadro", "a1");
        Div divCuadroNegro = new Div();
        divCuadroNegro.addClassNames("cuadro", "a2");
        if (flag) {
          divTablero.add(divCuadroBlanco);
          addPieces(i, divCuadroBlanco, x);
        } else {
          divTablero.add(divCuadroNegro);
          addPieces(i, divCuadroNegro, x);
        }

        flag = !flag;
      }
      for (int i = 8; i < 16; i++) {
        Div divCuadroBlanco = new Div();
        divCuadroBlanco.addClassNames("cuadro", "a1");
        Div divCuadroNegro = new Div();
        divCuadroNegro.addClassNames("cuadro", "a2");
        if (flag) {
          divTablero.add(divCuadroNegro);
          addPieces(i, divCuadroNegro, x);
        } else {
          divTablero.add(divCuadroBlanco);
          addPieces(i, divCuadroBlanco, x);
        }
        flag = !flag;
      }
    }
  }

  private void addPieces(int i, Div divCuadro, int x) {
    Pieza pieza = new Pieza();
    if (x == 0) { //Si X es 0 está pintando las dos primeras filas del tableo que corresponden a las piezas negras
      pieza.addClassNames("piezaNegra");
      divCuadro.add(pieza);
      switch (i) {
        case 0, 7 -> pieza.setText(PiezasHtml.torre.getInnerHtml());
        case 1, 6 -> pieza.setText(PiezasHtml.caballo.getInnerHtml());
        case 2, 5 -> pieza.setText(PiezasHtml.alfil.getInnerHtml());
        case 3 -> pieza.setText(PiezasHtml.reina.getInnerHtml());
        case 4 -> pieza.setText(PiezasHtml.rey.getInnerHtml());
        default -> pieza.setText(PiezasHtml.peon.getInnerHtml());
      }
    } else if (x==3){ //Si X es 0 está pintando las dos primeras filas del tableo que corresponden a las piezas blancas
      pieza.addClassNames("piezaBlanca");
      divCuadro.add(pieza);
      switch (i) {
        case 8, 15 -> pieza.setText(PiezasHtml.torre.getInnerHtml());
        case 9, 14 -> pieza.setText(PiezasHtml.caballo.getInnerHtml());
        case 10, 13 -> pieza.setText(PiezasHtml.alfil.getInnerHtml());
        case 11 -> pieza.setText(PiezasHtml.reina.getInnerHtml());
        case 12 -> pieza.setText(PiezasHtml.rey.getInnerHtml());
        default -> pieza.setText(PiezasHtml.peon.getInnerHtml());
      }
    }
  }

}
