package com.company;

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
    private HashMap<String,Piece> piecesPuttingKingInCheck;
    private ArrayList<String> allPossibleAttackSquares = new ArrayList<String>();

    public King(Game game, String color){
        super(game, color);
        piece = "K";
        if(color.equals("L")){
            piece = piece.toLowerCase();
        }
    }

    public boolean isLegalMove(String firstLocation, String secondLocation){

        boolean validMove = false;
        populateValidMoves(firstLocation);
        for(String location:validMoves){
            if(location.equals(secondLocation)){
                validMove = true;
            }
        }
        return validMove;
    }

    public void populateValidMoves(String firstLocation) {
        validMoves = new ArrayList<String>();
        ArrayList<String> kingLocations = new ArrayList<String>();

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


    public boolean determineIfInCheck(String location){
        boolean inCheck = false;
        Players players = game.getPlayers();
        if(this.color.equals("L")){
            Team dark = players.getDarkLeader().getTeam();

            for(Piece piece: dark.getTeamPieces().keySet()){
                if(dark.getTeamPieces().get(piece) != "captured"){
                    if(piece.isLegalMove(dark.getTeamPieces().get(piece),location)){
                        inCheck = true;

                    }
                }
            }

        }
        if(this.color.equals("D")){
            Team light = players.getLightLeader().getTeam();
            for(Piece piece: light.getTeamPieces().keySet()){
                if(light.getTeamPieces().get(piece) != "captured") {
                    if(piece.isLegalMove(light.getTeamPieces().get(piece),location)){
                        inCheck = true;

                    }
                }
            }

        }

        return inCheck;
    }

    private ArrayList<String> getPawnSquares(String location){
        ArrayList<String> pawnSquares = new ArrayList<String>();
        if(color.equals("L")){
            pawnSquares.add(moveDiagonalUpperLeft(location,1));
            pawnSquares.add(moveDiagonalUpperRight(location,1));
        }
        else if(color.equals("D")){
            pawnSquares.add(moveDiagonalLowerLeft(location,1));
            pawnSquares.add(moveDiagonalLowerRight(location,1));
        }
        System.out.println("pawnSquares: "+pawnSquares);
        return pawnSquares;
    }

    public ArrayList<String> checkHorizontalVerticals(String location){
        validMoves = new ArrayList<String>();
        checkHorizontals(location);
        checkVerticals(location);
        return validMoves;
    }

    public ArrayList<String>checkDiagonalMoves(String location){
        validMoves = new ArrayList<String>();
        checkDiagonals(location);
        return validMoves;
    }

    public ArrayList<String>checkForKnightMoves(String location){
        validMoves = new ArrayList<String>();
        checkKnight(location);
        return validMoves;
    }

    public boolean canMoveOutOfCheck(String location){
        boolean canMove = false;
        String originalLocation = location;
        populateValidMoves(originalLocation);
        King kingInCheck = (King)board.getPiece(originalLocation);
        for(String loc:validMoves){
            kingInCheck.move(originalLocation,loc,piece);
            if(!kingInCheck.determineIfInCheck(loc)){
                canMove = true;
            }
            kingInCheck.unDoMove(loc,originalLocation,piece);

        }
        return canMove;
    }

    public void populateAllPossibleAttackSquares(String location){
        piecesPuttingKingInCheck = new HashMap<String, Piece>();
        ArrayList<String>HorizontalVerticalMoves = checkHorizontalVerticals(location);
        ArrayList<String>DiagonalMoves = checkDiagonalMoves(location);
        ArrayList<String>KnightMoves = checkForKnightMoves(location);

        for(String sqrLocation:HorizontalVerticalMoves){
            allPossibleAttackSquares.add(sqrLocation);
            Piece checkPiece = board.getPiece(sqrLocation);
            populateValidMoves(location);
            if(checkPiece != null){
                if(checkPiece instanceof King && validMoves.contains(sqrLocation)){
                    piecesPuttingKingInCheck.put(sqrLocation, checkPiece);
                }
                if(checkPiece instanceof Queen || checkPiece instanceof Rook){
                    piecesPuttingKingInCheck.put(sqrLocation, checkPiece);
                }
            }
        }

        for(String sqrLocation:DiagonalMoves){
            allPossibleAttackSquares.add(sqrLocation);
            Piece checkPiece = board.getPiece(sqrLocation);
            if(checkPiece != null){
                if(checkPiece instanceof King && validMoves.contains(sqrLocation)){
                    piecesPuttingKingInCheck.put(sqrLocation, checkPiece);
                }
                if(checkPiece instanceof Pawn && getPawnSquares(location).contains(sqrLocation))   {
                    piecesPuttingKingInCheck.put(sqrLocation, checkPiece);
                }
                if(checkPiece instanceof Queen || checkPiece instanceof Bishop){
                    piecesPuttingKingInCheck.put(sqrLocation, checkPiece);
                }
            }
        }

        for(String sqrLocation:KnightMoves){
            allPossibleAttackSquares.add(sqrLocation);
            Piece checkPiece = board.getPiece(sqrLocation);
            if(checkPiece != null){
                if(checkPiece instanceof Knight){
                    piecesPuttingKingInCheck.put(sqrLocation, checkPiece);
                }
            }
        }
    }

    public ArrayList<String> getAllPossibleAttackSquares() {
        return allPossibleAttackSquares;
    }

    public void setAllPossibleAttackSquares(ArrayList<String> allPossibleAttackSquares) {
        this.allPossibleAttackSquares = allPossibleAttackSquares;
    }
}
