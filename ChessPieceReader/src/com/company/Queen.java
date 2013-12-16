package com.company;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Rachel
 * Date: 11/18/13
 * Time: 9:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class Queen extends Piece {
    private ImageIcon lightQueen, darkQueen;

    public Queen(Game game, String color){
        super(game, color);
        piece = "Q";
        lightQueen = new ImageIcon("/Users/Rachel/Chess/ChessPieceReader/White Pieces/WhiteQueen.png");
        darkQueen = new ImageIcon("/Users/Rachel/Chess/ChessPieceReader/Dark Pieces/DarkQueen.png");
        if(color.equals("L")){
            piece = piece.toLowerCase();
        }
    }


    public boolean isLegalMove(Location firstLocation, Location secondLocation){
        boolean validMove = false;
           populateValidMoves(firstLocation);
        for(Location location:validMoves){
            if(location.getLocation().equals(secondLocation.getLocation())){
                validMove = true;
            }
        }
        return validMove;
    }

    @Override
    public void populateValidMoves(Location firstLocation) {
        validMoves = new ArrayList<Location>();
        checkDiagonals(firstLocation);
        checkHorizontals(firstLocation);
        checkVerticals(firstLocation);
    }

    @Override
    public ImageIcon getLightImage() {
        return lightQueen;
    }

    @Override
    public ImageIcon getDarkImage() {
        return darkQueen;
    }


}
