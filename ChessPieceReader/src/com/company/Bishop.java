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
public class Bishop extends Piece {

    private ImageIcon lightBishop, darkBishop;

    public Bishop(Game game, String color){
        super(game, color);
        piece = "B";
        lightBishop = new ImageIcon("/Users/Rachel/Chess/ChessPieceReader/White Pieces/WhiteBishop.png");
        darkBishop = new ImageIcon("/Users/Rachel/Chess/ChessPieceReader/Dark Pieces/DarkBishop.png");
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

    public void populateValidMoves(Location firstLocation) {
        validMoves = new ArrayList<Location>();
        checkDiagonals(firstLocation);
//        System.out.println("Bishop Valid Moves: "+validMoves+" Bishop: "+this.toString() + "Color: "+color);
    }

    @Override
    public ImageIcon getLightImage() {
        return lightBishop;
    }

    @Override
    public ImageIcon getDarkImage() {
        return darkBishop;
    }
}
