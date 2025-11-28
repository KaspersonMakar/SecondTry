package com.game.model;

public enum PieceType {
  KNIGHT('N'),
  QUEEN('Q'),
  KING('K'),
  PAWN('P'),
  BISHOP('B'),
  ROOK('R');
  private final char pieceSymbol;

  PieceType(char pieceSymbol) {
    this.pieceSymbol = pieceSymbol;
  }

  public char getPieceSymbol() {
    return pieceSymbol;
  }
}
