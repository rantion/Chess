package com.company;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Rachel
 * Date: 11/18/13
 * Time: 9:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class Pawn extends Piece {
    private boolean hasMoved;

    public Pawn(Game game, String color){
        super (game, color);
        piece = "P";
        if(color.equals("L")){
            piece = piece.toLowerCase();
        }
        hasMoved = false;
    }

    public boolean isLegalMove(String firstLocation, String secondLocation){

        boolean validMove = false;
        populateValidMoves(firstLocation);
        for(String location:validMoves){
            if(location.equals(secondLocation)){
                validMove = true;
            }
        }
        hasMoved = true;
        return validMove;
    }

    @Override
    public void populateValidMoves(String firstLocation) {
        validMoves = new ArrayList<String>();
        checkPawn(firstLocation);
    }

    public void pawnCapture(String firstLocation, String secondLocation){
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
    private void captureIfPiece(String firstLocation, String secondLocation){
        if(board.getPiece(secondLocation) != null && (board.getPiece(secondLocation).getColor()!= color)){
            if(color.equals("L")){
                piece = piece.toLowerCase();
            }
            move(firstLocation,secondLocation,piece);
        }
    }

    private void addPawnCaptureSquares(String location){
        ArrayList<String>possibleCaptureLocations = new ArrayList<String>();
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
        for(String loc : possibleCaptureLocations){
            if(!board.isSquareEmpty((loc))){
                if (!isPieceSameColor(loc)){
                    validMoves.add(loc);
                }

            }

        }
    }

    private void checkPawn(String firstLocation){
        int numOfSquaresCanMove = 2;
        int numSquares =2;
        if(hasMoved){
            numSquares = 1;
        }

        for(int i = 0; i<numOfSquaresCanMove; i++){
            if(color.equals("L")){
                if(board.isSquareEmpty(moveVerticallyUp(firstLocation, numSquares))){
                    validMoves.add(moveVerticallyUp(firstLocation,numSquares));
                }
            }
            if(color.equals("D")){
                if(board.isSquareEmpty(moveVerticallyDown(firstLocation, numSquares))){
                    validMoves.add(moveVerticallyDown(firstLocation,numSquares));
                }
            }
            addPawnCaptureSquares(firstLocation);
            numSquares --;
        }

    }
}
