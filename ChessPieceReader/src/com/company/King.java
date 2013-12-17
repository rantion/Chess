package com.company;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Rachel
 * Date: 11/18/13
 * Time: 9:31 AM
 * To change this template use File | Settings | File Templates.
 */
public class King extends Piece {
    private HashMap<Location,Piece> piecesPuttingKingInCheck;
    private ArrayList<Location> allPossibleAttackSquares = new ArrayList<Location>();
    private ImageIcon lightKing, darkKing;

    public King(Game game, String color){
        super(game, color);
        piece = "K";
        lightKing = new ImageIcon("/Users/Rachel/Chess/ChessPieceReader/White Pieces/whiteKing.png");
        darkKing = new ImageIcon("/Users/Rachel/Chess/ChessPieceReader/Dark Pieces/DarkKing.png");
        if(color.equals("L")){
            piece = piece.toLowerCase();
        }
    }

    public boolean isLegalMove(Location firstLocation, Location secondLocation){

        boolean validMove = false;
        populateValidMoves(firstLocation);
        for(Location location:validMoves){
            if(location.getLocation().equals(secondLocation.getLocation())&&!determineIfInCheck(location)){
                validMove = true;
            }
        }
        return validMove;
    }

    public void populateValidMoves(Location firstLocation) {
        validMoves = new ArrayList<Location>();
        ArrayList<Location> kingLocations = new ArrayList<Location>();

        kingLocations.add(moveDiagonalUpperLeft(firstLocation,1));
        kingLocations.add(moveDiagonalLowerLeft(firstLocation,1));
        kingLocations.add(moveDiagonalLowerRight(firstLocation,1));
        kingLocations.add(moveDiagonalUpperRight(firstLocation,1));
        kingLocations.add(moveHorizontallyLeft(firstLocation,1));
        kingLocations.add(moveHorizontallyRight(firstLocation,1));
        kingLocations.add(moveVerticallyUp(firstLocation,1));
        kingLocations.add(moveVerticallyDown(firstLocation,1));

        validMoves = addValidLocations(kingLocations);
    }

    @Override
    public ImageIcon getLightImage() {
        return lightKing;
    }

    @Override
    public ImageIcon getDarkImage() {
        return darkKing;
    }


    public boolean determineIfInCheck(Location location){
        boolean inCheck = false;
        Players players = game.getPlayers();
        if(this.color.equals("L")){
            Team dark = players.getDarkLeader().getTeam();
            for(Piece piece: dark.getTeamPieces().keySet()){
                Location pieceLocation = dark.getTeamPieces().get(piece);
                if(!dark.getTeamPieces().get(piece).getLocation().equals("captured")){
                    if(piece.isLegalMove(pieceLocation,location)){
                        inCheck = true;

                    }
                }
            }
        }
        if(this.color.equals("D")){
            Team light = players.getLightLeader().getTeam();
            for(Piece piece: light.getTeamPieces().keySet()){
                if(!light.getTeamPieces().get(piece).getLocation().equals("captured")){
                    if(piece.isLegalMove(light.getTeamPieces().get(piece),location)){
                        inCheck = true;
                    }
                }
            }
        }
        return inCheck;
    }

    private ArrayList<Location> getPawnSquares(Location location){
        ArrayList<Location> pawnSquares = new ArrayList<Location>();
        if(color.equals("L")){
            pawnSquares.add(moveDiagonalUpperLeft(location,1));
            pawnSquares.add(moveDiagonalUpperRight(location,1));
        }
        else if(color.equals("D")){
            pawnSquares.add(moveDiagonalLowerLeft(location,1));
            pawnSquares.add(moveDiagonalLowerRight(location,1));
        }
        return pawnSquares;
    }

    public ArrayList<Location> checkHorizontalVerticals(Location location){
        validMoves = new ArrayList<Location>();
        checkHorizontals(location);
        checkVerticals(location);
        return validMoves;
    }

    public ArrayList<Location>checkDiagonalMoves(Location location){
        validMoves = new ArrayList<Location>();
        checkDiagonals(location);
        return validMoves;
    }

    public ArrayList<Location>checkForKnightMoves(Location location){
        validMoves = new ArrayList<Location>();
        checkKnight(location);
        return validMoves;
    }

    public boolean canMoveOutOfCheck(Location location){
        boolean canMove = false;
        Location originalLocation = location;
        populateValidMoves(originalLocation);
        King kingInCheck = (King)board.getPiece(originalLocation);
        for(Location loc:validMoves){
            kingInCheck.move(originalLocation,loc,piece);
            if(!kingInCheck.determineIfInCheck(loc)){
                canMove = true;
            }
            kingInCheck.unDoMove(loc,originalLocation,piece);
        }
        return canMove;
    }

    public void populateAllPossibleAttackSquares(Location location){
        ArrayList<Location>HorizontalVerticalMoves = checkHorizontalVerticals(location);
        ArrayList<Location>DiagonalMoves = checkDiagonalMoves(location);
        ArrayList<Location>KnightMoves = checkForKnightMoves(location);

        for(Location sqrLocation:HorizontalVerticalMoves){
            allPossibleAttackSquares.add(sqrLocation);
        }
        for(Location sqrLocation:DiagonalMoves){
            allPossibleAttackSquares.add(sqrLocation);
        }
        for(Location sqrLocation:KnightMoves){
            allPossibleAttackSquares.add(sqrLocation);
        }
    }

    public ArrayList<Location> getAllPossibleAttackSquares(Location kingLocation) {
        populateAllPossibleAttackSquares(kingLocation);
        return allPossibleAttackSquares;
    }
}
