package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    private static Board board = new Board();

    public static void main(String[] args) {

        System.out.println("Capital letters will represent dark pieces, and lowercase letters light pieces");
        ReadFile readFile = new ReadFile(args[0], board);

        board.printBoard();


    }


}
