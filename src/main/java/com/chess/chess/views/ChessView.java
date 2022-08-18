package com.chess.chess.views;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@PageTitle("Chess Game")
@Route(value = "/chess", layout = MainLayout.class)
@CssImport("./styles/chessStyles.css")
public class ChessView extends VerticalLayout {
}
