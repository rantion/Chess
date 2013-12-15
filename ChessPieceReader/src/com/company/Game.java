package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Rachel
 * Date: 11/19/13
 * Time: 2:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class Game extends JFrame {

    private Players players;
    private Square[][]board;
    private JPanel teamInfoPanel;
    private Board _board;


    public Game(){
        super("CHESS!!");
        setLayout(new BorderLayout());
        this.setSize(900, 1000);
        players = new Players();
        _board = new Board(players);
        teamInfoPanel = new TeamInfoPanel();

        this.getContentPane().add(_board, BorderLayout.CENTER);
        this.getContentPane().add(teamInfoPanel, BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);


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
        System.out.println("\n");
    }

    public Board getGameBoard() {
        return _board;
    }

    public void setBoard(){
        board = _board.getBoard();
    }
//
//    public void setGameBoard(Board gameBoard) {
//        this.gameBoard = gameBoard;
//    }
}
