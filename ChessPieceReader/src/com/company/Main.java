package com.company;

public class Main {

    private Board board;
    private Game game;
    public static void main(String[] args) {
        Game game = new Game();
        Board board = game.getGameBoard();
//        ReadFile initial = new ReadFile("/Users/Rachel/Documents/initialBoard.txt",game);
//        game.printBoard();
//        System.out.println("Capital letters will represent dark pieces, and lowercase letters light pieces");
//        initial.readInFile(args[0]);
//        ReadFile readFile = new ReadFile(args[0],game);
//        ReadFile readFile = new ReadFile(args[0],game);
//
//        Players players = game.getPlayers();
//        Team light = players.lightLeader.getTeam();
//        Team dark = players.darkLeader.getTeam();

//        System.out.println("Light" +light.getTeamPieces());
//        System.out.println("Dark" +dark.getTeamPieces());

//
        Controller controller = new Controller(game);
//
        controller.checkCommand("QLE4");
        controller.checkCommand("RDF3");
        controller.checkCommand("RDC2");
        controller.checkCommand("BDB7");
        controller.checkCommand("BDH7");
        controller.checkCommand("NDG4");
        controller.checkCommand("NDA4");
        controller.checkCommand("PDE7");
        controller.checkCommand("PDE1");
        Piece piece = board.getPiece("E4");
        System.out.println("Piece: "+piece);
        piece.checkDiagonals("E4");
        piece.checkHorizontals("E4");
        piece.checkVerticals("E4");
        System.out.println("Valid Moves: "+piece.validMoves);
//        controller.checkCommand("PLC1");
//        controller.checkCommand("PLC1 C2");
//        controller.checkCommand("PLC2 C3");

    }



}
