package com.company;

/**
 * Created with IntelliJ IDEA.
 * User: Rachel
 * Date: 11/18/13
 * Time: 9:31 AM
 * To change this template use File | Settings | File Templates.
 */
public class King extends Piece {

    private Board board;

    public King(Board board, String color){
       super(board, color);
        piece = "K";
    }

    public void checkMove(String firstLocation, String secondLocation){
        try{
        boolean validMove = (moveDiagonalLowerLeft(firstLocation,1).equals(secondLocation)||
                moveDiagonalUpperLeft(firstLocation,1).equals(secondLocation) ||
                moveDiagonalLowerRight(firstLocation,1).equals(secondLocation)||
                moveDiagonalUpperRight(firstLocation,1).equals(secondLocation)||
                moveHorizontallyLeft(firstLocation,1).equals(secondLocation)||
                moveHorizontallyRight(firstLocation,1).equals(secondLocation)||
                moveVerticallyUp(firstLocation,1).equals(secondLocation)||
                moveVerticallyDown(firstLocation,1).equals(secondLocation));

       if(!validMove) {
           notValid();
       }
        else {
           checkIfInBounds(secondLocation);
           if(color.equals("L")) {
               piece = piece.toLowerCase();
           }
           move(firstLocation,secondLocation,piece);
       }
        }
        catch (Exception e){
            System.out.println("Please enter two valid squares");
        }
    }



}
