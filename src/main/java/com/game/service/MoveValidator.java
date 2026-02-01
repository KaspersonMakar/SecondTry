package com.game.service;

import com.game.model.*;

public class MoveValidator {
  public boolean isMoveValid(Board board, Position from, Position to) {
    if (from.equals(to)) return false;
    if (!isValidCoordinate(to)) return false;

    Piece piece = board.getPiece(from);
    if (piece == null) return false;

    Piece target = board.getPiece(to);
    if (target != null && target.getColor() == piece.getColor()) {
      return false;
    }

    if (!isValidGeometryAndPath(board, piece, from, to)) {
      return false;
    }

    // 3. (Опционально для базовой логики, но обязательно для шахмат)
    // Проверка: не открывает ли этот ход шаха своему королю?
    // Обычно это делается симуляцией хода на копии доски.
    return true;
  }

  private boolean isValidGeometryAndPath(Board board, Piece piece, Position from, Position to) {
    int dRow = Math.abs(to.row() - from.row());
    int dCol = Math.abs(to.column() - from.column());

    switch (piece.getPieceType()) {
      case ROOK:
        // Ладья: либо строка, либо колонка должна совпадать (одна из дельт 0)
        if (dRow != 0 && dCol != 0) return false;
        return isPathClear(board, from, to);

      case BISHOP:
        // Слон: дельты равны (диагональ)
        if (dRow != dCol) return false;
        return isPathClear(board, from, to);

      case KNIGHT:
        // Конь: "Г"-образный ход (2+1)
        // Коню не нужен isPathClear, он перепрыгивает
        return (dRow == 2 && dCol == 1) || (dRow == 1 && dCol == 2);

      case QUEEN:
        // Ферзь: Ладья OR Слон
        boolean isLinear = (dRow == 0 || dCol == 0);
        boolean isDiagonal = (dRow == dCol);
        if (!isLinear && !isDiagonal) return false;
        return isPathClear(board, from, to);

      case PAWN:
        // Пешка - самая сложная логика (направление, первый ход, взятие)
        // Тут потребуется отдельный метод, учитывающий цвет фигуры.
        return checkPawnMove(board, piece, from, to);

      case KING:
        // Король: сдвиг не более чем на 1 клетку
        return dRow <= 1 && dCol <= 1;

      default:
        return false;
    }
  }

  private boolean isPathClear(Board board, Position from, Position to) {
    int dRow = Integer.compare(to.row(), from.row()); // Вернет -1, 0 или 1
    int dCol = Integer.compare(to.column(), from.column());

    int currRow = from.row() + dRow;
    int currCol = from.column() + dCol;

    while (currRow != to.row() || currCol != to.column()) {
      if (board.getPiece(new Position(currRow, currCol)) != null) {
        return false;
      }
      currRow += dRow;
      currCol += dCol;
    }
    return true;
  }

  private boolean isValidCoordinate(Position p) {
    return p.row() >= 0 && p.row() < 8 && p.column() >= 0 && p.column() < 8;
  }

  // Метод для пешки (упрощенно)
  private boolean checkPawnMove(Board board, Piece piece, Position from, Position to) {
    int direction =
        (piece.getColor() == Color.WHITE) ? 1 : -1; // Белые идут вверх (+), черные вниз (-)
    int diffRow = to.row() - from.row();
    int diffCol = Math.abs(to.column() - from.column());

    // Обычный ход вперед на 1
    if (diffCol == 0 && diffRow == direction) {
      return board.getPiece(to) == null;
    }

    // Длинный ход на 2 (только со стартовой позиции)
    boolean isStartRow =
        (piece.getColor() == Color.WHITE && from.row() == 1)
            || (piece.getColor() == Color.BLACK && from.row() == 6);
    if (diffCol == 0 && diffRow == 2 * direction && isStartRow) {
      // Проверяем, что обе клетки впереди пусты
      Position middle = new Position(from.row() + direction, from.column());
      return board.getPiece(middle) == null && board.getPiece(to) == null;
    }

    // Взятие (по диагонали на 1)
    if (diffCol == 1 && diffRow == direction) {
      Piece target = board.getPiece(to);
      return target != null && target.getColor() != piece.getColor();
    }

    return false;
  }
}
