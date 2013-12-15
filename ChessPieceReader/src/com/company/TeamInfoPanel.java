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
        darkPlayer = new JPanel();
        darkTeam = new JLabel("Dark Team");
        darkPlayer.add(darkTeam);
        emptyPanel = new JPanel();
        this.add(whitePlayer);
        this.add(darkPlayer);
    }
}
