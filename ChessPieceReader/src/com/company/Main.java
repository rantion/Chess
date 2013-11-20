package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    private static Board board;
    private static Game game = new Game();

    public static void main(String[] args) {
          board = game.getBoard();
        ReadFile initial = new ReadFile("/Users/Rachel/Documents/initialBoard.txt");
        board.printBoard();
//
//        System.out.println("Capital letters will represent dark pieces, and lowercase letters light pieces");
//        ReadFile readFile = new ReadFile(args[0]);
//        board.printBoard();



//        Piece piece = new King();
//        piece.checkMove("A1","A2");
//        Piece piece1 = new King();
//        piece1.checkMove("A1","A9");




    }


}
