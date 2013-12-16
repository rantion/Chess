package com.company;

/**
 * Created with IntelliJ IDEA.
 * User: Rachel
 * Date: 11/25/13
 * Time: 1:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class Players {
    public Player lightLeader, darkLeader, currentPlayer;

    public Players(){
        lightLeader = new Player();
        lightLeader.setColor("L");
        darkLeader = new Player();
        darkLeader.setColor("D");
        currentPlayer = new Player();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Player getLightLeader() {
        return lightLeader;
    }

    public Player getDarkLeader() {
        return darkLeader;
    }



}
