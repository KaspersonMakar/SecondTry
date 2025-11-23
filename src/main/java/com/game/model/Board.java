package com.game.model;

import java.util.HashMap;
import java.util.Map;

public class Board {
  private Map<Position, Piece> boardMap;

  public void setBoardMap(Map<Position, Piece> boardMap) {
    this.boardMap = boardMap;
  }

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

  private char getPieceSymbol(Piece piece) { //S
    switch (piece.getPieceType()) {
      case PAWN:
        return 'P';
      case KNIGHT:
        return 'N';
      case BISHOP:
        return 'B';
      case ROOK:
        return 'R';
      case QUEEN:
        return 'Q';
      case KING:
        return 'K';
      default:
        return '?';
    }
  }

  public void deletePiece(Position position) {
    this.boardMap.remove(position);
  }

  public void initialize() { //S
    this.boardMap.clear();
    // Расстановка белых фигур
    setPiece(new Position(0, 0), new Piece(Color.WHITE, PieceType.ROOK));
    setPiece(new Position(0, 7), new Piece(Color.WHITE, PieceType.ROOK));
    setPiece(new Position(0, 1), new Piece(Color.WHITE, PieceType.KNIGHT));
    setPiece(new Position(0, 6), new Piece(Color.WHITE, PieceType.KNIGHT));
    setPiece(new Position(0, 2), new Piece(Color.WHITE, PieceType.BISHOP));
    setPiece(new Position(0, 5), new Piece(Color.WHITE, PieceType.BISHOP));
    setPiece(new Position(0, 3), new Piece(Color.WHITE, PieceType.QUEEN));
    setPiece(new Position(0, 4), new Piece(Color.WHITE, PieceType.KING));

    for (int col = 0; col < 8; col++) {
      setPiece(new Position(1, col), new Piece(Color.WHITE, PieceType.PAWN));
    }

    // Расстановка черных фигур
    setPiece(new Position(7, 0), new Piece(Color.BLACK, PieceType.ROOK));
    setPiece(new Position(7, 7), new Piece(Color.BLACK, PieceType.ROOK));
    setPiece(new Position(7, 1), new Piece(Color.BLACK, PieceType.KNIGHT));
    setPiece(new Position(7, 6), new Piece(Color.BLACK, PieceType.KNIGHT));
    setPiece(new Position(7, 2), new Piece(Color.BLACK, PieceType.BISHOP));
    setPiece(new Position(7, 5), new Piece(Color.BLACK, PieceType.BISHOP));
    setPiece(new Position(7, 3), new Piece(Color.BLACK, PieceType.QUEEN));
    setPiece(new Position(7, 4), new Piece(Color.BLACK, PieceType.KING));

    for (int col = 0; col < 8; col++) {
      setPiece(new Position(6, col), new Piece(Color.BLACK, PieceType.PAWN));
    }
  }

  public void print() { //S
    System.out.println("  a b c d e f g h");
    System.out.println("  ----------------");

    for (int row = 7; row >= 0; row--) {
      System.out.print((row + 1) + "|");
      for (int col = 0; col < 8; col++) {
        Position pos = new Position(row, col);
        Piece piece = getPiece(pos);

        if (piece == null) {
          System.out.print(" ");
        } else {
          char symbol = getPieceSymbol(piece);
          if (piece.getColor() == Color.WHITE) {
            System.out.print(symbol);
          } else {
            System.out.print(Character.toLowerCase(symbol));
          }
        }

        if (col < 7) {
          System.out.print(" ");
        }
      }
      System.out.println("|" + (row + 1));
    }
    System.out.println("  ----------------");
    System.out.println("  a b c d e f g h");
  }
}
