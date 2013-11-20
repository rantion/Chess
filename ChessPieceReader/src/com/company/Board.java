package com.company;


import com.sun.xml.internal.messaging.saaj.util.FinalArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Rachel
 * Date: 11/14/13
 * Time: 7:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class Board {

    private String board [][];
    private final int WIDTH = 8;
    private final int HEIGHT = 8;


    public Board(){
        makeBoard();
    }

    public void makeBoard(){
        board = new String [WIDTH][HEIGHT];
        for(int i = 0; i< board.length; i++){
            for(int j = 0; j< board[i].length; j++){
                board[i][j]="-";
            }
        }
    }

    public void setUpBoard(){

    }

    private void setUpDark(){

    }

    private void setUpLight(){

    }


    public void printBoard(){
        for(int i = 0; i< board.length; i++){
            String line = "";
            for(int j = 0; j< board.length; j++){
                line += (board[i][j]+" ");
            }
            System.out.println(line);
        }
    }

    public void placePiece (String location, String input){
           char A = 'A';
           char int1 = '1';
           int indexLetterReference = (int)A;
           int indexNumberReference = (int)int1;

           char[] keys =location.toCharArray();
           int index1 = convertLetter(keys[1]-indexNumberReference)-2;
                //-2 because although a8 is a valid square on the chessboard, the array is 0-7
           int index2 = keys[0]-indexLetterReference;
           board[index1][index2] = input;

    }

    private int convertLetter(int key2){

           return (WIDTH+1)-key2;

    }

}
