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


    public Board(){
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

    public void printBoard(){
        for(int i = 0; i< board.length; i++){
            String line = "";
            for(int j = 0; j< board.length; j++){
                line += (board[i][j].getContent()+" ");
            }
            System.out.println(line);
        }
    }


    public void removePiece(String location){
        char[] keys =location.toCharArray();
        int index1 = convertLetter(keys[1]-indexNumberReference)-2;
        int index2 = keys[0]-indexLetterReference;
        board[index1][index2].setContent("-");
    }

    public boolean checkIfSquareEmpty(String location){
        System.out.println("Location: "+location);
        System.out.println("Inside checkIfSquareEmpty: ");
        boolean empty = false;
        char[] keys =location.toCharArray();
        int index1 = convertLetter(keys[1]-indexNumberReference)-2;
        int index2 = keys[0]-indexLetterReference;

        if(board[index1][index2].getContent() != "-")  {
            System.out.println("There is already a piece there.");
            empty = false;
        }
        else {
            empty = true;
        }
        return empty;

    }

    public void placePiece (String location, String input){
           char[] keys =location.toCharArray();
           int index1 = convertLetter(keys[1]-indexNumberReference)-2;
                //-2 because although a8 is a valid square on the chessboard, the array is 0-7
           int index2 = keys[0]-indexLetterReference;
            board[index1][index2].setContent(input);


    }

    private int convertLetter(int key2){

           return (WIDTH+1)-key2;

    }

}
