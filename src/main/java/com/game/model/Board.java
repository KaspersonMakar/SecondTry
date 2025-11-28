package com.game.model;

import java.util.HashMap;
import java.util.Map;

public class Board {

  private Map<Position, Piece> boardMap;

  public Board() {
    this.boardMap = new HashMap<>();
  }

  public Piece getPiece(Position position) {
    if (this.boardMap.containsKey(position)) {
      return this.boardMap.get(position);
    } else {
      return null;
    }
  }

  public void setPiece(Position position, Piece piece) {
    this.boardMap.put(position, piece);
  }

  public void setPiece(Piece piece) {
    this.boardMap.put(piece.getPosition(), piece);
  }

  public void deletePiece(Position position) {
    this.boardMap.remove(position);
  }

  public void clear() {
    boardMap.clear();
  }
}
