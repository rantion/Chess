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
        if(color.equals("L")){
            piece = piece.toLowerCase();
        }
    }

    public boolean isLegalMove(String firstLocation, String secondLocation){
         boolean validMove = false;
            populateValidMoves(firstLocation);
            for(String location:validMoves){
                if(location.equals(secondLocation)){
                    validMove = true;
                }
            }
        return validMove;
    }

    @Override
    public void populateValidMoves(String firstLocation) {
        validMoves = new ArrayList<String>();
        checkKnight(firstLocation);
    }


}
