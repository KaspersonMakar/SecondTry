package com.game.service;

import com.game.model.*;

import java.util.Optional;

public class BoardService {
  private static final int BOARD_SIZE = 8;
  private static final int FIRST_ROW = 0;
  private static final int FIRST_COL = 0;
  private static final int LAST_ROW = BOARD_SIZE - 1;
  private static final int WHITE_PAWN_ROW = 1;
  private static final int BLACK_PAWN_ROW = 6;

  public void movePiece(Board board, Position from, Position to) {
    Optional<Piece> pieceOptional = Optional.ofNullable(board.getPiece(from));

    if (pieceOptional.isPresent()) {
      Piece piece = pieceOptional.get();
      board.setPiece(to, piece);
      board.deletePiece(from);
    } else {
      System.out.println("movePiece(Position from): getPiece(from) == null");
    }
  }

  public void initialize(Board board) {
    board.clear();
    PieceType[] backRankPieces = {
      PieceType.ROOK, PieceType.KNIGHT, PieceType.BISHOP, PieceType.QUEEN,
      PieceType.KING, PieceType.BISHOP, PieceType.KNIGHT, PieceType.ROOK
    };
    for (int col = FIRST_COL; col < BOARD_SIZE; col++) {
      board.setPiece(new Piece(Color.WHITE, backRankPieces[col], new Position(FIRST_ROW, col)));
      board.setPiece(new Piece(Color.WHITE, PieceType.PAWN, new Position(WHITE_PAWN_ROW, col)));
    }
    for (int col = FIRST_COL; col < BOARD_SIZE; col++) {
      board.setPiece(new Piece(Color.BLACK, backRankPieces[col], new Position(LAST_ROW, col)));
      board.setPiece(new Piece(Color.BLACK, PieceType.PAWN, new Position(BLACK_PAWN_ROW, col)));
    }
  }

  private void printPieceSymbol(Board board, int row, int col) {
    Optional<Piece> pieceOptional = Optional.ofNullable(board.getPiece(new Position(row, col)));

    if (pieceOptional.isPresent()) {
      Piece piece = pieceOptional.get();
      char symbol = piece.getPieceType().getPieceSymbol();
      if (piece.getColor() == Color.WHITE) {
        System.out.print(symbol);
      } else {
        System.out.print(Character.toLowerCase(symbol));
      }
    } else {
      System.out.print(" ");
    }
  }

  public void print(Board board) {
    System.out.println("  a b c d e f g h");
    System.out.println("  ----------------");

    for (int row = LAST_ROW; row >= FIRST_ROW; row--) {
      System.out.print((row + 1) + "|");
      for (int col = FIRST_COL; col < BOARD_SIZE; col++) {
        printPieceSymbol(board, row, col);
        System.out.print(" ");
      }
      System.out.println("|" + (row + 1));
    }
    System.out.println("  ----------------");
    System.out.println("  a b c d e f g h");
  }
}
