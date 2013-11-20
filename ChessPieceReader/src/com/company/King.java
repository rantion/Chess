package com.company;

/**
 * Created with IntelliJ IDEA.
 * User: Rachel
 * Date: 11/18/13
 * Time: 9:31 AM
 * To change this template use File | Settings | File Templates.
 */
public class King extends Piece {

    public King(){
    }

    public void checkMove(String firstLocation, String secondLocation){
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
           move(secondLocation);
       }




    }



}
