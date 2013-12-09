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
            castlePiece, castleColor, castlePosition, secondCastlePosition, kingLocation, isInCheckMate, isNotInCheckMate;
    private Game game;
    private Player lightLeader, darkLeader,currentPlayer;
    private Board board;
    private Players players;
    private King darkKing, lightKing,king;
    private Team team;
    private Boolean checkMate;

    public Controller(Game game){
        this.game = game;
        board = game.getGameBoard();
        players = game.getPlayers();
        this.lightLeader =  players.getLightLeader();
        this.darkLeader = players.getDarkLeader();
        this.currentPlayer = lightLeader;
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

        try{
            getCommandPieces();

//            if(isTeamsTurn()){
            determineAction();
            changePlayer(currentPlayer);
//            }
        }
        catch(Exception e){
            notValid();
            e.printStackTrace();

        }
    }
//    }

    private Player changePlayer(Player currentPlayer){
        this.currentPlayer = (currentPlayer.equals(lightLeader)) ? darkLeader : lightLeader;
        players.setCurrentPlayer(this.currentPlayer);
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

        if((color.equals("L")&&currentPlayer.equals(lightLeader)) || (color.equals("D")&&currentPlayer.equals(darkLeader))){
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

    private void determineAction(){
        try{  if(piece != null){
            if(castlePiece == null){
                if(secondBoardPosition == null){
                    PlacePiece();
                }
                else{
                    MovePiece();

                }
            }
            else{
//            CastlePiece();
            }
        }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void notValid(){
        System.out.println(pieceDirective+ " is not a valid directive.");
    }

    private void PlacePiece(){
        Piece newPiece = createPiece();
        board.placePiece(boardPosition, newPiece,checkIfLightPiece(color, piece));
        if(color.equals("L")){
            Team lightTeam = players.lightLeader.getTeam();
            lightTeam.addPiecesToMap(newPiece,boardPosition);
        }
        if(color.equals("D")){
            Team darkTeam = players.darkLeader.getTeam();
            darkTeam.addPiecesToMap(newPiece,boardPosition);

        }


    }

    private void MovePiece(){
        isCurrentPlayerInCheck();

////        isCurrentPlayerInCheckMate();
        game.printBoard();


        Piece pieceToMove = board.getPiece(boardPosition);
        if(pieceToMove instanceof Pawn){
            if(capture.equals("*"))       {
                ((Pawn) pieceToMove).pawnCapture(boardPosition,secondBoardPosition);
            }
            else if(board.getPiece(secondBoardPosition) == null) {
                ((Pawn)pieceToMove).isLegalMove(boardPosition, secondBoardPosition);
            }
            else{
                System.out.println("The Pawn cannot move there without a capture command");
            }
        }
        else{
            if(pieceToMove.isLegalMove(boardPosition, secondBoardPosition)){
                pieceToMove.move(boardPosition,secondBoardPosition,pieceToMove.getPiece());
                game.printBoard();
            }
        }
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

    private void getCurrentTeam(){
        team = null;
        kingLocation = null;
        king = null;
        isInCheckMate = null;
        isNotInCheckMate = null;
        if(currentPlayer.equals(lightLeader)){
            team = players.lightLeader.getTeam();
            kingLocation = team.getOnePiece(getLightKing());
            king = getLightKing();
            isInCheckMate = "The light King is in checkmate :(";
            isNotInCheckMate = "The light King is not in checkmate";
        }
        if(currentPlayer.equals(darkLeader)){
            team = players.darkLeader.getTeam();
            kingLocation = team.getOnePiece(getDarkKing());
            king = getDarkKing();
            isInCheckMate = "The dark King is in checkmate :(";
            isNotInCheckMate = "The dark King is not in checkmate";
        }
    }



    private boolean isCurrentPlayerInCheckMate(String kingLocation, String _color){
        checkMate = true;
        getCurrentTeam();
        if(king.canMoveOutOfCheck(kingLocation)){
            checkMate = false;
        } else{
            king.populateAllPossibleAttackSquares(kingLocation);
            for(Piece teamPiece: team.getTeamPieces().keySet()){
                if(!(teamPiece instanceof King)){
                    String teamPieceLoc = team.getTeamPieces().get(teamPiece);
                    for(String loc :king.getAllPossibleAttackSquares()){
                        if(teamPiece.isLegalMove(teamPieceLoc,loc)){
                            teamPiece.move(teamPieceLoc, loc, teamPiece.piece);
                            if(!king.canMoveOutOfCheck(kingLocation)){
                                checkMate = false;
                            }
                            System.out.println("Piece has been moved back");
                            teamPiece.unDoMove(loc, teamPieceLoc, teamPiece.piece);
                        }



                    }
                }
            }
        }
        if(checkMate){
            System.out.println(isInCheckMate);
        }
        else{
            System.out.println(isNotInCheckMate);
        }

        return checkMate;
    }

    private void isCurrentPlayerInCheck(){
        if(currentPlayer.equals(lightLeader)){
            Team light = players.lightLeader.getTeam();
            String location = light.getOnePiece(getLightKing());
            if(lightKing.determineIfInCheck(location)){
                System.out.println("The Light King is in Check");
                isCurrentPlayerInCheckMate(location, "L");
            }
            else{
                System.out.println("The Light King is not in check");
            }
        }
        if(currentPlayer.equals(darkLeader)){
            Team dark = players.darkLeader.getTeam();
            String location = dark.getOnePiece(getDarkKing());
            if(darkKing.determineIfInCheck(location)) {
                System.out.println("The Dark King is in Check");
                isCurrentPlayerInCheckMate(location,"D");
            }
            else{
                System.out.println("The Dark King is not in check");
            }
        }

    }

    private Piece createPiece(){
        Piece newPiece = null;
        if(piece.equals("K")){
            newPiece = new King(game,color);
            saveKings(newPiece,color);
        }
        else if(piece.equals("Q")){
            newPiece = new Queen(game,color);
        }
        else if(piece.equals("R")){
            newPiece = new Rook (game, color);
        }
        else if(piece.equals("N")){
            newPiece = new Knight(game,color);
        }
        else if(piece.equals("B")){
            newPiece = new Bishop(game, color);
        }
        else if(piece.equals("P")){
            newPiece = new Pawn(game, color);
        }
        else{
            System.out.println("Something has gone awry, you should probably start over");
        }
        return newPiece;
    }

    private void saveKings(Piece king,String color){
        if(color.equals("L")){
            setLightKing((King)king);
        }
        if(color.equals("D")){
            setDarkKing((King)king);
        }

    }


    private String checkIfLightPiece(String color, String piece){
        if(color.equals("L")){
            piece = piece.toLowerCase();
        }
        return piece;
    }

    public King getDarkKing() {
        return darkKing;
    }

    public void setDarkKing(King darkKing) {
        this.darkKing = darkKing;
    }

    public King getLightKing() {
        return lightKing;
    }

    public void setLightKing(King lightKing) {
        this.lightKing = lightKing;
    }
}
