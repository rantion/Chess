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
        hasMoved = false;
    }

    public void checkMove(String firstLocation, String secondLocation){

        boolean validMove = false;
        checkPawn(firstLocation);

        System.out.println("ValidMoves: "+validMoves);
        for(String location:validMoves){
            if(location.equals(secondLocation)){
                validMove = true;
            }
        }

        if(!validMove) {
            notValid();
        }
        else {
            checkIfInBounds(secondLocation);
            if(color.equals("L")){
                piece = piece.toLowerCase();
            }
            validMoves = new ArrayList<String>();
            hasMoved = true;
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
                if(board.checkIfSquareEmpty(moveVerticallyUp(firstLocation,numSquares),this.color)){
                    validMoves.add(moveVerticallyUp(firstLocation,numSquares));
                }
            }
            if(color.equals("D")){
                if(board.checkIfSquareEmpty(moveVerticallyDown(firstLocation,numSquares),this.color)){
                    validMoves.add(moveVerticallyDown(firstLocation,numSquares));
                }
            }
            numSquares --;
        }

    }
}
