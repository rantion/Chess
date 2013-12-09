package com.company;

public class Main {

    private Board board;
    private Game game;
    public static void main(String[] args) {
        Game game = new Game();
        Board board = game.getGameBoard();
        ReadFile initial = new ReadFile("/Users/Rachel/Documents/initialBoard.txt",game);
//        game.printBoard();
        System.out.println("Capital letters will represent dark pieces, and lowercase letters light pieces");
//        initial.readInFile(args[0]);
//        ReadFile file = new ReadFile(args[0],game);

        Controller controller = new Controller(game);
        controller.startGame();
//        controller.checkCommand("KLE3");
//        controller.checkCommand("KDA8");
//        controller.checkCommand("RDB3");
//        controller.checkCommand("BDH6");
//        controller.checkCommand("BDB6");
//        controller.checkCommand("BLH2");
//        controller.checkCommand("QDE5");
//        controller.checkCommand("PDA8");
//        controller.checkCommand("BLH2 E5");
//        controller.checkCommand("RDH5 H6");
//        controller.checkCommand("BLF1 H3");
//        controller.checkCommand("RDG4 D4");


    }



}
