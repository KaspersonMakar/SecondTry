package com.game.model;

import java.util.Objects;

public class Position {
    private int row;
    private int column;

    public Position(int row, int column){
        this.row = row;
        this.column = column;
    }

    public int getRow(){
        return this.row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object o) { //S
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row && column == position.column;
    }

    @Override
    public int hashCode() { //S
        return Objects.hash(row, column);
    }

    // Добавляем toString для удобства отладки
    @Override //S
    public String toString() {
        return "(" + row + "," + column + ")";
    }
}
