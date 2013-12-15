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
public class Knight extends Piece {

    private ImageIcon lightKnight, darkKnight;

    public Knight(Game game, String color){
        super(game, color);
        piece = "N";
        lightKnight = new ImageIcon("/Users/Rachel/Chess/ChessPieceReader/White Pieces/WhiteKnight.png");
        darkKnight = new ImageIcon("/Users/Rachel/Chess/ChessPieceReader/Dark Pieces/DarkKnight.png");
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
        checkKnight(firstLocation);
    }

    @Override
    public ImageIcon getLightImage() {
        return lightKnight;
    }

    @Override
    public ImageIcon getDarkImage() {
        return darkKnight;
    }


}
