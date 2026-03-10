package com.game.service;

import com.game.model.Board;

import java.util.Scanner;

public class GameManager {
  MoveReader moveReader;
  MoveValidator moveValidator;
  TurnManager turnManager;
  BoardService boardService;
  Board board;

  public GameManager(Scanner console) {
    this.boardService = new BoardService();
    this.moveValidator = new MoveValidator();
    this.turnManager = new TurnManager();
    this.board = new Board();
    this.moveReader = new MoveReader(console);
  }

  public GameManager(Scanner console, Board board) {
    this.boardService = new BoardService();
    this.moveValidator = new MoveValidator();
    this.turnManager = new TurnManager();
    this.board = board;
    this.moveReader = new MoveReader(console);
  }

  public void startGame() {
    boardService.initialize(board);
    boardService.print(board);
  }

  public void makeNextTurn() {
    System.out.print(turnManager.getTurn() + "'s turn:");
    moveReader.readInput();
    if (moveValidator.isMoveValid(
        board, moveReader.getFrom(), moveReader.getTo(), turnManager.getTurn())) {
      boardService.movePiece(board, moveReader.getFrom(), moveReader.getTo());
      turnManager.changeTurn();
      boardService.print(board);
    } else System.out.println("illegal move");
  }
}
