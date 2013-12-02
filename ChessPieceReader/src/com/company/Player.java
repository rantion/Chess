package com.company;

/**
 * Created with IntelliJ IDEA.
 * User: Rachel
 * Date: 11/25/13
 * Time: 1:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class Player{
    private Team team;
    private String color;

    public Player(){
        team = new Team(color);
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        team.setColor(this.color);
    }
}
