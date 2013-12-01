package com.company;

/**
 * Created with IntelliJ IDEA.
 * User: Rachel
 * Date: 11/19/13
 * Time: 2:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class Game {

    private Board board = new Board();
    private Players players;


    public Game(){
        players = new Players();
    }

    public Players getPlayers(){
        return players;
    }

    public Board getBoard() {
        return board;
    }

//    public void setBoard(Board board) {
//        this.board = board;
//    }
}
