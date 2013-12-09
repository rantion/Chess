package com.company;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Rachel
 * Date: 11/18/13
 * Time: 9:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class Queen extends Piece {

    public Queen(Game game, String color){
        super(game, color);
        piece = "Q";
        if(color.equals("L")){
            piece = piece.toLowerCase();
        }
    }

    public void checkQueen(String firstLocation){
        checkDiagonals(firstLocation);
        checkHorizontals(firstLocation);
        checkVerticals(firstLocation);
    }

    public boolean isLegalMove(String firstLocation, String secondLocation){
        boolean validMove = false;
           checkQueen(firstLocation);
//
//        System.out.println("ValidMoves: "+validMoves);
        for(String location:validMoves){
            if(location.equals(secondLocation)){
                validMove = true;
            }
        }

            validMoves = new ArrayList<String>();
//            move(firstLocation,secondLocation,piece);

        return validMove;
    }







}
