package com.chess.chess.views;

import com.chess.chess.modelo.player.Player;
import com.chess.chess.service.PlayerService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.io.IOException;

@PageTitle("Players")
@Route(value = "/players", layout = MainLayout.class)
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
        closeEditor();
    }

    private void configureGrid() {
        grid.addClassNames("player-grid");
        grid.setSizeFull();
        grid.setColumns("nickname", "email");
        grid.addColumn(Player::getEstado).setHeader("Estado");
        grid.addColumn(Player::getGamesPlayed).setHeader("Partidas");
        grid.addColumn(Player::getGamesWon).setHeader("Ganadas");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        //Añade un listener al grid. Usamos asSingleSelect() porque solo queremos 1 player
        //El getValue nos devuelve el player si seleccionamos uno
        grid.asSingleSelect().addValueChangeListener(event ->
                editPlayer(event.getValue()));
    }

    private void configureForm() {
        playerForm = new PlayerForm();
        playerForm.setWidth("25em");
        playerForm.addListener(PlayerForm.SaveEvent.class, this::savePlayer);
        playerForm.addListener(PlayerForm.DeleteEvent.class, this::deletePlayer);
        playerForm.addListener(PlayerForm.CloseEvent.class, closeEvent -> closeEditor());
    }

    private void deletePlayer(PlayerForm.DeleteEvent event) {
        try {
            playerService.delete(event.getPlayer());
        } catch (IOException e) {
           e.printStackTrace();
        }
        setPlayerList();
        closeEditor();
    }

    private void savePlayer(PlayerForm.SaveEvent event) {
        playerService.create(event.getPlayer());
        setPlayerList();
        closeEditor();
    }


    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> setPlayerList());

        Button addPlayerButton = new Button("Add player");
        addPlayerButton.addClickListener(buttonClickEvent -> addPlayer());
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

    /**
     * Asigna el player seleccionado al formulario para rellenarlo con sus valores
     * Muestra u oculta el formulario según la selección
     * @param player
     */
    public void editPlayer(Player player) {
        if (player == null) {
            closeEditor();
        } else {
            playerForm.setPlayer(player);
            playerForm.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        playerForm.setPlayer(null);
        playerForm.setVisible(false);
        removeClassName("editing");
    }

    /**
     * Limpia la seleccion y revela el formulario vacío
     */
    private void addPlayer() {
        grid.asSingleSelect().clear();
        editPlayer(new Player());
    }

    private void setPlayerList() {
        if (filterText.isEmpty())
            grid.setItems(playerService.findAll());
        else
            grid.setItems(playerService.findAllByNicknameLike("%"+filterText.getValue()+"%"));
    }

}
