package com.chess.chess.views.chess;

import com.chess.chess.utils.PiezasHtml;
import com.chess.chess.views.MainLayout;
import com.vaadin.flow.component.dependency.CssImport;
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
    TableroDiv tableroDiv = new TableroDiv();
    VerticalLayout layout = new VerticalLayout(tableroDiv);
    add(layout);
  }





}
