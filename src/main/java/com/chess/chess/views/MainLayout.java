package com.chess.chess.views;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {

  public MainLayout() {
    createHeader();
    createDrawer();
  }

  private void createHeader() {
    H1 logo = new H1("Vaadin CRM");
    logo.addClassNames("text-l", "m-m");

    HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo);

    header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
    header.setHeight(75, Unit.PIXELS);
    header.setWidth("100%");
    header.addClassNames("py-0", "px-m");

    addToNavbar(header);
  }

  private void createDrawer() {
    RouterLink listLink = new RouterLink("See players", ListView.class);
    RouterLink chessLink = new RouterLink("See chess", ChessView.class);
    setHighlightCondition(listLink,chessLink);
    addToDrawer(new VerticalLayout(listLink, chessLink));
  }

  private void setHighlightCondition(RouterLink... routerLinks){
    for (RouterLink r : routerLinks) {
        r.setHighlightCondition(HighlightConditions.sameLocation());
    }
  }
}
