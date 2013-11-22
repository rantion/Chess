package com.company;

/**
 * Created with IntelliJ IDEA.
 * User: Rachel
 * Date: 11/18/13
 * Time: 9:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class Knight extends Piece {

    public Knight(Board board, String color){
        super(board, color);
        piece = "N";
    }

    public void checkMove(String firstLocation, String secondLocation){
        try{
        boolean validMove = (moveVerticallyUp(moveHorizontallyLeft(firstLocation,2),1).equals(secondLocation)||
        moveVerticallyDown(moveHorizontallyLeft(firstLocation,2),1).equals(secondLocation)||
        moveVerticallyUp(moveHorizontallyRight(firstLocation,2),1).equals(secondLocation)||
        moveVerticallyDown(moveHorizontallyRight(firstLocation,2),1).equals(secondLocation)||

        moveVerticallyUp(moveHorizontallyLeft(firstLocation,1),2).equals(secondLocation)||
        moveVerticallyDown(moveHorizontallyLeft(firstLocation,1),2).equals(secondLocation)||
        moveVerticallyUp(moveHorizontallyRight(firstLocation,1),2).equals(secondLocation)||
        moveVerticallyDown(moveHorizontallyRight(firstLocation,1),2).equals(secondLocation));

        if(!validMove) {
            notValid();
        }
        else {
            checkIfInBounds(secondLocation);
            System.out.println("Piece: "+piece);
            System.out.println("Color: "+color);
            if(color.equals("L")){
                System.out.println("In if statement");
                piece =piece.toLowerCase();
            }
            System.out.println("piece: "+piece);
            move(firstLocation,secondLocation,piece);
        }
    }
        catch (Exception e){
            System.out.println("Please enter two valid squares");
        }
    }
}
