package com.company;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Rachel
 * Date: 11/12/13
 * Time: 8:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class DirectiveMatcher {
    private String pieceDirective, piece, color, boardPosition, secondBoardPosition,capture,
            castlePiece, castleColor, castlePosition, secondCastlePosition;
    private HashMap <String, String> pieces = new HashMap<String, String>();
    private HashMap <String, String> colors = new HashMap<String, String>();

    public DirectiveMatcher(String pieceDirective){
        populateColorHashMap();
        populatePieceHashMap();
        this.pieceDirective = pieceDirective.toUpperCase();
        try{
            getPiecePieces();
            determineDirective();
        }
        catch(Exception e){
            notValid();
        }
    }

    public void getPiecePieces(){
//       String firstCheck =
        String regexBitch = ("(?<whole>(?<piece>[K|Q|R|P|N|B])(?<color>[L|D])(?<boardPosition>[A-H][1-8]{1})(?<secondBoardPosition>(\\ [A-H][1-8]{1}))?(?<capture>[\\*]?)(\\ (?<castlePiece>[K|Q|R|P|N|B])(?<castleColor>[L|D])(?<castlePosition>[A-H][1-8]{1})(?<castleSecondPosition>(\\ [A-H][1-8]{1})?))?$)");
        try{
            Pattern regex = Pattern.compile(regexBitch);
            Matcher regexMatcher = regex.matcher(pieceDirective);

            for(int i = 0; regexMatcher.find();i++)
            {
                String whole =regexMatcher.group("whole");
                System.out.println("Whole: "+whole);
                piece = regexMatcher.group("piece");
                color = regexMatcher.group("color");
                boardPosition = regexMatcher.group("boardPosition");
                secondBoardPosition = regexMatcher.group("secondBoardPosition");
                capture = regexMatcher.group("capture");
                castlePiece = regexMatcher.group("castlePiece");
                castleColor = regexMatcher.group("castleColor");
                castlePosition = regexMatcher.group("castlePosition");
                secondCastlePosition = regexMatcher.group("castleSecondPosition");
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void determineDirective(){
        if(castlePiece == null){
            if(secondBoardPosition == null){
                if(piece == null){
                    notValid();
                }
                else{
                printPlacePiece();
                }
            }
            else{
                printMovePiece();
            }
        }
        else{
            printCastlePiece();
        }
    }

    public String getColor(String color){
       return colors.get(color);
    }

    public String getPieceTitle(String piece){
       return pieces.get(piece);
    }


    public void notValid(){
        System.out.println(pieceDirective+ " is not a valid directive.");
    }

    public void printPlacePiece(){
        System.out.println("Place "+getColor(color)+ " "+getPieceTitle(piece)+" on "+boardPosition);
    }

    public void printMovePiece(){
        if(capture == null)  {
            System.out.println("Move "+getColor(color)+ " "+getPieceTitle(piece)+" from "+boardPosition+" to "+secondBoardPosition);
        }
        else if (capture!=null){
            System.out.println(getColor(color)+ " "+getPieceTitle(piece)+" moves from "+boardPosition+" to"
                    +secondBoardPosition+" and captures piece on" + secondBoardPosition);
        }
    }

    public void printCastlePiece(){
        if(color.equals(castleColor)) {
            System.out.println(getColor(color)+ " "+getPieceTitle(piece)+ " on "+boardPosition
                    +" castles with "+getColor(castleColor)+ " "+ getPieceTitle(castlePiece) +" on "+secondBoardPosition);
        }
        else{
            notValid();
        }
    }

    public void populatePieceHashMap(){
        pieces.put("K", "King");
        pieces.put("Q", "Queen");
        pieces.put("R", "Rook");
        pieces.put("B", "Bishop");
        pieces.put("R", "Rook");
    }

    public void populateColorHashMap(){
        colors.put("L", "light");
        colors.put("D", "dark") ;
    }

}
