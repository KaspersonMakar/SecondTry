package com.game;

import com.game.model.Board;
import com.game.model.Position;
import com.game.service.BoardService;

public class Main {
  public static void main(String[] args) {
    Board board = new Board();
    BoardService boardService = new BoardService();

    boardService.initialize(board);
    boardService.movePiece(board, new Position(1, 4), new Position(3, 4));
    boardService.print(board);
  }
}
