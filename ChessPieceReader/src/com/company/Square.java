package com.company;

/**
 * Created with IntelliJ IDEA.
 * User: Rachel
 * Date: 11/19/13
 * Time: 9:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class Square {

    private String content;
    private Piece piece;

    public Square(String content){
        this.content = content;
    }

    public Square(String content, Piece piece){
        this.content = content;
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}