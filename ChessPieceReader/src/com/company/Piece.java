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
        if(board.checkIfSquareEmpty(newLocation,this.color)){
            board.removePiece(oldLocation);
            board.placePiece(newLocation, this,piece);
            board.printBoard();
        }

    }

    public void checkMove(String firstLocation, String secondLocation){

    }

    public void notValid(){
        System.out.println("The move you tried to execute was not valid");
    }

    public void checkKnight(String firstLocation){
        if(board.checkIfSquareEmpty(moveVerticallyUp(moveHorizontallyLeft(firstLocation,2),1),this.color)){
            validMoves.add(moveVerticallyUp(moveHorizontallyLeft(firstLocation,2),1));
        }
        if(board.checkIfSquareEmpty(moveVerticallyDown(moveHorizontallyLeft(firstLocation,2),1),this.color)){
            validMoves.add(moveVerticallyDown(moveHorizontallyLeft(firstLocation,2),1));
        }
        if(board.checkIfSquareEmpty(moveVerticallyUp(moveHorizontallyRight(firstLocation,2),1),this.color)){
            validMoves.add(moveVerticallyUp(moveHorizontallyRight(firstLocation,2),1));
        }
        if(board.checkIfSquareEmpty(moveVerticallyDown(moveHorizontallyRight(firstLocation,2),1),this.color)){
            validMoves.add(moveVerticallyDown(moveHorizontallyRight(firstLocation,2),1));
        }
        if(board.checkIfSquareEmpty(moveVerticallyUp(moveHorizontallyLeft(firstLocation,1),2),this.color)){
            validMoves.add(moveVerticallyUp(moveHorizontallyLeft(firstLocation,1),2));
        }
        if(board.checkIfSquareEmpty(moveVerticallyDown(moveHorizontallyLeft(firstLocation,1),2),this.color)){
            validMoves.add(moveVerticallyDown(moveHorizontallyLeft(firstLocation,1),2));
        }
        if(board.checkIfSquareEmpty(moveVerticallyUp(moveHorizontallyRight(firstLocation,1),2),this.color)){
            validMoves.add(moveVerticallyUp(moveHorizontallyRight(firstLocation,1),2));
        }
        if(board.checkIfSquareEmpty(moveVerticallyDown(moveHorizontallyRight(firstLocation,1),2),this.color)){
            validMoves.add(moveVerticallyDown(moveHorizontallyRight(firstLocation,1),2));
        }


    }

    public void checkKing(String firstLocation){
        if(board.checkIfSquareEmpty(moveDiagonalUpperLeft(firstLocation,1),this.color)){
            validMoves.add(moveDiagonalUpperLeft(firstLocation,1));
        }
        if(board.checkIfSquareEmpty(moveDiagonalLowerLeft(firstLocation,1),this.color)){
            validMoves.add(moveDiagonalLowerLeft(firstLocation,1));
        }
        if(board.checkIfSquareEmpty(moveDiagonalLowerRight(firstLocation,1),this.color)){
            validMoves.add(moveDiagonalLowerRight(firstLocation,1));
        }
        if(board.checkIfSquareEmpty(moveDiagonalUpperRight(firstLocation,1),this.color)){
            validMoves.add(moveDiagonalUpperRight(firstLocation,1));
        }
        if(board.checkIfSquareEmpty(moveHorizontallyLeft(firstLocation,1),this.color)){
            validMoves.add(moveHorizontallyLeft(firstLocation,1));
        }
        if(board.checkIfSquareEmpty(moveHorizontallyRight(firstLocation,1),this.color)){
            validMoves.add(moveHorizontallyRight(firstLocation,1));
        }
        if(board.checkIfSquareEmpty(moveVerticallyUp(firstLocation,1),this.color)){
            validMoves.add(moveVerticallyUp(firstLocation,1));
        }
        if(board.checkIfSquareEmpty(moveVerticallyDown(firstLocation,1),this.color)){
            validMoves.add(moveVerticallyDown(firstLocation,1));
        }

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
        return newLocation();
    }

    public String moveVerticallyUp(String startingSquare, int numOfTimes){
        splitStartingSquare(startingSquare);
        numberIndex = numberIndex + numOfTimes;
        return newLocation();
    }

    public String moveVerticallyDown(String startingSquare, int numOfTimes){
        splitStartingSquare(startingSquare);
        numberIndex = numberIndex - numOfTimes;
        return newLocation();
    }

    public String moveDiagonalUpperLeft(String startingSquare, int numOfTimes){
        String tempSquare = moveHorizontallyLeft(startingSquare,numOfTimes);
        return moveVerticallyUp(tempSquare, numOfTimes);

    }

    public String moveDiagonalUpperRight(String startingSquare, int numOfTimes){
        String tempSquare = moveHorizontallyRight(startingSquare, numOfTimes);
        return moveVerticallyUp(tempSquare, numOfTimes);
    }

    public String moveDiagonalLowerLeft(String startingSquare, int numOfTimes){
        String tempSquare = moveHorizontallyLeft(startingSquare, numOfTimes);
        return moveVerticallyDown(tempSquare, numOfTimes);
    }

    public String moveDiagonalLowerRight(String startingSquare, int numOfTimes){
        String tempSquare = moveHorizontallyRight(startingSquare, numOfTimes);
        return moveVerticallyDown(tempSquare, numOfTimes);
    }

    // </editor-fold>

    // <editor-fold desc="check directions methods">

    public void checkDiagonals(String startingSquare){
        checkDiagonalUpperLeft(startingSquare);
        checkDiagonalUpperRight(startingSquare);
        checkDiagonalLowerLeft(startingSquare);
        checkDiagonalLowerRight(startingSquare);
    }

    public void checkHorizontals(String startingSquare){
        checkHorizontalRight(startingSquare);
        checkHorizontalLeft(startingSquare);
    }

    public void checkVerticals(String startingSquare){
        checkVerticalUp(startingSquare);
        checkVerticalDown(startingSquare);
    }

    //</editor-fold>

    // <editor-fold desc="Individual check directions methods">
    private void checkDiagonalUpperLeft(String startingSquare){
        splitStartingSquare(startingSquare);
         int maxSquares = getMaxSquareDiagonalUpLeft();

            boolean keepChecking = true;
            for(int i = 1; i<=maxSquares && keepChecking;i++){
                if(!board.checkIfSquareEmpty(moveDiagonalUpperLeft(startingSquare,i),this.color)){
                    keepChecking = false;
                }
                else{
                    validMoves.add(moveDiagonalUpperLeft(startingSquare,i));
                }
            }
    }

    private void checkDiagonalUpperRight(String startingSquare){
        splitStartingSquare(startingSquare);

        boolean keepChecking = true;
        int maxSquare = getMaxSquareDiagonalUpRight();
        for(int i = 1; i<=maxSquare && keepChecking;i++){
            if(!board.checkIfSquareEmpty(moveDiagonalUpperRight(startingSquare, i),this.color)){
                keepChecking = false;
            }
            else{
                validMoves.add(moveDiagonalUpperRight(startingSquare, i));
            }
        }
    }

    private void checkDiagonalLowerLeft(String startingSquare){
        splitStartingSquare(startingSquare);
        boolean keepChecking = true;
        int maxSquares = getMaxSquareDiagonalLowLeft();

        for(int i = 1; i<=maxSquares && keepChecking;i++){
            if(!board.checkIfSquareEmpty(moveDiagonalLowerLeft(startingSquare, i),this.color)){
                keepChecking = false;
            }
            else{
                validMoves.add(moveDiagonalLowerLeft(startingSquare, i));
            }
        }
    }

    private void checkDiagonalLowerRight(String startingSquare){
        splitStartingSquare(startingSquare);

        boolean keepChecking = true;
        int maxSquares = getMaxSquareDiagonalLowRight();
        for(int i = 1; i<=maxSquares && keepChecking;i++){
            if(!board.checkIfSquareEmpty(moveDiagonalLowerRight(startingSquare, i),this.color)){
                keepChecking = false;
            }
            else{
                validMoves.add(moveDiagonalLowerRight(startingSquare, i));
            }
        }
    }

    private void checkHorizontalRight(String startingSquare){
        splitStartingSquare(startingSquare);
        setMaxes();

        boolean keepChecking = true;
        for(int i = 1; i<=maxLeftRight && keepChecking;i++){
            if(!board.checkIfSquareEmpty(moveHorizontallyRight(startingSquare, i),this.color)){
                keepChecking = false;
            }
            else{
                validMoves.add(moveHorizontallyRight(startingSquare, i));
            }
        }
    }

    private void checkHorizontalLeft(String startingSquare){
        splitStartingSquare(startingSquare);
        setMaxes();

        boolean keepChecking = true;
        for(int i = 1; i<maxLeftRight1 && keepChecking;i++){
            if(!board.checkIfSquareEmpty(moveHorizontallyLeft(startingSquare, i),this.color)){
                keepChecking = false;
            }
            else{
                validMoves.add(moveHorizontallyLeft(startingSquare, i));
            }
        }
    }

    private void checkVerticalUp(String startingSquare){
        splitStartingSquare(startingSquare);
        setMaxes();

        boolean keepChecking = true;
        for(int i = 1; i<=maxUpDown && keepChecking;i++){
            if(!board.checkIfSquareEmpty(moveVerticallyUp(startingSquare, i),this.color)){
                keepChecking = false;
            }
            else{
                validMoves.add(moveVerticallyUp(startingSquare, i));
            }
        }
    }

    private void checkVerticalDown(String startingSquare){
        splitStartingSquare(startingSquare);
        setMaxes();

        boolean keepChecking = true;
        for(int i = 1; i<maxUpDown1 && keepChecking;i++){
            if(!board.checkIfSquareEmpty(moveVerticallyDown(startingSquare, i),this.color)){
                keepChecking = false;
            }
            else{
                validMoves.add(moveVerticallyDown(startingSquare, i));
            }
        }
    }

   //</editor-fold>

    // <editor-fold desc="Max Square Methods">
    private int getMaxSquareDiagonalLowRight(){
        int maxSquare = 0;
        boolean keepGoing  = (numberIndex >bottomBoundry && letterIndex <rightBoundry);
        while(keepGoing){
            numberIndex = numberIndex -1;
            letterIndex = letterIndex +1;
            if(numberIndex >bottomBoundry && letterIndex <rightBoundry)  {
                keepGoing = true;
            }
            else{
                keepGoing = false;
            }
            maxSquare = maxSquare +1;
        }
        return maxSquare;

    }

    private int getMaxSquareDiagonalUpRight(){
        int maxSquare = 0;
        boolean keepGoing  = (numberIndex <topBoundry && letterIndex <rightBoundry);
        while(keepGoing){
            numberIndex = numberIndex +1;
            letterIndex = letterIndex +1;
            if(numberIndex <topBoundry && letterIndex <rightBoundry)  {
                keepGoing = true;
            }
            else{
                keepGoing = false;
            }
            maxSquare = maxSquare +1;
        }
        return maxSquare;

    }

    private int getMaxSquareDiagonalUpLeft(){
        int maxSquare = 0;
        boolean keepGoing  = (numberIndex <topBoundry && letterIndex >leftBoundry);
        while(keepGoing){
            numberIndex = numberIndex +1;
            letterIndex = letterIndex -1;
            if(numberIndex <topBoundry && letterIndex >leftBoundry)  {
                keepGoing = true;
            }
            else{
                keepGoing = false;
            }
            maxSquare = maxSquare +1;
        }
        return maxSquare;
    }

    private int getMaxSquareDiagonalLowLeft(){
        int maxSquare = 0;
        boolean keepGoing  = (numberIndex >bottomBoundry && letterIndex <rightBoundry);
        while(keepGoing){
            numberIndex = numberIndex -1;
            letterIndex = letterIndex -1;
            if(numberIndex >bottomBoundry && letterIndex <rightBoundry)  {
                keepGoing = true;
            }
            else{
                keepGoing = false;
            }
            maxSquare = maxSquare +1;
        }
        return maxSquare;
    }

    private void setMaxes(){
      maxLeftRight = (rightBoundry - letterIndex);
      maxLeftRight1 = widthHeight-(maxLeftRight);
      maxUpDown = (topBoundry - numberIndex);
      maxUpDown1 = widthHeight-(maxUpDown);

    }

    //</editor-fold>

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
        return  newLocation;

    }

    // </editor-fold>
}
