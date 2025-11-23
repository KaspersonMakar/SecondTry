package com.game.model;

public class Piece {
    Color color;
    PieceType pieceType;
    Position position;

    public Piece(Color color, PieceType pieceType, Position position){
        this.color = color;
        this.pieceType = pieceType;
        this.position = position;
    }

    public Piece(Color color, PieceType pieceType){
        this.color = color;
        this.pieceType = pieceType;
        this.position = null;
    }

    public PieceType getPieceType(){
        return this.pieceType;
    }

    public Color getColor() {return color;
    }
}
