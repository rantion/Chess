package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Rachel
 * Date: 12/12/13
 * Time: 1:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class TeamInfoPanel extends JPanel {
    JPanel whitePlayer, darkPlayer, emptyPanel;
    JLabel whiteTeam, darkTeam;

    public TeamInfoPanel(){
        GridLayout gridLayout = new GridLayout(0,2);
        this.setLayout(gridLayout);
        this.setSize(new Dimension(1000,200));

        whitePlayer = new JPanel();
        whiteTeam = new JLabel("Light Team");
        whitePlayer.add(whiteTeam);

        whitePlayer.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        darkPlayer = new JPanel();
        darkTeam = new JLabel("Dark Team");
        darkPlayer.add(darkTeam);
        darkPlayer.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        emptyPanel = new JPanel();
        this.add(whitePlayer);
        this.add(darkPlayer);
    }

    public void changeCurrentPlayer(String teamColor){
        if(teamColor.equals("L")){
            whitePlayer.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
            darkPlayer.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        }
        if(teamColor.equals("D")){
            darkPlayer.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
            whitePlayer.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        }
        this.repaint();
    }

    public void updateLabelForCheck(String teamColor, String checkMessage){
        if(teamColor.equals("L")){
             whiteTeam.setText("Light Team "+checkMessage);
        }
        if(teamColor.equals("D")){
            darkTeam.setText("Dark Team " + checkMessage);

        }

    }
}
