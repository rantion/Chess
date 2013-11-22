package com.company;

/**
 * Created with IntelliJ IDEA.
 * User: Rachel
 * Date: 11/18/13
 * Time: 9:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class Queen extends Piece {

    public Queen(Board board, String color){
        super(board, color);
        piece = "Q";

    }

    public void checkMove(String firstLocation, String secondLocation){
        System.out.println("Inside CheckMove");
       boolean validMove = false;
        checkDiagonalUpperLeft(firstLocation);
        checkDiagonalUpperRight(firstLocation);
        checkDiagonalLowerLeft(firstLocation);
        checkDiagonalLowerRight(firstLocation);
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
            move(firstLocation,secondLocation,piece);
        }
    }







}
