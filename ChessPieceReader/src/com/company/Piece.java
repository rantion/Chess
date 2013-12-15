package com.company;

import javax.swing.*;
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
    protected ArrayList<Location> validMoves = new ArrayList();
    protected Board board;
    protected Game game;
    protected boolean hasMoved;


    public boolean getHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public Piece(Game game,String color){
        this.game = game;
        this.board = game.getGameBoard();
        this.color = color;
    }

    public void move(Location oldLocation,Location newLocation, String piece){
        if(board.isSquareEmpty(newLocation) || !isPieceSameColor(newLocation)){
            board.removePiece(oldLocation);
            if(board.getPiece(newLocation)!=null){
                board.removePiece(newLocation);
            }
            board.placePiece(newLocation, this, piece);
        }

    }

    public void unDoMove(Location newLocation, Location oldLocation, String piece){
        board.removePiece(newLocation);
        board.placePiece(oldLocation, this, piece);
    }

    public abstract boolean isLegalMove (Location firstLocation, Location secondLocation);

    public abstract void populateValidMoves(Location firstLocation);

    public abstract ImageIcon getLightImage();
    public abstract ImageIcon getDarkImage();


    public void checkKnight(Location firstLocation){
        ArrayList<Location> knightMoveLocations = new ArrayList<Location>();

        Location leftTwoUpOne = moveVerticallyUp(moveHorizontallyLeft(firstLocation,2),1);
        Location rightTwoUpOne = moveVerticallyUp(moveHorizontallyRight(firstLocation,2),1);

        Location leftOneUpTwo = moveVerticallyUp(moveHorizontallyLeft(firstLocation,1),2);
        Location rightOneUpTwo = moveVerticallyUp(moveHorizontallyRight(firstLocation,1),2);

        Location leftTwoDownOne = moveVerticallyDown(moveHorizontallyLeft(firstLocation,2),1);
        Location rightTwoDownOne = moveVerticallyDown(moveHorizontallyRight(firstLocation,2),1);

        Location leftOneDownTwo = moveVerticallyDown(moveHorizontallyLeft(firstLocation,1),2);
        Location rightOneDownTwo =  moveVerticallyDown(moveHorizontallyRight(firstLocation,1),2);

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

    public boolean inBounds(Location location){
        splitStartingSquare(location);
        boolean inBounds = (letterIndex>=leftBoundry && letterIndex<=rightBoundry && numberIndex>= bottomBoundry && numberIndex<=topBoundry);
        return inBounds;
    }

    public ArrayList<Location> addValidLocations(ArrayList<Location> locationList){
        ArrayList<Location> validLocations = new ArrayList<Location>();
        for(Location location: locationList){
            splitStartingSquare(location);
            if(inBounds(location)){
                if(!board.isSquareEmpty(location)) {
                    if (!isPieceSameColor(location)){
                    validLocations.add(location);
                    }
                }
                else{
                    validLocations.add(location);
                }

            }
        }
        return validLocations;
    }


    public Location moveHorizontallyRight(Location startingSquare,int numOfTimes){
//       return moveHorizontally(startingSquare, numOfTimes,1);
        splitStartingSquare(startingSquare);
        letterIndex = letterIndex +numOfTimes;
        String location = "";
        return newLocation();
    }

    public Location moveHorizontallyLeft(Location startingSquare, int numOfTimes){
//       return moveHorizontally(startingSquare, numOfTimes,-1);
        splitStartingSquare(startingSquare);
        letterIndex = letterIndex - numOfTimes;
        Location location = null;
        if(letterIndex>=leftBoundry){
            location= newLocation();
        }
        else{
            location = null;
        }
        return location;
    }

    public Location moveHorizontally(Location startingSquare, int numOfTimes, int direction){
        splitStartingSquare(startingSquare);
        letterIndex = letterIndex + (numOfTimes*direction);
        return newLocation();

    }

    public Location moveVerticallyUp(Location startingSquare, int numOfTimes){
        return moveVertically(startingSquare, numOfTimes, 1);
    }

    public Location moveVerticallyDown(Location startingSquare, int numOfTimes){
        return moveVertically(startingSquare, numOfTimes, -1);

    }


    public Location moveVertically(Location startingSquare, int numOfTimes,int direction){
        splitStartingSquare(startingSquare);

        numberIndex = numberIndex + (numOfTimes * direction);
        return newLocation();
    }

    public Location moveDiagonalUpperLeft(Location startingSquare, int numOfTimes){
        Location tempSquare = moveHorizontallyLeft(startingSquare,numOfTimes);
        return moveVerticallyUp(tempSquare, numOfTimes);

    }

    public Location moveDiagonalUpperRight(Location startingSquare, int numOfTimes){
        Location tempSquare = moveHorizontallyRight(startingSquare, numOfTimes);
        return moveVerticallyUp(tempSquare, numOfTimes);
    }

    public Location moveDiagonalLowerLeft(Location startingSquare, int numOfTimes){
        Location tempSquare = moveHorizontallyLeft(startingSquare, numOfTimes);
        return moveVerticallyDown(tempSquare, numOfTimes);
    }

    public Location moveDiagonalLowerRight(Location startingSquare, int numOfTimes){
        Location tempSquare = moveHorizontallyRight(startingSquare, numOfTimes);
        return moveVerticallyDown(tempSquare, numOfTimes);
    }

    // </editor-fold>

    // <editor-fold desc="check directions methods">

    public void checkDiagonals(Location startingSquare){
        checkDiagonalUpperLeft(startingSquare);
        checkDiagonalUpperRight(startingSquare);
        checkDiagonalLowerLeft(startingSquare);
        checkDiagonalLowerRight(startingSquare);
    }

    public void checkHorizontals(Location startingSquare){
        checkHorizontalRight(startingSquare);
        checkHorizontalLeft(startingSquare);
    }

    public void checkVerticals(Location startingSquare){
        checkVerticalUp(startingSquare);
        checkVerticalDown(startingSquare);
    }

    //</editor-fold>

    protected boolean isPieceSameColor(Location loc){
        return(board.getPiece(loc).getColor().equals(this.color));
    }

    // <editor-fold desc="Individual check directions methods">
    private void checkDiagonalUpperLeft(Location startingSquare){
        splitStartingSquare(startingSquare);
        int maxSquares = getMaxSquareDiagonalUpLeft();

        boolean keepChecking = true;

        for(int i = 1; i<=maxSquares && keepChecking; i++){
            Location loc = moveDiagonalUpperLeft(startingSquare,i);
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

    private void checkDiagonalUpperRight(Location startingSquare){
        splitStartingSquare(startingSquare);

        boolean keepChecking = true;
        int maxSquare = getMaxSquareDiagonalUpRight();
        for(int i = 1; i<=maxSquare && keepChecking;i++){
            Location loc = moveDiagonalUpperRight(startingSquare,i);
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

    private void checkDiagonalLowerLeft(Location startingSquare){
        splitStartingSquare(startingSquare);
        boolean keepChecking = true;
        int maxSquares = getMaxSquareDiagonalLowLeft();

        for(int i = 1; i<=maxSquares && keepChecking;i++){
           Location loc = moveDiagonalLowerLeft(startingSquare,i);
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

    private void checkDiagonalLowerRight(Location startingSquare){
        splitStartingSquare(startingSquare);

        boolean keepChecking = true;
        int maxSquares = getMaxSquareDiagonalLowRight();
        for(int i = 1; i<=maxSquares && keepChecking;i++){
            Location loc = moveDiagonalLowerRight(startingSquare,i);
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

    private void checkHorizontalRight(Location startingSquare){
        splitStartingSquare(startingSquare);
        setMaxes();

        boolean keepChecking = true;
        for(int i = 1; i<=maxLeftRight && keepChecking;i++){
            Location loc = moveHorizontallyRight(startingSquare,i);
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

    private void checkHorizontalLeft(Location startingSquare){
        splitStartingSquare(startingSquare);
        setMaxes();

        boolean keepChecking = true;
        for(int i = 1; i<maxLeftRight1 && keepChecking;i++){
            Location loc = moveHorizontallyLeft(startingSquare,i);
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

    private void checkVerticalUp(Location startingSquare){
        splitStartingSquare(startingSquare);
        setMaxes();

        boolean keepChecking = true;
        for(int i = 1; i<=maxUpDown && keepChecking;i++){
            Location loc = moveVerticallyUp(startingSquare,i);
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

    private void checkVerticalDown(Location startingSquare){
        splitStartingSquare(startingSquare);
        setMaxes();

        boolean keepChecking = true;
        for(int i = 1; i<maxUpDown1 && keepChecking;i++){
            Location loc = moveVerticallyDown(startingSquare,i);
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

    private void splitStartingSquare(Location startingSquare){
        if(startingSquare != null){
        char[] locations =startingSquare.getLocation().toCharArray();
        letterIndex = locations[0];
        numberIndex = locations[1];
        }
    }

    private void notInBounds(){
        System.out.println("You are trying to move to a square that does not exist in the scope of a chessboard");
    }

    protected void checkIfInBounds(Location location){
        splitStartingSquare(location);
        if(letterIndex>=leftBoundry && letterIndex<=rightBoundry && numberIndex<=topBoundry && numberIndex>=bottomBoundry){
            newLocation();
        }
    }

    private Location newLocation(){
        newLetterChar =  (char)letterIndex;
        String newLetter = Character.toString(newLetterChar);
        newNumChar = (char)numberIndex;
        String newNumber = Character.toString(newNumChar);
       Location newLocation = new Location(newLetter+newNumber);
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

    public ArrayList<Location> getValidMoves() {
        return validMoves;
    }

    public void setValidMoves(ArrayList<Location> validMoves) {
        this.validMoves = validMoves;
    }
//</editor-fold>
}
