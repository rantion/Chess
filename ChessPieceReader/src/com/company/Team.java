package com.company;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Rachel
 * Date: 11/26/13
 * Time: 6:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class Team {
    private HashMap<Piece,Location> teamPieces;
    private String color;

    public Team(String color){
        this.color = color;
        teamPieces = new HashMap<Piece, Location>();
    }

    public HashMap<Piece, Location> getTeamPieces() {
        return teamPieces;
    }

    public void addPiecesToMap(Piece piece, Location location){
          teamPieces.put(piece,location);
    }

    public void setTeamPieces(HashMap<Piece, Location> teamPieces) {
        this.teamPieces = teamPieces;
    }

    public Location getOnePiece(Piece piece){
        return teamPieces.get(piece);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public ArrayList<Piece> getMovablePieces(){
        ArrayList<Location> pieceValidMoves = new ArrayList<Location>();
        ArrayList<Piece>  movablePieces = new ArrayList<Piece>();
        for(Piece teamPiece :this.getTeamPieces().keySet()){
            teamPiece.populateValidMoves(this.getTeamPieces().get(teamPiece));
            pieceValidMoves = teamPiece.validMoves;
//            System.out.println("Piece: "+teamPiece+ "Location: "+team.getTeamPieces().get(teamPiece));
//            System.out.println("Valid moves: "+teamPiece.validMoves+ "\n\n");
            if(pieceValidMoves.size() != 0) {
                movablePieces.add(teamPiece);
            }

        }
        return movablePieces;
    }


    public void printMovablePieces(){
        ArrayList<Piece> movablePieces = getMovablePieces();
        for(int i = 0; i<movablePieces.size();i++){
            Piece tempPiece = movablePieces.get(i);
            Location location = this.getTeamPieces().get(tempPiece);
            System.out.println(i+") Piece: "+tempPiece+" Location: "+location+ " Has Moved: "+tempPiece.getHasMoved());
        }

    }


}
