package com.company;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Rachel
 * Date: 11/18/13
 * Time: 9:31 AM
 * To change this template use File | Settings | File Templates.
 */
public class King extends Piece {

    public King(Game game, String color){
       super(game, color);
        piece = "K";
    }

    public void checkMove(String firstLocation, String secondLocation){

        boolean validMove = false;
         checkKing(firstLocation);

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
                move(firstLocation,secondLocation,piece);
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

}
