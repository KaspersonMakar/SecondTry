package com.game;

import com.game.model.Board;
import com.game.service.BoardService;
import com.game.service.InputReader;
import com.game.service.MoveValidator;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Board board = new Board();
    BoardService boardService = new BoardService();
    MoveValidator moveValidator = new MoveValidator();
    Scanner console = new Scanner(System.in);
    InputReader inputReader = new InputReader(console);

    boardService.initialize(board);
    boardService.print(board);
    while (true) {
      inputReader.readInput();
      if (moveValidator.isMoveValid(board, inputReader.getFrom(), inputReader.getTo())) {
        boardService.movePiece(board, inputReader.getFrom(), inputReader.getTo());
        boardService.print(board);
      }
    }
  }
}
