package com.company;

public class Main {

    private Board board;
    private Game game;
    public static void main(String[] args) {
        Game game = new Game();
        Board board = game.getGameBoard();
//        ReadFile initial = new ReadFile("/Users/Rachel/Documents/initialBoard.txt",game);
//        game.printBoard();

        System.out.println("Capital letters will represent dark pieces, and lowercase letters light pieces");
        ReadFile readFile = new ReadFile(args[0],game);
        game.printBoard();

        Players players = game.getPlayers();
        Team light = players.lightLeader.getTeam();
        Team dark = players.darkLeader.getTeam();

//        System.out.println("Light" +light.getTeamPieces());
//        System.out.println("Dark" +dark.getTeamPieces());

    }



}
