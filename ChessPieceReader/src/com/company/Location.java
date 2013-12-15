package com.company;

/**
 * Created with IntelliJ IDEA.
 * User: Rachel
 * Date: 12/12/13
 * Time: 1:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class Location {
    private String location;
    private final int WIDTH = 8;
    private final int HEIGHT = 8;
    private final char A ='A';
    private final char int1 ='1';
    private final int indexLetterReference = (int)A;
    private final int indexNumberReference = (int)int1;
    private int index1, index2;

    public Location(String location){
          this.location = location;
          splitAndConvert();
    }

    public String getLocation(){
         return location;
    }

    private void splitAndConvert(){
        char[] keys =location.toCharArray();
        index1 = convertLetter(keys[1]-indexNumberReference)-2;
        //-2 because although a8 is a valid square on the chessboard, the array is 0-7
        index2 = keys[0]-indexLetterReference;
    }

    private int convertLetter(int key2){
        return (WIDTH+1)-key2;
    }

    public int getIndexOne(){
        return index1;
    }

    public int getIndexTwo(){
        return index2;
    }

    @Override
    public String toString() {
        return getLocation();
    }
}
