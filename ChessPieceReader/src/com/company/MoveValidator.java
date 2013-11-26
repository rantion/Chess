package com.company;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Rachel
 * Date: 11/12/13
 * Time: 8:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class MoveValidator {
    private String pieceDirective, piece, color, boardPosition, secondBoardPosition,capture,
            castlePiece, castleColor, castlePosition, secondCastlePosition;
    private Game game = new Game();
    private Board board;

    public MoveValidator(String pieceDirective){
        board = game.getBoard();
        this.pieceDirective = pieceDirective.toUpperCase();
        try{
            getCommandPieces();
            determineAction();
        }
        catch(Exception e){
            notValid();

        }
    }

    public void getCommandPieces(){

        String pieceRegex = ("(?<whole>(?<piece>[K|Q|R|P|N|B|P])(?<color>[L|D])(?<boardPosition>[A-H][1-8]{1})(\\ (?<secondBoardPosition>[A-H][1-8]{1}))?(?<capture>[\\*]?)(\\ (?<castlePiece>[K|Q|R|P|N|B|P])(?<castleColor>[L|D])(?<castlePosition>[A-H][1-8]{1})(?<castleSecondPosition>(\\ [A-H][1-8]{1})?))?$)");
        try{
            Pattern regex = Pattern.compile(pieceRegex);
            Matcher regexMatcher = regex.matcher(pieceDirective);

            while(regexMatcher.find()){
                String whole =regexMatcher.group("whole");
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
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void determineAction(){
        if(castlePiece == null){
            if(secondBoardPosition == null){


                PlacePiece();

            }
            else{
                if(piece != null){
                MovePiece();
                }
                else
                    notValid();
            }
        }
        else{
//            CastlePiece();
        }
    }

    public void notValid(){
        System.out.println(pieceDirective+ " is not a valid directive.");
    }

    public void PlacePiece(){
        board.placePiece(boardPosition, createPiece(),checkIfLightPiece(color, piece));


    }

    public void MovePiece(){
       Piece pieceToMove = board.getPiece(boardPosition);
        pieceToMove.checkMove(boardPosition,secondBoardPosition);
    }

//    public void CastlePiece(){
//        if(color.equals(castleColor)) {
//            board.placePiece(boardPosition, checkIfLightPiece(castleColor, castlePiece));
//            board.placePiece(secondBoardPosition, checkIfLightPiece(color, piece));
//        }
//        else{
//            notValid();
//        }
//    }

   private Piece createPiece(){
        Piece newPiece = new Piece(board,color);
        if(piece.equals("K")){
            newPiece = new King(board, color);
        }
        else if(piece.equals("Q")){
            newPiece = new Queen(board, color);
        }
        else if(piece.equals("R")){
            newPiece = new Rook (board, color);
        }
        else if(piece.equals("N")){
            newPiece = new Knight(board,color);
        }
        else if(piece.equals("B")){
            newPiece = new Rook(board, color);
        }
        else if(piece.equals("P")){
            newPiece = new Pawn(board, color);
        }
       else{
            System.out.println("Something has gone awry, you should probably start over");
        }
        return newPiece;
    }


    public String checkIfLightPiece(String color, String piece){
        if(color.equals("L")){
            piece = piece.toLowerCase();
        }
        return piece;
    }
}
