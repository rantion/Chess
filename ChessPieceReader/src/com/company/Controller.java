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
public class Controller {
    private String pieceDirective, piece, color, boardPosition, secondBoardPosition,capture,
            castlePiece, castleColor, castlePosition, secondCastlePosition;
    private Game game;
    private Player lightLeader, darkLeader,currentPlayer;
    private Board board;
    private Players players;
    private boolean gameCanContinue;

    public Controller(Game game){
        this.game = game;
        board = game.getBoard();
        players = game.getPlayers();
        this.lightLeader =  players.getLightLeader();
        this.darkLeader = players.getDarkLeader();
        this.currentPlayer = lightLeader;
        gameCanContinue = true;
        players.setCurrentPlayer(currentPlayer);
    }

    public void initialPlacement(String pieceDirective){
        this.pieceDirective = pieceDirective.toUpperCase();
        try{
            getCommandPieces();
            determineAction();
        }

        catch(Exception e) {
            System.out.println("Something is wrong with the input file");
            e.printStackTrace();
        }
    }

    public void checkCommand(String pieceDirective){
        this.pieceDirective = pieceDirective.toUpperCase();
//        while(gameCanContinue){
        try{
            getCommandPieces();

            if(isTeamsTurn()){
            determineAction();
            changePlayer(currentPlayer);
            gameCanContinue = true;
            }
            else{
               gameCanContinue = false;
            }
              }
        catch(Exception e){
            notValid();

        }
        }
//    }

    private Player changePlayer(Player currentPlayer){
        this.currentPlayer = (currentPlayer.equals(lightLeader)) ? darkLeader : lightLeader;
        return this.currentPlayer;

    }

    private boolean isTeamsTurn(){
        boolean isTurn;
              String cp= "";
        if(currentPlayer.equals(lightLeader)){
            cp = "light leader";
        }
        if(currentPlayer.equals(darkLeader)){
            cp= "dark leader";
        }
        System.out.println("Current Player: "+cp);
        System.out.println("Move: "+pieceDirective);
      if(color.equals("L")&&currentPlayer.equals(lightLeader)){
            isTurn = true;
      }
      else if(color.equals("D")&&currentPlayer.equals(darkLeader)){
          isTurn = true;
      }
     else{
          System.out.println("Sorry. It is the "+cp+" team's turn.");
          isTurn = false;
      }
      return isTurn;

    }

    private void getCommandPieces(){

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

    private void checkIfCurrentPlayer(Player currentPlayer){

    }

    private void determineAction(){
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

    private void notValid(){
        System.out.println(pieceDirective+ " is not a valid directive.");
    }

    private void PlacePiece(){
        board.placePiece(boardPosition, createPiece(),checkIfLightPiece(color, piece));


    }

    private void MovePiece(){
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


    private String checkIfLightPiece(String color, String piece){
        if(color.equals("L")){
            piece = piece.toLowerCase();
        }
        return piece;
    }
}
