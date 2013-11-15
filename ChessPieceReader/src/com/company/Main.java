package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    private static Board board = new Board();

    public static void main(String[] args) {
	// write your code here
//        DirectiveMatcher sdf = new DirectiveMatcher("Kda7");
//        DirectiveMatcher sdsf = new DirectiveMatcher("Kda7 d7 Rdd7 a7");
//        DirectiveMatcher sdfsfsdf = new DirectiveMatcher("Bld7 a6*");
//        DirectiveMatcher ssdfsdfdf = new DirectiveMatcher("RDa8 b6*");
        System.out.println("Capital letters will represent dark pieces, and lowercase letters light pieces");
        ReadFile readFile = new ReadFile(args[0], board);
        board.printBoard();


    }


}
