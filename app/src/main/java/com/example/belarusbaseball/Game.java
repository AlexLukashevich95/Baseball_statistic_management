package com.example.belarusbaseball;

import java.util.Date;

public class Game {
    public String nameHomeTeam;
    public String nameGuestTeam;
    public String date;
    public String tournament;

    public Game(){

    }

    public Game(String nameHomeTeam, String nameGuestTeam, String date,String tournament) {
        this.nameHomeTeam = nameHomeTeam;
        this.nameGuestTeam = nameGuestTeam;
        this.date = date;
        this.tournament = tournament;

    }

    public String getNameHomeTeam() {
        return this.nameHomeTeam;
    }

    public void setNameHomeTeam(String nameHomeTeam) {
        this.nameHomeTeam = nameHomeTeam;
    }

    public String getNameGuestTeam() {
        return this.nameGuestTeam;
    }

    public void setNameGuestTeam(String nameGuestTeam) {
        this.nameGuestTeam = nameGuestTeam;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTournament() {
        return this.tournament;
    }

    public void setTournament(String tournament) {
        this.tournament = tournament;
    }
}
