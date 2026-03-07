package com.game;

import com.game.model.Board;
import com.game.service.BoardService;
import com.game.service.MoveReader;
import com.game.service.MoveValidator;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Board board = new Board();
    BoardService boardService = new BoardService();
    MoveValidator moveValidator = new MoveValidator();
    Scanner console = new Scanner(System.in);
    MoveReader moveReader = new MoveReader(console);

    boardService.initialize(board);
    boardService.print(board);
    while (true) {
      moveReader.readInput();
      if (moveValidator.isMoveValid(board, moveReader.getFrom(), moveReader.getTo())) {
        boardService.movePiece(board, moveReader.getFrom(), moveReader.getTo());
        boardService.print(board);
      }
    }
  }
}
