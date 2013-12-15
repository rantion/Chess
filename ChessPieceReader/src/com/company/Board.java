package com.company;


import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Rachel
 * Date: 11/14/13
 * Time: 7:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class Board extends JPanel {

    private Square board [][];
    private final int WIDTH = 8;
    private final int HEIGHT = 8;
    private final char A ='A';
    private final char int1 ='1';
    private final int indexLetterReference = (int)A;
    private final int indexNumberReference = (int)int1;
    private Players players;
    private String [] letters = {"A", "B","C","D","E","F","G","H"};
    private String [] numbers = {"8","7","6","5","4","3","2","1"};
    private JLabel location;
    private boolean isWhiteSquare;


    public Board(Players players){
        isWhiteSquare = true;
        GridLayout gridLayout = new GridLayout(8,8);
        this.setLayout(gridLayout);
        this.setBounds(0,0,1000,1000);
        this.players=players;
        makeBoard();
    }

    public void makeBoard(){
        board = new Square[WIDTH][HEIGHT];
        for(int i = 0; i< board.length; i++){
            for(int j = 0; j< board[i].length; j++){
                Square panel = new Square("-");
                board[i][j]= panel;
                panel.setLayout(new BorderLayout());
                String locationAddress = letters[j]+numbers[i];
                location = new JLabel(locationAddress);
                location.setVisible(true);
                if(isWhiteSquare){
                    panel.setBackground(Color.CYAN);
                    JLabel test = new JLabel();
                    test.setIcon(new ImageIcon("/Users/Rachel/Chess/ChessPieceReader/Dark Pieces/DarkKnight.png"));
                    this.add(test, BorderLayout.CENTER);
                }
                if(!isWhiteSquare){
                    panel.setBackground(Color.DARK_GRAY);

                }
                this.isWhiteSquare = (isWhiteSquare) ? false : true;
                this.add(panel);
                panel.add(location,BorderLayout.PAGE_END);
                panel.setVisible(true);
            }
            this.isWhiteSquare=(isWhiteSquare)? false: true;
        }
    }

    public Square[][] getBoard(){
        return board;
    }


    public void removePiece(Location location){
        int index1 = location.getIndexOne();
        int index2 = location.getIndexTwo();
        Piece pieceToRemove = getPiece(location);
        if(pieceToRemove.color.equals("L")) {
            Team lightTeam = players.lightLeader.getTeam();
            lightTeam.addPiecesToMap(pieceToRemove,new Location("captured"));
           }
        if(pieceToRemove.color.equals("D")){
            Team darkTeam = players.darkLeader.getTeam();
            darkTeam.addPiecesToMap(pieceToRemove,new Location("captured"));
        }

        board[index1][index2].setContent("-");
        board[index1][index2].setPiece(null);

    }

    public Piece getPiece(Location location){
        int index1 = location.getIndexOne();
        int index2 = location.getIndexTwo();
        Piece piece = board[index1][index2].getPiece();
         return piece;
    }

    public boolean isSquareEmpty(Location location){
        boolean empty = false;
        int index1 = location.getIndexOne();
        int index2 = location.getIndexTwo();
        if(board[index1][index2].getContent().equals("-"))  {
                empty = true;

        }
        else {
            empty = false;
        }
        return empty;

    }

    public void placePiece (Location location, Piece piece, String input){
        String content = input;
        ImageIcon pieceImage = null;
           int index1 = location.getIndexOne();
           int index2 = location.getIndexTwo();
        if(piece.color.equals("L")) {
            Team lightTeam = players.lightLeader.getTeam();
            lightTeam.addPiecesToMap(piece,location);
            pieceImage = piece.getLightImage();
        }
        if(piece.color.equals("D")){
            Team darkTeam = players.darkLeader.getTeam();
            darkTeam.addPiecesToMap(piece,location);
            pieceImage = piece.getDarkImage();
        }
            board[index1][index2].setContent(content);
            board[index1][index2].setPiece(piece);
            board[index1][index2].setPicture(pieceImage);


    }

    private int convertLetter(int key2){

           return (WIDTH+1)-key2;

    }

}
