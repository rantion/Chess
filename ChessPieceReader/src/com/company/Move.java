package com.company;

/**
 * Created with IntelliJ IDEA.
 * User: Rachel
 * Date: 12/5/13
 * Time: 1:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class Move {
    private String startingPosition,endingPosition;
    private Board board;
    public Move(Board board){
           this.board=board;
    }

//    public void movePiece(String oldLocation,String newLocation, String piece){
//        if(board.isSquareEmpty(newLocation,this.color)){
//            board.removePiece(oldLocation);
//            if(board.getPiece(newLocation)!=null){
//                board.removePiece(newLocation);
//            }
//            board.placePiece(newLocation, this,piece);
//            game.printBoard();
////            System.out.println("\n");
//        }



}
