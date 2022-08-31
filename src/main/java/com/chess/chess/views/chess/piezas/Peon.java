package com.chess.chess.views.chess.piezas;

import com.vaadin.flow.component.html.Div;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Peon extends Pieza{

    private boolean hasAlreadyMoved;
    private boolean hasMoved2Squares;

    public Peon(String color) {
        this.setNombre(getClass().getSimpleName());
        this.setColor(color);
        this.hasAlreadyMoved = false;
        this.hasMoved2Squares = false;
    }

    @Override
    public boolean canMoveToThatPosition(String currentPos, String destPos) {
        return false;
    }

    @Override
    protected void getPossibleDivsToEnd(List<Div> cuadrosTablero, int posX, int posY, List<Div> cuadrosPosibles) {
        getDivsDelante(cuadrosTablero,posX,posY, cuadrosPosibles);
    }

    private void getDivsDelante(List<Div> cuadrosTablero, int posX, int posY, List<Div> cuadrosPosibles) {
        int avance = this.getColor().equals("blanco") ? 1 : -1;
        Div cuadradoDelante = cuadrosTablero.stream().filter(div -> div.getId().get().equals(Character.toString(posX) + Character.toString(posY+avance))).findFirst().orElse(null);
        Div cuadradoDelante2 = null;
        if (!this.isHasAlreadyMoved()) cuadradoDelante2 = cuadrosTablero.stream().filter(div -> div.getId().get().equals(Character.toString(posX) + Character.toString(posY+avance+avance))).findFirst().orElse(null);
        Div cuadradoDiagonalDerecha = cuadrosTablero.stream().filter(div -> div.getId().get().equals(Character.toString(posX+avance) + Character.toString(posY+avance))).findFirst().orElse(null);
        Div cuadradoDiagonalIzquierda = cuadrosTablero.stream().filter(div -> div.getId().get().equals(Character.toString(posX+avance) + Character.toString(posY+avance))).findFirst().orElse(null);
        if (cuadradoDelante!=null) cuadrosPosibles.add(cuadradoDelante);
        if (cuadradoDelante2!=null) cuadrosPosibles.add(cuadradoDelante2);
        if (cuadradoDiagonalDerecha!=null) cuadrosPosibles.add(cuadradoDiagonalDerecha);
        if (cuadradoDiagonalIzquierda!=null) cuadrosPosibles.add(cuadradoDiagonalIzquierda);

    }

    @Override
    public void checkPossibleMovements(String currentPos, List<Div> cuadros, List<Div> possibleMovements) {
        if (isCanMove()){
            int posX = currentPos.charAt(0);
            int posY = currentPos.charAt(1);
            List<Div> cuadrosPosibles = new ArrayList<>();
            getPossibleDivsToEnd(cuadros,posX,posY,cuadrosPosibles);
            getPossibleMovements(cuadrosPosibles,possibleMovements,posX,posY);
        }
    }

    @Override
    public void getPossibleMovements(List<Div> cuadrosPosibles, List<Div> possibleMovements, int posX, int posY){
        //Si no se ha movido, el size de los cuadros posibles es 4, siendo los Div de la posicion 0 y 1 los cuadros que tiene delante
        Div divDelante = cuadrosPosibles.get(0);
        //Si no hay nadie, puede mover ahi
        if (!isCasillaOcupada(divDelante)) {
            possibleMovements.add(divDelante);
            cuadrosPosibles.remove(0);
            if(!this.isHasAlreadyMoved()){
                Div div2Delante = cuadrosPosibles.get(0);
                if (!isCasillaOcupada(div2Delante)) possibleMovements.add(div2Delante);
                cuadrosPosibles.remove(0);
            }
        }
        //Si no se ha movido aun, puede moverse 2 cuadros si no hay nadie
        Div diagonal = null;
        Div diagonal2 = null;
        if (cuadrosPosibles.size()==1){
           diagonal = cuadrosPosibles.get(0);
        }else if (cuadrosPosibles.size()==2){
            diagonal = cuadrosPosibles.get(0);
            diagonal2 = cuadrosPosibles.get(1);
        }
        checkIfEnemiesInDiagonal(possibleMovements, diagonal);
        checkIfEnemiesInDiagonal(possibleMovements, diagonal2);
    }

    private void checkIfEnemiesInDiagonal(List<Div> possibleMovements, Div diagonal) {
        if (diagonal !=null && isCasillaOcupadaPorEnemigo(diagonal)){
            diagonal.addClassName("puedeComerse");
            possibleMovements.add(diagonal);
        }
    }
}
