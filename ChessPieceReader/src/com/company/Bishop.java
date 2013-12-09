package com.company;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Rachel
 * Date: 11/18/13
 * Time: 9:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class Bishop extends Piece {

    public Bishop(Game game, String color){
        super(game, color);
        piece = "B";
        if(color.equals("L")){
            piece = piece.toLowerCase();
        }
    }

    public boolean isLegalMove(String firstLocation, String secondLocation){
        boolean validMove = false;

        for(String location:validMoves){
            if(location.equals(secondLocation)){
                validMove = true;
            }
        }
        return validMove;
    }

    public void populateValidMoves(String firstLocation) {
        validMoves = new ArrayList<String>();
        checkDiagonals(firstLocation);
    }
}
