package com.company;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Rachel
 * Date: 11/14/13
 * Time: 7:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class Board {
    private HashMap<String, String> board = new HashMap<String, String>();
    private ArrayList<String> letters = new ArrayList<String>();

    public Board(){
        addLetters();
        makeBoard();
      }

    public void makeBoard(){
        for(int i = 1; i<9; i++)
            for(String letter:letters){
                board.put(letter+i,"-");
            }
    }

    public void printBoard(){

        for(int i= 8; i>0; i--){
            String line = "";
            for(String letter:letters) {
                line +=(board.get(letter + i)+ " ");
            }
            System.out.println(line);
        }
    }

    public void changeInput(String key,String input){

            board.put(key,input);
    }

    public void addLetters(){
        letters.add("A");
        letters.add("B");
        letters.add("C");
        letters.add("D");
        letters.add("E");
        letters.add("F");
        letters.add("G");
        letters.add("H");

    }
}
