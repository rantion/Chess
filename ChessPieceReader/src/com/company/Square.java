package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Rachel
 * Date: 11/19/13
 * Time: 9:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class Square extends JPanel{

    public String content;
    public Piece piece;
    private JLabel picture;
    private Board board;
    private Location location;
    private ImageIcon piecePicture;

    public Square(String content, final Board board, Location location){
        this.location = location;
        this.content = content;
        this.board = board;
        this.setLayout(new BorderLayout());
        this.addMouseListener(new MouseListener(board, this));

    }

    public Square(String content, Piece piece){
        this.content = content;
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPicture(JLabel picture){
        this.picture = picture;
//        this.piecePicture =  piecePicture;
//        picture.setIcon(this.piecePicture);
        picture.setVisible(true);
        this.add(picture, BorderLayout.CENTER);
        this.picture.repaint();
        this.revalidate();
        this.repaint();

    }

    public void removePicture(){
        this.picture.setIcon(null);
        this.picture.repaint();
        this.repaint();
    }

    public Location getSquareLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public class MouseListener implements java.awt.event.MouseListener {
        private Board board;
        private Square square;

        public MouseListener(Board board, Square square){
            this.board = board;
            this.square = square;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if(board.isFirstSquareSelected()){
                board.saveFirstSquare(square);
            }
            else if(!board.isFirstSquareSelected()){
                board.saveSecondSquare(square);
            }
            System.out.println("Yo this is being clicked");
        }

        @Override
        public void mousePressed(MouseEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    }
    }

