package com.game.service;

import com.game.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
  private Board board;

  @BeforeEach
  void setUp() {
    board = new Board();
  }

  @Test
  void testSetAndGetPiece() {
    Position position = new Position(0, 0);
    Piece piece = new Piece(Color.WHITE, PieceType.ROOK, position);

    board.setPiece(position, piece);
    Piece gettedPiece = board.getPiece(position);

    assertNotNull(gettedPiece);
    assertEquals(Color.WHITE, gettedPiece.getColor());
    assertEquals(PieceType.ROOK, gettedPiece.getPieceType());
  }

  @Test
  void testDeletePiece() {
    Position position = new Position(0, 0);
    Piece piece = new Piece(Color.WHITE, PieceType.ROOK, position);

    board.setPiece(position, piece);
    assertNotNull(board.getPiece(position));

    board.deletePiece(position);
    assertNull(board.getPiece(position));
  }

  @Test
  void testClearBoard() {
    board.setPiece(new Piece(Color.WHITE, PieceType.ROOK, new Position(0, 0)));
    board.setPiece(new Piece(Color.BLACK, PieceType.QUEEN, new Position(7, 7)));

    board.clear();

    assertNull(board.getPiece(new Position(0, 0)));
    assertNull(board.getPiece(new Position(7, 7)));
  }
}
