package com.company;

import java.util.ArrayList;
import java.util.Scanner;
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
            castlePiece, castleColor, castlePosition, secondCastlePosition, isInCheckMate, isNotInCheckMate, teamName;
    private Game game;
    private Player lightLeader, darkLeader,currentPlayer;
    private Board board;
    private Players players;
    private King darkKing, lightKing,king;
    private Team team;
    private Boolean checkMate;
    private Boolean gameIsGoing;
    private Scanner scan = new Scanner(System.in);
    private Location firstLocation, secondLocation, kingLocation;

    public Controller(Game game){
        this.game = game;
        board = game.getGameBoard();
        players = game.getPlayers();
        this.lightLeader =  players.getLightLeader();
        this.darkLeader = players.getDarkLeader();
        this.currentPlayer = lightLeader;
        this.gameIsGoing = true;
        players.setCurrentPlayer(currentPlayer);
    }

    public void startGame(){
        while(gameIsGoing){
            takeTurn();
            changePlayer(currentPlayer);
        }

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


    public void takeTurn(){
//        ArrayList<Location> pieceLocations = new ArrayList<Location>();
        Piece pieceSelected;
        startTurn();
        try{
            if(gameIsGoing){
                pieceSelected = getPieceFromUser();
                firstLocation =  team.getTeamPieces().get(pieceSelected);
                getMoveFromUser(pieceSelected);
                movePiece(firstLocation,secondLocation,pieceSelected);
            }
        }
        catch (Exception e){
            e.printStackTrace();
//            System.out.println("Woah, why don't we enter some valid inputs hmm?");
//            takeTurn();
        }

    }

    private void startTurn(){

        getCurrentTeam();
        System.out.println("It is the "+teamName+" player's turn.\n");
        game.printBoard();
        isCurrentPlayerInCheck();


    }

    private Piece getPieceFromUser(){
        ArrayList<Piece> movablePieces = team.getMovablePieces();
        System.out.println("Enter the number of the piece you would like to move: ");
        team.printMovablePieces();
        String pieceNumString = scan.nextLine();
        int pieceNum = Integer.parseInt(pieceNumString);
        return movablePieces.get(pieceNum);

    }

    private void getMoveFromUser(Piece pieceSelected){
        ArrayList<Location> pieceLocations = new ArrayList<Location>();
        pieceLocations = getValidMoves(pieceSelected);
        System.out.println("Move from "+boardPosition+ " to: ");
        printValidMoves(pieceLocations);

        String newLocationNum = scan.nextLine();
        int newLocNum = Integer.parseInt(newLocationNum);

        secondLocation = pieceLocations.get(newLocNum);

    }

    private void printValidMoves(ArrayList<Location> validPieceMoves){

        for(int i = 0; i<validPieceMoves.size();i++){
            Location location = validPieceMoves.get(i);
            System.out.println(i+") "+location.toString());
        }

    }

    private ArrayList<Location> getValidMoves(Piece pieceSelected){
        ArrayList<Location>validPieceMoves = new ArrayList<Location>();
        for(Location location : pieceSelected.validMoves){
            validPieceMoves.add(location);
        }
        return validPieceMoves;
    }

//    private void printMovablePieces(ArrayList<Piece> movablePieces){
//        for(int i = 0; i<movablePieces.size();i++){
//            Piece tempPiece = movablePieces.get(i);
//           String location = team.getTeamPieces().get(tempPiece);
//            System.out.println(i+") Piece: "+tempPiece+" Location: "+location+ " Has Moved: "+tempPiece.getHasMoved());
//        }
//
//    }

//    private ArrayList<Piece> getMovablePieces(){
//        ArrayList<String> pieceValidMoves = new ArrayList<String>();
//        ArrayList<Piece>  movablePieces = new ArrayList<Piece>();
//        for(Piece teamPiece :team.getTeamPieces().keySet()){
//            teamPiece.populateValidMoves(team.getTeamPieces().get(teamPiece));
//            pieceValidMoves = teamPiece.validMoves;
////            System.out.println("Piece: "+teamPiece+ "Location: "+team.getTeamPieces().get(teamPiece));
////            System.out.println("Valid moves: "+teamPiece.validMoves+ "\n\n");
//            if(pieceValidMoves.size() != 0) {
//                movablePieces.add(teamPiece);
//            }
//
//        }
//        return movablePieces;
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
        firstLocation = new Location (boardPosition);
        board.placePiece(firstLocation, newPiece,checkIfLightPiece(color, piece));
        if(color.equals("L")){
            Team lightTeam = players.lightLeader.getTeam();
            lightTeam.addPiecesToMap(newPiece,firstLocation);
        }
        if(color.equals("D")){
            Team darkTeam = players.darkLeader.getTeam();
            darkTeam.addPiecesToMap(newPiece,firstLocation);

        }


    }

    private void movePiece(Location firstLocation, Location secondLocation, Piece pieceToMove){
        pieceToMove.setHasMoved(true);
        pieceToMove.move(firstLocation,secondLocation,pieceToMove.getPiece());

    }

    private void MovePiece(){
        isCurrentPlayerInCheck();
        game.printBoard();


        Piece pieceToMove = board.getPiece(firstLocation);
        pieceToMove.setHasMoved(true);
        System.out.println("Piece to move: "+ pieceToMove );
        if(pieceToMove instanceof Pawn){
            if(capture.equals("*"))       {
                ((Pawn) pieceToMove).pawnCapture(firstLocation,secondLocation);
            }
            else if(board.getPiece(secondLocation) == null) {
                ((Pawn)pieceToMove).isLegalMove(firstLocation, secondLocation);
            }
            else{
                System.out.println("The Pawn cannot move there without a capture command");
            }
        }
        else{
            if(pieceToMove.isLegalMove(firstLocation, secondLocation)){
                pieceToMove.move(firstLocation,secondLocation,pieceToMove.getPiece());
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
        teamName = null;
        if(currentPlayer.equals(lightLeader)){
            team = players.lightLeader.getTeam();
            kingLocation = team.getOnePiece(getLightKing());
            king = getLightKing();
            teamName = "light";
            isInCheckMate = "The light King is in checkmate :(";
            isNotInCheckMate = "The light King is not in checkmate";
        }
        if(currentPlayer.equals(darkLeader)){
            team = players.darkLeader.getTeam();
            kingLocation = team.getOnePiece(getDarkKing());
            king = getDarkKing();
            teamName = "dark";
            isInCheckMate = "The dark King is in checkmate :(";
            isNotInCheckMate = "The dark King is not in checkmate";
        }
    }



    private boolean isCurrentPlayerInCheckMate(Location kingLocation){
        checkMate = true;
        getCurrentTeam();
        if(king.canMoveOutOfCheck(kingLocation)){
            checkMate = false;
//        } else{
//
//            king.populateAllPossibleAttackSquares(kingLocation);
            for(Piece teamPiece: team.getTeamPieces().keySet()){
                if(!(teamPiece instanceof King) && !(team.getTeamPieces().get(teamPiece).equals("captured"))){
                   Location teamPieceLoc = team.getTeamPieces().get(teamPiece);
                    for(Location loc :king.getAllPossibleAttackSquares(kingLocation)){
                        if(teamPiece.isLegalMove(teamPieceLoc,loc)){
                            teamPiece.move(teamPieceLoc, loc, teamPiece.piece);
                            if(!king.determineIfInCheck(kingLocation)){
                                checkMate = false;
                            }
                            teamPiece.unDoMove(loc, teamPieceLoc, teamPiece.piece);
                        }



                    }
                }
            }
        }
        if(checkMate){
            System.out.println(isInCheckMate);
            gameIsGoing = false;
        }
//        else{
//           System.out.println(isNotInCheckMate);
//        }

        return checkMate;
    }

    private void isCurrentPlayerInCheck(){
        if(currentPlayer.equals(lightLeader)){
            Team light = players.lightLeader.getTeam();
            Location location = light.getOnePiece(getLightKing());
            System.out.println("Location: "+location.toString());
            if(lightKing.determineIfInCheck(location)){
                if(!(isCurrentPlayerInCheckMate(location))){
                    System.out.println("The Light King is in Check");
                }
            }
//            else{
//               System.out.println("The Light King is not in check");
//            }
        }
        if(currentPlayer.equals(darkLeader)){
            Team dark = players.darkLeader.getTeam();
           Location location = dark.getOnePiece(getDarkKing());
            if(darkKing.determineIfInCheck(location)) {
                if(! (isCurrentPlayerInCheckMate(location))){
                    System.out.println("The Dark King is in Check");
                }
            }
//            else{
//                System.out.println("The Dark King is not in check");
//            }
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
