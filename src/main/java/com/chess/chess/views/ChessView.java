package com.chess.chess.views;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

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

    generarCuadros(divTablero);

    VerticalLayout layout = new VerticalLayout(divTablero);
    add(layout);
  }

  private void generarCuadros(Div divTablero) {

    Html htmlPeon = new Html("<i>&#9823;</i>");
    boolean flag = true;
    for (int x = 0; x < 4; x++) {
      for (int i = 0; i < 8; i++) {
        Div divCuadroA1 = new Div();
        divCuadroA1.addClassNames("cuadro", "a1");
        Div divCuadroA2 = new Div();
        divCuadroA2.addClassNames("cuadro", "a2");
        Div divPiezaBlanca = new Div();
        divPiezaBlanca.addClassName("piezaBlanca");
        divPiezaBlanca.add(htmlPeon.getInnerHtml());
        if (flag) {
          divTablero.add(divCuadroA1);
          if (x==3)divCuadroA1.add(divPiezaBlanca);
        } else {
          divTablero.add(divCuadroA2);
          if (x==3)divCuadroA2.add(divPiezaBlanca);
        }
        flag = !flag;

      }
      for (int i = 0; i < 8; i++) {
        Div divCuadroA1 = new Div();
        divCuadroA1.addClassNames("cuadro", "a1");
        Div divCuadroA2 = new Div();
        divCuadroA2.addClassNames("cuadro", "a2");
        Div divPiezaNegra = new Div();
        divPiezaNegra.addClassName("piezaNegra");
        divPiezaNegra.add(htmlPeon.getInnerHtml());
        if (flag) {
          divTablero.add(divCuadroA2);
          if (x==0) divCuadroA2.add(divPiezaNegra);
        } else {
          divTablero.add(divCuadroA1);
          if (x==0) divCuadroA1.add(divPiezaNegra);
        }
        flag = !flag;
      }
    }
  }
}
