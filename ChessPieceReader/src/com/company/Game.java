package com.company;

/**
 * Created with IntelliJ IDEA.
 * User: Rachel
 * Date: 11/19/13
 * Time: 2:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class Game {

    private static Board board = new Board();

    public Game(){
    }

    public static Board getBoard() {
        return board;
    }

    public static void setBoard(Board board) {
        Game.board = board;
    }
}
