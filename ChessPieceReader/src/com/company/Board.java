package com.company;


/**
 * Created with IntelliJ IDEA.
 * User: Rachel
 * Date: 11/14/13
 * Time: 7:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class Board {

    private Square board [][];
    private final int WIDTH = 8;
    private final int HEIGHT = 8;
    private final char A ='A';
    private final char int1 ='1';
    private final int indexLetterReference = (int)A;
    private final int indexNumberReference = (int)int1;
    private Players players;


    public Board(Players players){
        this.players=players;
        makeBoard();
    }

    public void makeBoard(){
        board = new Square[WIDTH][HEIGHT];
        for(int i = 0; i< board.length; i++){
            for(int j = 0; j< board[i].length; j++){
                board[i][j]= new Square("-");
            }
        }
    }

    public Square[][] getBoard(){
        return board;
    }


    public void removePiece(String location){
        char[] keys =location.toCharArray();
        int index1 = convertLetter(keys[1]-indexNumberReference)-2;
        int index2 = keys[0]-indexLetterReference;
        Piece pieceToRemove = getPiece(location);
        if(pieceToRemove.color.equals("L")) {
            Team lightTeam = players.lightLeader.getTeam();
            lightTeam.addPiecesToMap(pieceToRemove,"captured");
           }
        if(pieceToRemove.color.equals("D")){
            Team darkTeam = players.darkLeader.getTeam();
            darkTeam.addPiecesToMap(pieceToRemove,"captured");
        }

        board[index1][index2].setContent("-");
        board[index1][index2].setPiece(null);

    }

    public Piece getPiece(String location){
        char[] keys =location.toCharArray();
        int index1 = convertLetter(keys[1]-indexNumberReference)-2;
        int index2 = keys[0]-indexLetterReference;
        Piece piece = board[index1][index2].getPiece();
         return piece;
    }

    public boolean isSquareEmpty(String location){
        boolean empty = false;
        char[] keys =location.toCharArray();
        int index1 = convertLetter(keys[1]-indexNumberReference)-2;
        int index2 = keys[0]-indexLetterReference;

        if(board[index1][index2].getContent().equals("-"))  {
                empty = true;

        }
        else {
            empty = false;
        }
        return empty;

    }

    public void placePiece (String location, Piece piece, String input){
        String content = input;
           char[] keys =location.toCharArray();
           int index1 = convertLetter(keys[1]-indexNumberReference)-2;
                //-2 because although a8 is a valid square on the chessboard, the array is 0-7
           int index2 = keys[0]-indexLetterReference;
        if(piece.color.equals("l")) {
            Team lightTeam = players.lightLeader.getTeam();
            lightTeam.addPiecesToMap(piece,location);
        }
        if(piece.color.equals("D")){
            Team darkTeam = players.darkLeader.getTeam();
            darkTeam.addPiecesToMap(piece,location);
        }
            board[index1][index2].setContent(content);
            board[index1][index2].setPiece(piece);


    }

    private int convertLetter(int key2){

           return (WIDTH+1)-key2;

    }

}
