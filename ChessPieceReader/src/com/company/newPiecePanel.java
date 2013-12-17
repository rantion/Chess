package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: Rachel
 * Date: 12/16/13
 * Time: 7:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class newPiecePanel extends JFrame{
    private JPanel piecePanel;
    private JButton queen, bishop, rook, knight;
    private ButtonHandler handler = new ButtonHandler();
    private String piece, color;
    private Square square;
    private Board board;

    public newPiecePanel(Board board,String color, Square square){
        super("Select the piece you'd like");
        this.board = board;
        this.color = color;
        this.square = square;
        piecePanel = new JPanel();
        populatePiecePanel();
        piecePanel.setVisible(true);
        setLayout(new BorderLayout());
        this.setSize(200, 200);
        this.getContentPane().add(piecePanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    public void populatePiecePanel(){
        piecePanel.setLayout(new GridLayout(4,0));
        queen = new JButton("Queen");
        bishop = new JButton("Bishop");
        rook = new JButton("Rook");
        knight = new JButton("Knight");

        queen.addActionListener(handler);
        bishop.addActionListener(handler);
        rook.addActionListener(handler);
        knight.addActionListener(handler);

        piecePanel.add(queen);
        piecePanel.add(bishop);
        piecePanel.add(rook);
        piecePanel.add(knight);
    }


    private class ButtonHandler implements ActionListener

    {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == queen){
            piece = "Q";
            Piece newPiece = board.getController().createPiece(piece,color);
            board.addPieceToSquare(square, newPiece);
             dispose();
            }
            if(e.getSource() == bishop){
                piece = "B";
                Piece newPiece = board.getController().createPiece(piece,color);
                board.addPieceToSquare(square, newPiece);
                dispose();
            }
            if(e.getSource() == rook){
                piece = "R";
                Piece newPiece = board.getController().createPiece(piece,color);
                board.addPieceToSquare(square, newPiece);
                dispose();
            }
            if(e.getSource() == knight){
                piece = "N";
                Piece newPiece = board.getController().createPiece(piece,color);
                board.addPieceToSquare(square, newPiece);
                dispose();
            }
        }
    }
}
