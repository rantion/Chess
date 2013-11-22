package com.company;

import java.util.ArrayList;

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
    private final int widthHeight = 8;
    private int maxUpDown, maxUpDown1, maxLeftRight, maxLeftRight1;
    public String color, piece;
    public ArrayList<String> validMoves = new ArrayList();
    Board board;



    public Piece(Board board,String color){
        this.board = board;
        this.color = color;

    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void move(String oldLocation,String newLocation, String piece){
        if(board.checkIfSquareEmpty(newLocation)){
            board.removePiece(oldLocation);
            System.out.println("This move was valid and worked good sir!");
            board.placePiece(newLocation,piece);  }

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


    public void checkDiagonalUpperLeft(String startingSquare){
        System.out.println("Inside checkDiagonalUpperLeft");
        splitStartingSquare(startingSquare);
        setMaxes();

            boolean keepChecking = true;
            for(int i = 1; i< getMaxSquare(maxLeftRight1,maxUpDown)&& keepChecking;i++){
                if(!board.checkIfSquareEmpty(moveDiagonalUpperLeft(startingSquare,i))){
                    System.out.println("this is empty");
                    keepChecking = false;
                }
                else{
                    System.out.println(moveDiagonalUpperLeft(startingSquare, i));
                    validMoves.add(moveDiagonalUpperLeft(startingSquare,i));
                }
            }
    }

    public void checkDiagonalUpperRight(String startingSquare){
        System.out.println("Inside checkDiagonalUpperRight");
        splitStartingSquare(startingSquare);
        setMaxes();

        boolean keepChecking = true;
        for(int i = 1; i<getMaxSquare(maxLeftRight1,maxUpDown1) && keepChecking;i++){
            if(!board.checkIfSquareEmpty(moveDiagonalUpperRight(startingSquare, i))){
                System.out.println("this is empty");
                keepChecking = false;
            }
            else{
                System.out.println(moveDiagonalUpperRight(startingSquare, i));
                validMoves.add(moveDiagonalUpperRight(startingSquare, i));
            }
        }
    }

    public void checkDiagonalLowerLeft(String startingSquare){
        System.out.println("Inside checkDiagonalLowerLeft");
        splitStartingSquare(startingSquare);
        setMaxes();


        boolean keepChecking = true;
        for(int i = 1; i<getMaxSquare(maxLeftRight1,maxUpDown1) && keepChecking;i++){
            if(!board.checkIfSquareEmpty(moveDiagonalLowerLeft(startingSquare, i))){
                System.out.println("this is empty");
                keepChecking = false;
            }
            else{
                System.out.println(moveDiagonalLowerLeft(startingSquare, i));
                validMoves.add(moveDiagonalLowerLeft(startingSquare, i));
            }
        }
    }

    public void checkDiagonalLowerRight(String startingSquare){
        System.out.println("Inside checkDiagonalLowerRight");
        splitStartingSquare(startingSquare);
        setMaxes();


        boolean keepChecking = true;
        for(int i = 1; i<getMaxSquare(maxLeftRight,maxUpDown1) && keepChecking;i++){
            if(!board.checkIfSquareEmpty(moveDiagonalLowerRight(startingSquare, i))){
                System.out.println("this is empty");
                keepChecking = false;
            }
            else{
                System.out.println(moveDiagonalLowerRight(startingSquare, i));
                validMoves.add(moveDiagonalLowerRight(startingSquare, i));
            }
        }
    }


    private int getMaxSquare (int leftRight, int upDown){
        int maxSquare = 0;

        System.out.println("LeftRight: "+leftRight);
        System.out.println("UpDown: "+upDown);

        if(leftRight==0){
//            System.out.println("Inside if MaxLeftRight = 0");
            maxSquare = upDown;
        }
        else if(upDown==0){
//            System.out.println("\nInside if UpDown = 0");
            maxSquare = leftRight;
        }
        else if(leftRight<upDown){
//            System.out.println("Inside if RightLeft less than MaxUpDown");
            maxSquare = leftRight;
        }
        else if(leftRight>upDown){
//            System.out.println("Inside if UPDown less than MaxRightLeft");
            maxSquare = upDown;
        }
        else if(leftRight == upDown){
            maxSquare = upDown;
        }
        else{
            System.out.println("Something bad has occured :/");
        }
        System.out.println("Max Square in getMaxSquare method: " +maxSquare);
       return maxSquare;
    }

    private void setMaxes(){
      maxLeftRight = (rightBoundry - letterIndex);
      maxLeftRight1 = widthHeight-(maxLeftRight);
      maxUpDown = (bottomBoundry - numberIndex)*-1;
      maxUpDown1 = widthHeight-(maxUpDown);
        System.out.println("maxLeftRight: "+maxLeftRight);
        System.out.println("maxLeftRight1: "+maxLeftRight1);
        System.out.println("maxUpDown: "+maxUpDown);
        System.out.println("maxUpDown1: "+maxUpDown1);
    }


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
