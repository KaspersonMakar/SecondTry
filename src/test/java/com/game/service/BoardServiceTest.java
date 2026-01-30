package com.game.service;

import com.game.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BoardServiceTest {

  private BoardService boardService;
  private Board board;

  @BeforeEach
  void setUp() {
    boardService = new BoardService();
    board = new Board();
    boardService.initialize(board);
  }

  private boolean checkEmptyFiles() {
    boolean checkResult = true;
    for (int col = 0; col < 8; col++) {
      for (int row = 2; row < 6; row++) {
        if (board.getPiece(new Position(row, col)) != null) {
          checkResult = false;
        }
      }
    }
    return checkResult;
  }

  private boolean checkPawnFiles() {
    boolean checkResult = true;
    for (int col = 0; col < 8; col++) {
      for (int row : new int[] {1, 6}) {
        if (board.getPiece(new Position(row, col)).getPieceType() != PieceType.PAWN) {
          checkResult = false;
        }
      }
    }
    return checkResult;
  }

  private boolean checkBackRankFiles() {
    Map<Integer, PieceType> expectedPieces =
        Map.of(
            0, PieceType.ROOK,
            1, PieceType.KNIGHT,
            2, PieceType.BISHOP,
            3, PieceType.QUEEN,
            4, PieceType.KING,
            5, PieceType.BISHOP,
            6, PieceType.KNIGHT,
            7, PieceType.ROOK);

    for (int row : new int[] {0, 7}) {
      for (int col = 0; col < 8; col++) {
        PieceType expected = expectedPieces.get(col);
        PieceType actual = board.getPiece(new Position(row, col)).getPieceType();

        if (actual != expected) {
          return false;
        }
      }
    }
    return true;
  }

  @Test
  void initialize_should_initialize_successfully() {
    assertTrue(checkEmptyFiles());
    assertTrue(checkPawnFiles());
    assertTrue(checkBackRankFiles());
  }

  @Test
  void movePiece_SuccessFlow() {

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
  void movePiece_fromEmptySquare_shouldNotModifyBoard() {
    Position emptyFrom = new Position(3, 3); // empty pos
    Position to = new Position(4, 4);

    boardService.movePiece(board, emptyFrom, to);

    assertNull(board.getPiece(emptyFrom));
    assertNull(board.getPiece(to));
  }

  @Test
  void movePiece_toOutOfBoard_shouldNotModifyBoard() {
    Position from = new Position(0, 0);
    Position to = new Position(0, -4); // oob

    boardService.movePiece(board, from, to);

    assertEquals(PieceType.ROOK, board.getPiece(from).getPieceType());
    assertNull(board.getPiece(to));
  }
}
