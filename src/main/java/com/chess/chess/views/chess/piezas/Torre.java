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
    public void getPossibleDivsToEnd(List<Div> cuadrosTablero, int posX, int posY, List<Div> cuadrosPosibles) {
        cuadrosTablero.forEach(
                div -> {
                    if (div.getId().get().startsWith(Character.toString(posX))||div.getId().get().endsWith(Character.toString(posY))) {
                        cuadrosPosibles.add(div);
                    }
                });
    }

    @Override
    public void checkPossibleMovements(String currentPos, List<Div> cuadrosTablero, List<Div> possibleMovements) {
        int posX = currentPos.charAt(0); //Letra, A B C D E F G H, eje X del tablero.
        //En ascii la A es 65 y la H es 72
        int posY = currentPos.charAt(1); //Numero, 1 2 3 4 5 6 7 8, eje Y del tablero.
        // En ascii el 1 es 49 y el 8 es 56
        //La torre solo se puede mover en horizontal y vertical.
        //Todas las posiciones que quiera sin saltar piezas
        List<Div> cuadrosPosibles = new ArrayList<>();
        //Coge todos los divs verticales y horizontales sin comprobar que tengan o no piezas
        getPossibleDivsToEnd(cuadrosTablero,posX, posY, cuadrosPosibles);
        // A partir de esos Divs anteriores, comprueba si es posible ir a ellos o no
        getPossibleMovements(cuadrosPosibles, possibleMovements, posX, posY);
    }

    public Boolean canEnroque(){
        return !hasAlreadyMoved;
    }
}
