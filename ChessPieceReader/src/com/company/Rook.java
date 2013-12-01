package com.company;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Rachel
 * Date: 11/18/13
 * Time: 9:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class Rook extends Piece{

    public Rook(Game game, String color){
      super(game, color);
       piece = "R";
    }

    public void checkMove(String firstLocation, String secondLocation){
        boolean validMove = false;
        checkHorizontals(firstLocation);
        checkVerticals(firstLocation);
//
//        System.out.println("ValidMoves: "+validMoves);
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

}
