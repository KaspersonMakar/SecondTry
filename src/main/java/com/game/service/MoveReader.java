package com.game.service;

import com.game.model.Position;

import java.util.Scanner;

public class MoveReader {
  private final Scanner console;
  private Position from;
  private Position to;

  public MoveReader(Scanner console) {
    this.console = console;
  }

  public void readInput() {
    String move = console.nextLine().trim();
    move = move.toLowerCase();
    if (move.length() != 4) {
      System.out.println("Некорректный ввод: длина");
      clearPos();
      return;
    }
    int colFrom = move.charAt(0) - 'a';
    int rowFrom = move.charAt(1) - '1';
    int colTo = move.charAt(2) - 'a';
    int rowTo = move.charAt(3) - '1';

    if (colFrom > 7 || colFrom < 0 || colTo > 7 || colTo < 0) {
      System.out.println("Некорректный ввод: буквы");
      clearPos();
      return;
    }
    if (rowFrom > 7 || rowFrom < 0 || rowTo > 7 || rowTo < 0) {
      System.out.println("Некорректный ввод: цифры");
      clearPos();
      return;
    }
    from = new Position(rowFrom, colFrom);
    to = new Position(rowTo, colTo);
  }

  private void clearPos() {
    from = null;
    to = null;
  }

  public Position getFrom() {
    return from;
  }

  public Position getTo() {
    return to;
  }
}
