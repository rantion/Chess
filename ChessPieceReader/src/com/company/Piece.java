package com.company;

/**
 * Created with IntelliJ IDEA.
 * User: Rachel
 * Date: 11/18/13
 * Time: 9:31 AM
 * To change this template use File | Settings | File Templates.
 */
public class Piece {

    private int letterIndex, numberIndex;
    private char newNumChar, newLetterChar;
    private final int leftBoundry = 65; //ascii value of A
    private final int rightBoundry = 72; //ascii value of H
    private final int bottomBoundry = 49; //ascii value of 1
    private final int topBoundry = 56; //ascii value of 8




    public Piece(){


    }

    public void move(String newLocation){
        System.out.println("This move was valid and worked good sir!");
    }

    public void checkMove(String firstLocation, String secondLocation){

    }

    public void notValid(){
        System.out.println("This was not a valid move.");
    }

    // <editor-fold desc="Move Methods">

    public String moveHorizontallyRight(String startingSquare,int numOfTimes){
        splitStartingSquare(startingSquare);
        letterIndex = letterIndex +numOfTimes;
        newLocation();
       return newLocation();
    }

    public String moveHorizontallyLeft(String startingSquare, int numOfTimes){
        splitStartingSquare(startingSquare);
        letterIndex = letterIndex - numOfTimes;
//
//        if(!checkIfInBounds()){
//            notInBounds();
//        }
       return newLocation();
    }

    public String moveVerticallyUp(String startingSquare, int numOfTimes){
        splitStartingSquare(startingSquare);
        numberIndex = numberIndex + numOfTimes;
//
//        if(!checkIfInBounds()){
//            notInBounds();
//        }
       return newLocation();
    }

    public String moveVerticallyDown(String startingSquare, int numOfTimes){
        splitStartingSquare(startingSquare);
        numberIndex = numberIndex - numOfTimes;
//
//        if(!checkIfInBounds()){
//            notInBounds();
//        }
       return newLocation();
    }

    public String moveDiagonalUpperLeft(String startingSquare, int numOfTimes){
        String tempSquare = moveHorizontallyLeft(startingSquare,numOfTimes);
//
//        if(!checkIfInBounds()){
//            notInBounds();
//        }
        return moveVerticallyUp(tempSquare, numOfTimes);

    }

    public String moveDiagonalUpperRight(String startingSquare, int numOfTimes){
        String tempSquare = moveHorizontallyRight(startingSquare, numOfTimes);
//
//        if(!checkIfInBounds()){
//            notInBounds();
//        }
        return moveVerticallyUp(tempSquare, numOfTimes);
    }

    public String moveDiagonalLowerLeft(String startingSquare, int numOfTimes){
        String tempSquare = moveHorizontallyLeft(startingSquare, numOfTimes);
//
//        if(!checkIfInBounds()){
//            notInBounds();
//        }
        return moveVerticallyDown(tempSquare, numOfTimes);
    }

    public String moveDiagonalLowerRight(String startingSquare, int numOfTimes){
        String tempSquare = moveHorizontallyRight(startingSquare, numOfTimes);

//        if(!checkIfInBounds()){
//            notInBounds();
//        }
        return moveVerticallyDown(tempSquare, numOfTimes);
    }

    // </editor-fold>

    // <editor-fold desc="Methods Used by Move Methods">

    private void splitStartingSquare(String startingSquare){
        char[] locations =startingSquare.toCharArray();
        letterIndex = locations[0];
        numberIndex = locations[1];
    }

    private void notInBounds(){
        System.out.println("You are trying to move to a square that does not exist in the scope of a chessboard");
    }

    public void checkIfInBounds(String location){
       splitStartingSquare(location);
        if(letterIndex>=leftBoundry && letterIndex<=rightBoundry && numberIndex<=topBoundry && numberIndex>=bottomBoundry){
                       newLocation();
        }

    }

    private String newLocation(){
        newLetterChar =  (char)letterIndex;
        String newLetter = Character.toString(newLetterChar);
        newNumChar = (char)numberIndex;
        String newNumber = Character.toString(newNumChar);
        String newLocation = newLetter+newNumber;
//        System.out.println(newLocation);
        return  newLocation;

    }

    // </editor-fold>
}
