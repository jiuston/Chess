package com.chess.chess.views.chess.piezas;

import com.chess.chess.views.chess.PiezaDiv;
import com.vaadin.flow.component.html.Div;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public abstract class Pieza {

  private String color;
  private String nombre;
  private String posicion;
  private boolean canMove;

  public abstract boolean canMoveToThatPosition(String currentPos, String destPos);

  protected abstract void getPossibleDivsToEnd(
      List<Div> cuadrosTablero, int posX, int posY, List<Div> cuadrosPosibles);

  public abstract void checkPossibleMovements(
      String currentPos, List<Div> cuadros, List<Div> possibleMovements);

  protected boolean canEatPiece(Pieza piezaToEat) {
    return !piezaToEat.getColor().equals(this.getColor());
  }

  protected boolean isCasillaOcupadaPorEnemigo(Div div) {
    PiezaDiv piezaDiv = (PiezaDiv) div.getChildren().findFirst().orElse(null);
    if (piezaDiv != null) return !piezaDiv.getPieza().getColor().equals(this.getColor());
    return false;
  }

  protected boolean isCasillaOcupada(Div div) {
    return div.getChildren().findFirst().isPresent();
  }

  protected void getPossibleMovements(
      List<Div> divsCuadrado, List<Div> possibleMovements, int posX, int posY) {
    // Letra, A B C D E F G H, eje X del tablero.
    // En ascii la A es 65 y la H es 72
    // Número, 1 2 3 4 5 6 7 8, eje Y del tablero.
    // En ascii el 1 es 49 y el 8 es 56
    for (int i = posX + 1; i < 73 && canMove; i++) {
      // Comprobamos las casillas hacia la derecha de la pieza
      getHorizontalMovements(divsCuadrado, possibleMovements, posY, i);
    }
    canMove = true;
    for (int i = posX - 1; i > 64 && canMove; i--) {
      // Comprobamos las casillas hacia la izquierda de la pieza
      getHorizontalMovements(divsCuadrado, possibleMovements, posY, i);
    }
    canMove = true;
    for (int i = posY + 1; i < 57 && canMove; i++) {
      // Comprobamos las casillas hacia la arriba de la pieza
      getVerticalMovements(divsCuadrado, possibleMovements, posX, i);
    }
    canMove = true;
    for (int i = posY - 1; i > 48 && canMove; i--) {
      // Comprobamos las casillas hacia la abajo de la pieza
      getVerticalMovements(divsCuadrado, possibleMovements, posX, i);
    }
  }

  private void getVerticalMovements(List<Div> divsCuadrado, List<Div> possibleMovements, int posX, int i){
    Div div =
            divsCuadrado.stream()
                    .filter(
                            div1 -> div1.getId().get().equals(Character.toString(posX) + Character.toString(i)))
                    .findFirst()
                    .orElse(null);
    if (div != null && isCasillaOcupadaPorEnemigo(div)) {
      div.addClassName("puedeComerse");
      possibleMovements.add(div);
      canMove = false;
    } else if (div != null && isCasillaOcupada(div)) canMove = false;
    else possibleMovements.add(div);
  }

  private void getHorizontalMovements(
      List<Div> divsCuadrado, List<Div> possibleMovements, int posY, int i) {
    Div div =
        divsCuadrado.stream()
            .filter(
                div1 -> div1.getId().get().equals(Character.toString(i) + Character.toString(posY)))
            .findFirst()
            .orElse(null);
    if (div != null && isCasillaOcupadaPorEnemigo(div)) {
      div.addClassName("puedeComerse");
      possibleMovements.add(div);
      canMove = false;
    } else if (div != null && isCasillaOcupada(div)) canMove = false;
    else possibleMovements.add(div);
  }
}
