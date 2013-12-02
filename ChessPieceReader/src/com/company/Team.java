package com.company;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Rachel
 * Date: 11/26/13
 * Time: 6:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class Team {
    private HashMap<Piece,String> teamPieces;
    private String color;

    public Team(String color){
        this.color = color;
        teamPieces = new HashMap<Piece, String>();
    }

    public HashMap<Piece, String> getTeamPieces() {
        return teamPieces;
    }

    public void addPiecesToMap(Piece piece, String location){
          teamPieces.put(piece,location);
    }

    public void setTeamPieces(HashMap<Piece, String> teamPieces) {
        this.teamPieces = teamPieces;
    }

    public String getOnePiece(Piece piece){
        return teamPieces.get(piece);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
