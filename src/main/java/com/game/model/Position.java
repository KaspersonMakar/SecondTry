package com.game.model;

public record Position(int row, int column) {
  @Override
  public String toString() {
    char file = (char) ('a' + column);
    int rank = row + 1;
    return "" + file + rank;
  }
}
