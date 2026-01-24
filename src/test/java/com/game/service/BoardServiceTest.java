package com.game.service;

import com.game.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BoardServiceTest {

  private BoardService boardService;
  private Board board;

  @BeforeEach
  void setUp() {
    boardService = new BoardService();
    board = new Board();
  }

  @Test
  void testInitializeBoard() {
    boardService.initialize(board);

    for (int col = 0; col < 8; col++) {
      assertNotNull(board.getPiece(new Position(0, col)));
      assertEquals(Color.WHITE, board.getPiece(new Position(0, col)).getColor());
      assertNotNull(board.getPiece(new Position(1, col)));
      assertEquals(Color.WHITE, board.getPiece(new Position(1, col)).getColor());
    }

    for (int col = 0; col < 8; col++) {
      assertNotNull(board.getPiece(new Position(6, col)));
      assertEquals(Color.BLACK, board.getPiece(new Position(6, col)).getColor());
      assertNotNull(board.getPiece(new Position(7, col)));
      assertEquals(Color.BLACK, board.getPiece(new Position(7, col)).getColor());
    }
    assertEquals(PieceType.ROOK, board.getPiece(new Position(0, 0)).getPieceType());
    assertEquals(PieceType.KNIGHT, board.getPiece(new Position(0, 1)).getPieceType());
    assertEquals(PieceType.KING, board.getPiece(new Position(7, 4)).getPieceType());
  }

  @Test
  void testMovePieceSuccess() {
    boardService.initialize(board);

    Position from = new Position(1, 4); // e2
    Position to = new Position(3, 4); // e4

    Piece pieceBeforeMove = board.getPiece(from);
    assertNotNull(pieceBeforeMove);

    boardService.movePiece(board, from, to);

    Piece pieceAfterMove = board.getPiece(to);
    assertNotNull(pieceAfterMove);

    assertEquals(pieceBeforeMove.getColor(), pieceAfterMove.getColor());
    assertEquals(pieceBeforeMove.getPieceType(), pieceAfterMove.getPieceType());

    assertNull(board.getPiece(from));
  }

  @Test
  void testMovePieceFromEmptySquare() {
    boardService.initialize(board);
    Position emptyFrom = new Position(3, 3); // empty pos
    Position to = new Position(4, 4);

    boardService.movePiece(board, emptyFrom, to);

    assertNull(board.getPiece(emptyFrom));
    assertNull(board.getPiece(to));
  }
}
