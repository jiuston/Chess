package com.chess.chess.views;

import com.chess.chess.service.PlayerService;
//import com.vaadin.flow.component.Chart;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "dashboard", layout = MainLayout.class)
@PageTitle("Dashboard | Players")
public class DashboardView extends VerticalLayout {

    private final PlayerService playerService;

    public DashboardView(PlayerService playerService) {
        this.playerService = playerService;
        addClassName("dashboard-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        //add(getPlayerStats(), getEstadosChart());
    }

//    private Chart getEstadosChart() {
//    }
//
//    private Component getPlayerStats() {
//        Span stats = new Span(playerService.countPlayers() + " players");
//        stats.addClassNames("text-xl", "mt-m");
//        return stats;
//    }
}
