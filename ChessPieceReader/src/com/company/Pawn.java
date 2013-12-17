package com.company;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Rachel
 * Date: 11/18/13
 * Time: 9:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class Pawn extends Piece {

    private ImageIcon lightPawn, darkPawn;

    public Pawn(Game game, String color){
        super (game, color);
        piece = "P";
        lightPawn = new ImageIcon("/Users/Rachel/Chess/ChessPieceReader/White Pieces/whitePawn.png");
        darkPawn = new ImageIcon("/Users/Rachel/Chess/ChessPieceReader/Dark Pieces/DarkPawn.png");
        hasMoved = false;
        if(color.equals("L")){
            piece = piece.toLowerCase();
        }
    }

    public boolean isLegalMove(Location firstLocation, Location secondLocation){

        boolean validMove = false;
        populateValidMoves(firstLocation);
        for(Location location:validMoves){
            if(location.getLocation().equals(secondLocation.getLocation())){
                validMove = true;
            }
        }
        return validMove;
    }

    @Override
    public void populateValidMoves(Location firstLocation) {
        validMoves = new ArrayList<Location>();
        checkPawn(firstLocation);
    }

    @Override
    public ImageIcon getLightImage() {
        return lightPawn;
    }

    @Override
    public ImageIcon getDarkImage() {
        return darkPawn;
    }

    public void pawnCapture(Location firstLocation,Location secondLocation){
        if(color.equals("L")){
            if(secondLocation.equals(moveDiagonalUpperLeft(firstLocation,1)) || secondLocation.equals(moveDiagonalUpperRight(firstLocation,1))){
                captureIfPiece(firstLocation, secondLocation);
            }
        }
        if(color.equals("D")){
            if(secondLocation.equals(moveDiagonalLowerLeft(firstLocation,1)) || secondLocation.equals(moveDiagonalLowerRight(firstLocation,1))){
                captureIfPiece(firstLocation, secondLocation);
            }
        }
    }
    //captures if there is a piece in the second location and it is an opposing piece
    private void captureIfPiece(Location firstLocation, Location secondLocation){
        if(board.getPiece(secondLocation) != null && (board.getPiece(secondLocation).getColor()!= color)){
            if(color.equals("L")){
                piece = piece.toLowerCase();
            }
            move(firstLocation,secondLocation,piece);
        }
    }

    private void addPawnCaptureSquares(Location location){
        ArrayList<Location>possibleCaptureLocations = new ArrayList<Location>();
        int squaresCanMove = 1;

        if(color.equals("L")){
            if(inBounds(moveDiagonalUpperLeft(location,squaresCanMove))){
                possibleCaptureLocations.add(moveDiagonalUpperLeft(location,squaresCanMove));
            }
            if(inBounds(moveDiagonalUpperRight(location,squaresCanMove))) {
                possibleCaptureLocations.add(moveDiagonalUpperRight(location,squaresCanMove));
            }
        }
        if(color.equals("D")){
            if(inBounds(moveDiagonalLowerLeft(location,squaresCanMove))) {
                possibleCaptureLocations.add(moveDiagonalLowerLeft(location,squaresCanMove));
            }
            if(inBounds(moveDiagonalLowerRight(location,squaresCanMove))){
                possibleCaptureLocations.add(moveDiagonalLowerRight(location,squaresCanMove));
            }
        }
        for(Location loc : possibleCaptureLocations){
            if(!board.isSquareEmpty((loc))){
                if (!isPieceSameColor(loc)){
                    validMoves.add(loc);
                }
            }
        }
    }

    public boolean doesPawnNeedToBePromoted(Location location){
        boolean needsToBePromoted = false;
        splitStartingSquare(location);
        if(color.equals("L")){
            if(numberIndex==topBoundry){
                needsToBePromoted = true;
            }
        }
        if(color.equals("D")){
            if(numberIndex == bottomBoundry){
                needsToBePromoted = true;
            }

        }
        return needsToBePromoted;
    }

    private void checkPawn(Location firstLocation){
        boolean keepChecking = true;

        int numSquares =2;
        if(hasMoved){
            numSquares = 1;
        }
        int numOfSquaresCanMove = numSquares;

        for(int i = 0; i<numOfSquaresCanMove && keepChecking; i++){
            if(color.equals("L")){
                Location tempLoc = moveVerticallyUp(firstLocation, i+1) ;
                if(inBounds(tempLoc)){
                    if(board.isSquareEmpty(tempLoc)) {
                        validMoves.add(tempLoc);

                    }
                    else{
                        keepChecking = false;
                    }
                }
            }

            if(color.equals("D")){
                Location tempLoc = moveVerticallyDown(firstLocation,i+1);
                if(inBounds(tempLoc)){
                    if(board.isSquareEmpty(tempLoc)) {
                        validMoves.add(tempLoc);

                    }
                    else{
                        keepChecking = false;
                    }
                }
            }
        }
        addPawnCaptureSquares(firstLocation);
    }

}

