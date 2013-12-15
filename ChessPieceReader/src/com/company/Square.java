package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Rachel
 * Date: 11/19/13
 * Time: 9:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class Square extends JPanel {

    public String content;
    public Piece piece;
    private JLabel picture;
    private ImageIcon piecePicture;

    public Square(String content){
        picture = new JLabel();
        piecePicture = new ImageIcon();
        this.content = content;
        this.setLayout(new BorderLayout());
//        this.add(picture, BorderLayout.CENTER);
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

    public void setPicture(ImageIcon piecePicture){
        this.piecePicture =  piecePicture;
        picture.setIcon(this.piecePicture);
        picture.repaint();
    }

}
