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
        checkPawn(firstLocation);

//        System.out.println("ValidMoves: "+validMoves);
        for(String location:validMoves){
            if(location.equals(secondLocation)){
                validMove = true;
            }
        }


            validMoves = new ArrayList<String>();
            hasMoved = true;
//            move(firstLocation,secondLocation,piece);

        return validMove;
    }

    public void pawnCapture(String firstLocation, String secondLocation){
//        System.out.println("In Pawn Capture");
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

    public void checkPawn(String firstLocation){
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
            numSquares --;
        }

    }
}
