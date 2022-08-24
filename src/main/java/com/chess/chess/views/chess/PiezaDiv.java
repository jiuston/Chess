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
        String currentPos = pieza.getPosicion();
        List<String> possibleMovements = new ArrayList<>();
        switch (grabbedPiece){
            case "Peon" -> checkPossiblePeonMovements(currentPos, tableroDiv, possibleMovements);
            case "Torre" -> checkPossibleTorreMovements(currentPos, tableroDiv, possibleMovements);
            case "Alfil" -> checkPossibleAlfilMovements(currentPos, tableroDiv, possibleMovements);
            case "Caballo" -> checkPossibleCaballoMovements(currentPos, tableroDiv, possibleMovements);
            case "Reina" -> checkPossibleReinaMovements(currentPos, tableroDiv, possibleMovements);
            case "Rey" -> checkPossibleReyMovements(currentPos, tableroDiv, possibleMovements);
        }
        while (possibleMovements.contains(currentPos)) possibleMovements.remove(currentPos);
        possibleMovements.forEach(System.out::println);
    }

    private void checkPossiblePeonMovements(String currentPos, TableroDiv tableroDiv, List<String> possibleMovements) {
    }

    private void checkPossibleReyMovements(String currentPos, TableroDiv tableroDiv, List<String> possibleMovements) {
    }

    private void checkPossibleReinaMovements(String currentPos, TableroDiv tableroDiv, List<String> possibleMovements) {
    }

    private void checkPossibleCaballoMovements(String currentPos, TableroDiv tableroDiv, List<String> possibleMovements) {
    }

    private void checkPossibleAlfilMovements(String currentPos, TableroDiv tableroDiv, List<String> possibleMovements) {
    }

    private void checkPossibleTorreMovements(String currentPos, TableroDiv tableroDiv, List<String> possibleMovements) {

        int posX = currentPos.charAt(0); //Letra, A B C D E F G H, eje X del tablero.
                                        //En ascii la A es 65 y la H es 72
        int posY = currentPos.charAt(1); //Numero, 1 2 3 4 5 6 7 8, eje Y del tablero.
                                        // En ascii el 1 es 49 y el 8 es 56
        //La torre solo se puede mover en horizontal y vertical.
        //Todas las posiciones que quiera sin saltar piezas
        getPossibleHorizontalMovements(posX, posY, possibleMovements, tableroDiv);
        getPossibleVerticalMovements(posX, posY, possibleMovements, tableroDiv);
    }

    private void getPossibleVerticalMovements(int posX, int posY, List<String> possibleMovements, TableroDiv tableroDiv) {
        //Calculamos los posibles movimientos en el eje X hacia la derecha
        for(int i = posX; i < 73; i++) {
            possibleMovements.add(Character.toString(i) + Character.toString(posY));
        }
        //Hacemos lo mismo hacia la izquierda
        for(int i = posX; i > 64; i--) {
            possibleMovements.add(Character.toString(i) + Character.toString(posY));
        }
    }

    private void getPossibleHorizontalMovements(int posX, int posY, List<String> possibleMovements, TableroDiv tableroDiv) {
        //Calculamos los posibles movimientos en el eje Y hacia arriba
        for(int i = posY; i < 57; i++) {
            //Comprobamos que no haya una pieza en ese cuadrado, si la hay, no puede recorrer más
            possibleMovements.add(Character.toString(posX) + Character.toString(i));

        }
        //Hacemos lo mismo hacia abajo
        for(int i = posY; i >48; i--) {
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
