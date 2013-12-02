package com.company;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Rachel
 * Date: 11/18/13
 * Time: 9:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class Knight extends Piece {

    public Knight(Game game, String color){
        super(game, color);
        piece = "N";
    }

    public void checkMove(String firstLocation, String secondLocation){
         boolean validMove = false;
            checkKnight(firstLocation);

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

//    public void checkKnight(String firstLocation){
//        if(board.checkIfSquareEmpty(moveVerticallyUp(moveHorizontallyLeft(firstLocation,2),1),this.color)){
//            validMoves.add(moveVerticallyUp(moveHorizontallyLeft(firstLocation,2),1));
//        }
//        if(board.checkIfSquareEmpty(moveVerticallyDown(moveHorizontallyLeft(firstLocation,2),1),this.color)){
//            validMoves.add(moveVerticallyDown(moveHorizontallyLeft(firstLocation,2),1));
//        }
//        if(board.checkIfSquareEmpty(moveVerticallyUp(moveHorizontallyRight(firstLocation,2),1),this.color)){
//            validMoves.add(moveVerticallyUp(moveHorizontallyRight(firstLocation,2),1));
//        }
//        if(board.checkIfSquareEmpty(moveVerticallyDown(moveHorizontallyRight(firstLocation,2),1),this.color)){
//            validMoves.add(moveVerticallyDown(moveHorizontallyRight(firstLocation,2),1));
//        }
//        if(board.checkIfSquareEmpty(moveVerticallyUp(moveHorizontallyLeft(firstLocation,1),2),this.color)){
//            validMoves.add(moveVerticallyUp(moveHorizontallyLeft(firstLocation,1),2));
//        }
//        if(board.checkIfSquareEmpty(moveVerticallyDown(moveHorizontallyLeft(firstLocation,1),2),this.color)){
//            validMoves.add(moveVerticallyDown(moveHorizontallyLeft(firstLocation,1),2));
//        }
//        if(board.checkIfSquareEmpty(moveVerticallyUp(moveHorizontallyRight(firstLocation,1),2),this.color)){
//            validMoves.add(moveVerticallyUp(moveHorizontallyRight(firstLocation,1),2));
//        }
//        if(board.checkIfSquareEmpty(moveVerticallyDown(moveHorizontallyRight(firstLocation,1),2),this.color)){
//            validMoves.add(moveVerticallyDown(moveHorizontallyRight(firstLocation,1),2));
//        }
//
//
//    }
}
