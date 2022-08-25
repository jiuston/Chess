package com.chess.chess.views.chess.piezas;

import com.chess.chess.views.chess.TableroDiv;
import com.vaadin.flow.component.html.Div;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public abstract class Pieza {

  private String color;
  private String nombre;
  private String posicion;

  public abstract boolean canMoveToThatPosition(String currentPos, String destPos);

  public abstract List<String> getPosibleMovements(
      String currentPos,
      List<String> positionsOfFriendlyPieces,
      List<String> positionsOfEnemyPieces);

  public abstract void checkPossibleMovements(
      String currentPos, List<Div> cuadros, List<Div> possibleMovements);

  public boolean canEatPiece(Pieza piezaToEat) {
    return !piezaToEat.getColor().equals(this.getColor());
  }

  public void getPossibleVerticalMovements(
      int posX, List<Div> divsCuadrado, List<Div> possibleMovements) {
    // Cogemos los cuadrados verticales
    divsCuadrado.forEach(
        div -> {
          if (div.getId().get().startsWith(Character.toString(posX))) {
            possibleMovements.add(div);
          }
        });
  }

  public void getPossibleHorizontalMovements(
      int posY, List<Div> divsCuadrado, List<Div> possibleMovements) {
    // Cogemos los cuadrados horizontales

    divsCuadrado.forEach(
        div -> {
          if (div.getId().get().endsWith(Character.toString(posY))) {
            possibleMovements.add(div);
          }
        });
  }
}
