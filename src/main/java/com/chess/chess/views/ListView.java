package com.chess.chess.views;

import com.chess.chess.modelo.player.Player;
import com.chess.chess.service.PlayerService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Players")
@Route(value = "/players")
public class ListView extends VerticalLayout {

    Grid<Player> grid = new Grid<>(Player.class);
    TextField filterText = new TextField();
    PlayerForm playerForm;

    PlayerService playerService;

    public ListView(PlayerService playerService) {
        this.playerService=playerService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        configureForm();
        add(getToolbar(), getContent());
        setPlayerList();
    }

    private void configureGrid() {
        grid.addClassNames("player-grid");
        grid.setSizeFull();
        grid.setColumns("nickname", "email");
        grid.addColumn(Player::getEstado).setHeader("Estado");
        grid.addColumn(Player::getGamesPlayed).setHeader("Partidas");
        grid.addColumn(Player::getGamesWon).setHeader("Ganadas");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private void configureForm() {
        playerForm = new PlayerForm();
        playerForm.setWidth("25em");
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> setPlayerList());

        Button addPlayerButton = new Button("Add player");
        HorizontalLayout toolbar = new HorizontalLayout(filterText, addPlayerButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }
    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, playerForm);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, playerForm);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void setPlayerList() {
        if (filterText.isEmpty())
            grid.setItems(playerService.findAll());
        else
            grid.setItems(playerService.findAllByNicknameLike(filterText.getValue()));
    }

}
