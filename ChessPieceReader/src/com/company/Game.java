package com.company;

/**
 * Created with IntelliJ IDEA.
 * User: Rachel
 * Date: 11/19/13
 * Time: 2:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class Game {

    private Board gameBoard;
    private Players players;
    private Square[][]board;


    public Game(){
        players = new Players();
        gameBoard = new Board(players);
    }

    public Players getPlayers(){
        return players;
    }

    public void printBoard(){
        setBoard();
        for(int i = 0; i< board.length; i++){
            String line = "";
            for(int j = 0; j< board.length; j++){
                line += (board[i][j].getContent()+" ");
            }
            System.out.println(line);
        }
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public void setBoard(){
        board = gameBoard.getBoard();
    }
//
//    public void setGameBoard(Board gameBoard) {
//        this.gameBoard = gameBoard;
//    }
}
