package com.game.service;

import com.game.model.Color;

public class TurnManager {
  private Color turn;

  public TurnManager() {
    this.turn = Color.WHITE;
  }

  public Color getTurn() {
    return turn;
  }

  public void changeTurn() {
    if (turn == Color.WHITE) {
      turn = Color.BLACK;
      return;
    }
    turn = Color.WHITE;
  }
}
