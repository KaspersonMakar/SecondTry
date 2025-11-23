package com.game.service;

import com.game.model.Board;
import com.game.model.Position;

public class BoardService {
  public void movePiece(Board board, Position from, Position to) {
      if (board.getPiece(from)!=null){
          board.setPiece(to,board.getPiece(from));
          board.deletePiece(from);
    } else System.out.println("movePiece(Position from): getPiece(from) == null");
  }

    public void initialize(Board board) { //S
        board.initialize();
    }

    public void printBoard(Board board) { //S
        board.print();
    }


}
