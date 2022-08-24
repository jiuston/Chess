package com.chess.chess.views.chess;

import com.chess.chess.views.chess.piezas.*;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.dnd.DragSource;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.dom.ClassList;

import java.util.ArrayList;
import java.util.List;

public class PiezaDiv extends Div implements DragSource<PiezaDiv>, HasStyle {

    private Pieza pieza;

    public PiezaDiv(){
        setDraggable(true);
        addClassName("pieza");
        addDragStartListener(piezaDivDragStartEvent -> checkPiecePossibleMovements(pieza, (TableroDiv) getParent().get().getParent().get())); //El primer padre es el cuadrado, el segundo es el tablero
    }

    private void checkPiecePossibleMovements(Pieza pieza, TableroDiv tableroDiv) {
        String grabbedPiece = pieza.getNombre();
        switch (grabbedPiece){
            case "Peon" -> checkPossiblePeonMovements(pieza, tableroDiv);
            case "Torre" -> checkPossibleTorreMovements(pieza, tableroDiv);
            case "Alfil" -> checkPossibleAlfilMovements(pieza, tableroDiv);
            case "Caballo" -> checkPossibleCaballoMovements(pieza, tableroDiv);
            case "Reina" -> checkPossibleReinaMovements(pieza, tableroDiv);
            case "Rey" -> checkPossibleReyMovements(pieza, tableroDiv);
        }
    }

    private void checkPossiblePeonMovements(Pieza pieza, TableroDiv tableroDiv) {
    }

    private void checkPossibleReyMovements(Pieza pieza, TableroDiv tableroDiv) {
    }

    private void checkPossibleReinaMovements(Pieza pieza, TableroDiv tableroDiv) {
    }

    private void checkPossibleCaballoMovements(Pieza pieza, TableroDiv tableroDiv) {
    }

    private void checkPossibleAlfilMovements(Pieza pieza, TableroDiv tableroDiv) {
    }

    private void checkPossibleTorreMovements(Pieza pieza, TableroDiv tableroDiv) {
        String currentPos = pieza.getPosicion();
        int posX = currentPos.charAt(0); //Letra, A B C D E F G H, eje X del tablero.
                                        //En ascii la A es 65 y la H es 72
        int posY = currentPos.charAt(1); //Numero, 1 2 3 4 5 6 7 8, eje Y del tablero.
                                        // En ascii el 1 es 49 y el 8 es 56
        //La torre solo se puede mover en horizontal y vertical.
        //Todas las posiciones que quiera sin saltar piezas
        List<String> possibleMovements = new ArrayList<>();

        getPossibleHorizontalMovements(posX, posY, possibleMovements, tableroDiv);
        getPossibleVerticalMovements(posX, posY, possibleMovements, tableroDiv);
        possibleMovements.remove(currentPos);
        possibleMovements.forEach(System.out::println);
    }

    private void getPossibleVerticalMovements(int posX, int posY, List<String> possibleMovements, TableroDiv tableroDiv) {
        //Calculamos los posibles movimientos en el eje X hacia la derecha
        for(int i = posX; i < 73; i++) {
            possibleMovements.add(Character.toString(i) + Character.toString(posY));
        }
        //Hacemos lo mismo hacia la izquierda
        for(int i = posX; i > 65; i--) {
            possibleMovements.add(Character.toString(i) + Character.toString(posY));
        }
    }

    private void getPossibleHorizontalMovements(int posX, int posY, List<String> possibleMovements, TableroDiv tableroDiv) {
        //Calculamos los posibles movimientos en el eje Y hacia arriba
        for(int i = ++posY; i < 57; i++) {
            //Comprobamos que no haya una pieza en ese cuadrado, si la hay, no puede recorrer más
            possibleMovements.add(Character.toString(posX) + Character.toString(i));

        }
        //Hacemos lo mismo hacia abajo
        for(int i = --posY; i >49; i--) {
            possibleMovements.add(Character.toString(posX) + Character.toString(i));
        }
    }

    public void setText(PiezasHtml piezasHtml){
        super.setText(new Html(piezasHtml.getPieza()).getInnerHtml());
        ClassList clases = this.getClassNames();
        String color = clases.contains("piezaNegra") ? "negro" : clases.contains("piezaBlanca") ? "blanco" : "";
        switch (piezasHtml){
            case REY -> this.pieza = new Rey(color);
            case PEON -> this.pieza = new Peon(color);
            case ALFIL -> this.pieza = new Alfil(color);
            case REINA -> this.pieza = new Reina(color);
            case TORRE -> this.pieza = new Torre(color);
            case CABALLO -> this.pieza = new Caballo(color);
        }
        Div cuadroDiv =  this.getParent().isPresent() ? (Div)this.getParent().get() : null;
        if (cuadroDiv!=null){
            String posInicio=cuadroDiv.getId().orElse(null);
            this.pieza.setPosicion(posInicio);
        }
    }



}
