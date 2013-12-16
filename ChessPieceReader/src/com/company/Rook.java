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
public class Rook extends Piece{

    private ImageIcon lightRook, darkRook;

    public Rook(Game game, String color){
        super(game, color);
        piece = "R";
        if(color.equals("L")){
            piece = piece.toLowerCase();
        }
        lightRook = new ImageIcon("/Users/Rachel/Chess/ChessPieceReader/White Pieces/WhiteRook.png");
        darkRook = new ImageIcon("/Users/Rachel/Chess/ChessPieceReader/Dark Pieces/DarkRook.png");
    }

    public boolean isLegalMove(Location firstLocation,Location secondLocation){
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
        checkHorizontals(firstLocation);
        checkVerticals(firstLocation);
    }

    @Override
    public ImageIcon getLightImage() {
        return lightRook;
    }

    @Override
    public ImageIcon getDarkImage() {
        return darkRook;
    }

}
