package com.company;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
    private Controller controller;
    private String [] letters = {"A", "B","C","D","E","F","G","H"};
    private String [] numbers = {"8","7","6","5","4","3","2","1"};
    private JLabel location;
    private boolean isWhiteSquare, isFirstSquareSelected;
    private Square firstSquare, secondSquare;


    public Board(Players players){
        this.controller = null;
        this.firstSquare = null;
        this.secondSquare =null;
        isWhiteSquare = true;
        isFirstSquareSelected = true;
        GridLayout gridLayout = new GridLayout(8,8);
        this.setLayout(gridLayout);
        this.players=players;
        makeBoard();
    }

    public void makeBoard(){
        board = new Square[WIDTH][HEIGHT];
        for(int i = 0; i< board.length; i++){
            for(int j = 0; j< board[i].length; j++){
                String locationAddress = letters[j]+numbers[i];
                Square panel = new Square("-", this, new Location(locationAddress));
                board[i][j]= panel;
                panel.setLayout(new BorderLayout());
                location = new JLabel(locationAddress);
                location.setVisible(true);
                if(isWhiteSquare){
                    panel.setBackground(Color.CYAN);  JLabel test = new JLabel();
//                    test.setIcon(new ImageIcon("/Users/Rachel/Chess/ChessPieceReader/White Pieces/whitequeen.png"));
//                    panel.add(test, BorderLayout.CENTER);
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
        board[index1][index2].removePicture();
        this.repaint();

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
        if(piece instanceof Pawn){
            Pawn tempPawn = (Pawn)piece;
            if(tempPawn.doesPawnNeedToBePromoted(location)){
              removePiece(location);
                System.out.println("What type of piece would you like?");
            }
        }
            JLabel picture = new JLabel(pieceImage);
            board[index1][index2].setContent(content);
            board[index1][index2].setPiece(piece);
            board[index1][index2].setPicture(picture);
            this.repaint();


    }

    private void returnSquaresToOriginal(){
        isWhiteSquare = true;
        for(int i = 0; i< board.length; i++){
            for(int j = 0; j< board[i].length; j++){
                if(isWhiteSquare){
                    board[i][j].setBackground(Color.CYAN);  JLabel test = new JLabel();
//                    test.setIcon(new ImageIcon("/Users/Rachel/Chess/ChessPieceReader/White Pieces/whitequeen.png"));
//                    panel.add(test, BorderLayout.CENTER);
                }
                if(!isWhiteSquare){
                    board[i][j].setBackground(Color.DARK_GRAY);

                }
                this.isWhiteSquare = (isWhiteSquare) ? false : true;
            }
            this.isWhiteSquare=(isWhiteSquare)? false: true;
        }
    }

    public void highlightValidSquares(Square square){
        returnSquaresToOriginal();
        Piece piece = square.getPiece();
        for(Square sqr: createListValidSquares(piece)){
            sqr.setBackground(Color.GREEN);
        }
        this.repaint();
    }

    private ArrayList<Square> createListValidSquares(Piece piece){
        ArrayList<Square> validSquares = new ArrayList<Square>();
        piece.getValidMoves();
        for(Location loc: piece.validMoves){
            Square tempSquare = board[loc.getIndexOne()][loc.getIndexTwo()];
            validSquares.add(tempSquare);
        }
        return validSquares;
    }

    private boolean isValidFirstSquare(Square square){
        Location loc =players.currentPlayer.getTeam().getTeamPieces().get(square.getPiece());
        square.getPiece().populateValidMoves(loc);
        boolean isValidFirstSquare = false;
        System.out.println("Piece: "+square.getPiece());
        System.out.println("Color: "+square.getPiece().getColor());
        System.out.println("Valid Moves: " + square.getPiece().validMoves);
        if(square.getPiece() != null && square.getPiece().getColor().equals(players.currentPlayer.getColor())&&(square.getPiece().validMoves.size()!=0)){
                isValidFirstSquare = true;
        }
        return isValidFirstSquare;
    }

    public void movePiece(Square firstSquare, Square secondSquare){
        Piece pieceToMove = firstSquare.getPiece();
        remove_Piece(firstSquare);
        addPieceToNewSquare(secondSquare, pieceToMove);

    }

    private void addPieceToNewSquare(Square secondSquare, Piece piece){
        ImageIcon pieceImage = null;
        if(secondSquare.getPiece() != null){
            remove_Piece(secondSquare);
        }
        if(piece.color.equals("L")) {
            Team lightTeam = players.lightLeader.getTeam();
            lightTeam.addPiecesToMap(piece,secondSquare.getSquareLocation());
            pieceImage = piece.getLightImage();
        }
        if(piece.color.equals("D")){
            Team darkTeam = players.darkLeader.getTeam();
            darkTeam.addPiecesToMap(piece,secondSquare.getSquareLocation());
            pieceImage = piece.getDarkImage();
        }
        if(piece instanceof Pawn){
            Pawn tempPawn = (Pawn)piece;
            if(tempPawn.doesPawnNeedToBePromoted(secondSquare.getSquareLocation())){
                removePiece(secondSquare.getSquareLocation());
                System.out.println("What type of piece would you like?");
            }
        }
        JLabel picture = new JLabel(pieceImage);
        secondSquare.setPiece(piece);
        secondSquare.setPicture(picture);
        this.repaint();

    }

    private void remove_Piece(Square firstSquare){
        Piece pieceToRemove = firstSquare.getPiece();
        if(pieceToRemove != null){
            if(pieceToRemove.color.equals("L")) {
                Team lightTeam = players.lightLeader.getTeam();
                lightTeam.addPiecesToMap(pieceToRemove,new Location("captured"));
            }
            if(pieceToRemove.color.equals("D")){
                Team darkTeam = players.darkLeader.getTeam();
                darkTeam.addPiecesToMap(pieceToRemove,new Location("captured"));
            }
        }

        firstSquare.setContent("-");
        firstSquare.setPiece(null);
        firstSquare.removePicture();
        this.repaint();
    }

    private boolean isValidSecondSquare(Square square){
       boolean isValidSecondSquare = false;
        for(Square sqr: createListValidSquares(firstSquare.getPiece())){
            if(sqr.equals(square)){
                isValidSecondSquare = true;
            }
        }
        System.out.println("IsValidSecondSquare:"+isValidSecondSquare);
        return isValidSecondSquare;
    }

    public boolean isFirstSquareSelected(){
        System.out.println("isFirstSquareSelected: "+isFirstSquareSelected);
        return isFirstSquareSelected;
    }

    public void saveFirstSquare(Square square){

        if(isValidFirstSquare(square)){
         firstSquare = square;
        isFirstSquareSelected = false;
            highlightValidSquares(square);
        }
        else{
        }
    }

    public void saveSecondSquare(Square square){
        if(isValidSecondSquare(square)){
           secondSquare = square;
           controller.takeTurn(firstSquare, secondSquare);
        }
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    private int convertLetter(int key2){
           return (WIDTH+1)-key2;

    }

    public void setFirstSquareSelected(boolean firstSquareSelected) {
        isFirstSquareSelected = firstSquareSelected;
    }
}
