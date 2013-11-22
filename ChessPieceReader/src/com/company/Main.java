package com.company;

public class Main {

    private static Board board;
    private static Game game = new Game();

    public static void main(String[] args) {
        board = game.getBoard();
        ReadFile initial = new ReadFile("/Users/Rachel/Documents/initialBoard.txt");
        Piece piece = new Queen(board,"L");
        piece.checkMove("B7","A8");
//        piece.checkMove("G4","D1");
        board.printBoard();
//
//        System.out.println("Capital letters will represent dark pieces, and lowercase letters light pieces");
//        ReadFile readFile = new ReadFile(args[0]);
//        board.printBoard();





    }


}
