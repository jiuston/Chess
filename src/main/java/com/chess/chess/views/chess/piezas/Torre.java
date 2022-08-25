package com.chess.chess.views.chess.piezas;

import com.vaadin.flow.component.html.Div;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Torre extends Pieza{

    private boolean hasAlreadyMoved;

    public Torre(String color) {
        this.setNombre(getClass().getSimpleName());
        this.setColor(color);
        this.hasAlreadyMoved = false;
    }

    @Override
    public boolean canMoveToThatPosition(String currentPos, String destPos) {
        return false;
    }

    @Override
    public List<String> getPosibleMovements(String currentPos, List<String> positionsOfFriendlyPieces, List<String> positionsOfEnemyPieces) {
        return null;
    }

    @Override
    public void checkPossibleMovements(String currentPos, List<Div> cuadros, List<Div> possibleMovements) {
        int posX = currentPos.charAt(0); //Letra, A B C D E F G H, eje X del tablero.
        //En ascii la A es 65 y la H es 72
        int posY = currentPos.charAt(1); //Numero, 1 2 3 4 5 6 7 8, eje Y del tablero.
        // En ascii el 1 es 49 y el 8 es 56
        //La torre solo se puede mover en horizontal y vertical.
        //Todas las posiciones que quiera sin saltar piezas

        getPossibleHorizontalMovements(posY, cuadros, possibleMovements);
        getPossibleVerticalMovements(posX, cuadros, possibleMovements);

    }

    public Boolean canEnroque(){
        return !hasAlreadyMoved;
    }
}
