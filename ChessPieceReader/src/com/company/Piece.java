package com.company;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Rachel
 * Date: 11/18/13
 * Time: 9:31 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Piece {

    private int letterIndex, numberIndex;
    private char newNumChar, newLetterChar;
    private final int leftBoundry = 65; //ascii value of A
    private final int rightBoundry = 72; //ascii value of H

    private final int bottomBoundry = 49; //ascii value of 1
    private final int topBoundry = 56; //ascii value of 8
    private final int widthHeight = 8;
    private int maxUpDown, maxUpDown1, maxLeftRight, maxLeftRight1;
    protected String color, piece;
    protected ArrayList<String> validMoves = new ArrayList();
    protected Board board;
    protected Game game;



    public Piece(Game game,String color){
        this.game = game;
        this.board = game.getGameBoard();
        this.color = color;

    }



    public void move(String oldLocation,String newLocation, String piece){
        if(board.isSquareEmpty(newLocation) || !isPieceSameColor(newLocation)){
            board.removePiece(oldLocation);
            if(board.getPiece(newLocation)!=null){
                board.removePiece(newLocation);
            }
            board.placePiece(newLocation, this,piece);
            game.printBoard();
//            System.out.println("\n");
        }

    }

    public void unDoMove(String newLocation, String oldLocation, String piece){
        board.removePiece(newLocation);
        board.placePiece(oldLocation, this, piece);
    }

    public abstract boolean isLegalMove (String firstLocation, String secondLocation);

    protected void notValid(){
        System.out.println("The move you tried to execute was not valid");
    }


    public void checkKnight(String firstLocation){
        ArrayList<String> knightMoveLocations = new ArrayList<String>();

        String leftTwoUpOne = moveVerticallyUp(moveHorizontallyLeft(firstLocation,2),1);
        String leftTwoDownOne = moveVerticallyDown(moveHorizontallyLeft(firstLocation,2),1);
        String rightTwoUpOne = moveVerticallyUp(moveHorizontallyRight(firstLocation,2),1);
        String rightTwoDownOne = moveVerticallyDown(moveHorizontallyRight(firstLocation,2),1);
        String leftOneUpTwo = moveVerticallyUp(moveHorizontallyLeft(firstLocation,1),2);
        String leftOneDownTwo = moveVerticallyDown(moveHorizontallyLeft(firstLocation,1),2);
        String rightOneUpTwo = moveVerticallyUp(moveHorizontallyRight(firstLocation,1),2);
        String rightOneDownTwo =  moveVerticallyDown(moveHorizontallyRight(firstLocation,1),2);

        knightMoveLocations.add(leftTwoUpOne);
        knightMoveLocations.add(leftTwoDownOne);
        knightMoveLocations.add(rightTwoUpOne);

        knightMoveLocations.add(rightTwoDownOne);
        knightMoveLocations.add(leftOneUpTwo);
        knightMoveLocations.add(leftOneDownTwo);
        knightMoveLocations.add(rightOneUpTwo);
        knightMoveLocations.add(rightOneDownTwo);

        validMoves = addValidLocations(knightMoveLocations);
    }


    // <editor-fold desc="Move Methods">

    public boolean inBounds(String location){
        splitStartingSquare(location);
        boolean inBounds = (letterIndex>=leftBoundry && letterIndex<=rightBoundry && numberIndex>= bottomBoundry && numberIndex<=topBoundry);
        return inBounds;
    }

    public ArrayList<String> addValidLocations(ArrayList<String> locationList){
        ArrayList<String> validLocations = new ArrayList<String>();
        for(String location: locationList){
            if(inBounds(location)){
                if(board.isSquareEmpty(location)) {
                    validLocations.add(location);
                }
            }
        }
        return validLocations;
    }


    public String moveHorizontallyRight(String startingSquare,int numOfTimes){
//       return moveHorizontally(startingSquare, numOfTimes,1);
        splitStartingSquare(startingSquare);
        letterIndex = letterIndex +numOfTimes;
        String location = "";
        return newLocation();
    }

    public String moveHorizontallyLeft(String startingSquare, int numOfTimes){
//       return moveHorizontally(startingSquare, numOfTimes,-1);
        splitStartingSquare(startingSquare);
        letterIndex = letterIndex - numOfTimes;
        String location = "";
        if(letterIndex>=leftBoundry){
            location= newLocation();
        }
        else{
            location = null;
        }
        return location;
    }

    public String moveHorizontally(String startingSquare, int numOfTimes, int direction){
        splitStartingSquare(startingSquare);
        letterIndex = letterIndex + (numOfTimes*direction);
        return newLocation();

    }

    public String moveVerticallyUp(String startingSquare, int numOfTimes){
        return moveVertically(startingSquare, numOfTimes, 1);
    }

    public String moveVerticallyDown(String startingSquare, int numOfTimes){
        return moveVertically(startingSquare, numOfTimes, -1);

    }


    public String moveVertically(String startingSquare, int numOfTimes,int direction){
        splitStartingSquare(startingSquare);

        numberIndex = numberIndex + (numOfTimes * direction);
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

    private boolean isPieceSameColor(String loc){
        return(board.getPiece(loc).getColor().equals(this.color));
    }

    // <editor-fold desc="Individual check directions methods">
    private void checkDiagonalUpperLeft(String startingSquare){
        splitStartingSquare(startingSquare);
        int maxSquares = getMaxSquareDiagonalUpLeft();

        boolean keepChecking = true;

        for(int i = 1; i<=maxSquares && keepChecking; i++){
            String loc = moveDiagonalUpperLeft(startingSquare,i);
            if(!board.isSquareEmpty((loc))){
                keepChecking = false;
                if (!isPieceSameColor(loc)){
                    validMoves.add(loc);
                }

            }
            else{
                validMoves.add(loc);
                keepChecking = true;
            }
        }
    }

    private void checkDiagonalUpperRight(String startingSquare){
        splitStartingSquare(startingSquare);

        boolean keepChecking = true;
        int maxSquare = getMaxSquareDiagonalUpRight();
        for(int i = 1; i<=maxSquare && keepChecking;i++){
            String loc = moveDiagonalUpperRight(startingSquare,i);
            if(!board.isSquareEmpty((loc))){
                keepChecking = false;
                if (!isPieceSameColor(loc)){
                    validMoves.add(loc);
                }

            }
            else{
                validMoves.add(loc);
                keepChecking = true;
            }
        }
    }

    private void checkDiagonalLowerLeft(String startingSquare){
        splitStartingSquare(startingSquare);
        boolean keepChecking = true;
        int maxSquares = getMaxSquareDiagonalLowLeft();

        for(int i = 1; i<=maxSquares && keepChecking;i++){
            String loc = moveDiagonalLowerLeft(startingSquare,i);
            if(!board.isSquareEmpty((loc))){
                keepChecking = false;
                if (!isPieceSameColor(loc)){
                    validMoves.add(loc);
                }

            }
            else{
                validMoves.add(loc);
                keepChecking = true;
            }
        }
    }

    private void checkDiagonalLowerRight(String startingSquare){
        splitStartingSquare(startingSquare);

        boolean keepChecking = true;
        int maxSquares = getMaxSquareDiagonalLowRight();
        for(int i = 1; i<=maxSquares && keepChecking;i++){
            String loc = moveDiagonalLowerRight(startingSquare,i);
            if(!board.isSquareEmpty((loc))){
                keepChecking = false;
                if (!isPieceSameColor(loc)){
                    validMoves.add(loc);
                }

            }
            else{
                validMoves.add(loc);
                keepChecking = true;
            }
        }
    }

    private void checkHorizontalRight(String startingSquare){
        splitStartingSquare(startingSquare);
        setMaxes();

        boolean keepChecking = true;
        for(int i = 1; i<=maxLeftRight && keepChecking;i++){
            String loc = moveHorizontallyRight(startingSquare,i);
            if(!board.isSquareEmpty((loc))){
                keepChecking = false;
                if (!isPieceSameColor(loc)){
                    validMoves.add(loc);
                }

            }
            else{
                validMoves.add(loc);
                keepChecking = true;
            }
        }
    }

    private void checkHorizontalLeft(String startingSquare){
        splitStartingSquare(startingSquare);
        setMaxes();

        boolean keepChecking = true;
        for(int i = 1; i<maxLeftRight1 && keepChecking;i++){
            String loc = moveHorizontallyLeft(startingSquare,i);
            if(!board.isSquareEmpty((loc))){
                keepChecking = false;
                if (!isPieceSameColor(loc)){
                    validMoves.add(loc);
                }

            }
            else{
                validMoves.add(loc);
                keepChecking = true;
            }
        }
    }

    private void checkVerticalUp(String startingSquare){
        splitStartingSquare(startingSquare);
        setMaxes();

        boolean keepChecking = true;
        for(int i = 1; i<=maxUpDown && keepChecking;i++){
            String loc = moveVerticallyUp(startingSquare,i);
            if(!board.isSquareEmpty((loc))){
                keepChecking = false;
                if (!isPieceSameColor(loc)){
                    validMoves.add(loc);
                }

            }
            else{
                validMoves.add(loc);
                keepChecking = true;
            }
        }
    }

    private void checkVerticalDown(String startingSquare){
        splitStartingSquare(startingSquare);
        setMaxes();

        boolean keepChecking = true;
        for(int i = 1; i<maxUpDown1 && keepChecking;i++){
            String loc = moveVerticallyDown(startingSquare,i);
            if(!board.isSquareEmpty((loc))){
                keepChecking = false;
                if (!isPieceSameColor(loc)){
                    validMoves.add(loc);
                }

            }
            else{
                validMoves.add(loc);
                keepChecking = true;
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
        boolean keepGoing  = (numberIndex >bottomBoundry && letterIndex >leftBoundry);
        while(keepGoing){
            numberIndex = numberIndex -1;
            letterIndex = letterIndex -1;
            if(numberIndex >bottomBoundry && letterIndex >leftBoundry)  {
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

    protected void checkIfInBounds(String location){
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

    // <editor-fold desc="Getters and Setters">

    public String getPiece() {
        return piece;
    }

    public void setPiece(String piece) {
        this.piece = piece;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    //</editor-fold>
}
