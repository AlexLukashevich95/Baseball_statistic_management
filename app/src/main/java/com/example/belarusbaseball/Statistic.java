package com.example.belarusbaseball;

public class Statistic {
    String teamHomeRuns;
    String teamGuestRuns;
    String teamWin;
    String teamLose;

    public Statistic(String teamHomeRuns, String teamGuestRuns, String teamWin, String teamLose) {
        this.teamHomeRuns = teamHomeRuns;
        this.teamGuestRuns = teamGuestRuns;
        this.teamWin = teamWin;
        this.teamLose = teamLose;
    }

    public String getTeamHomeRuns() {
        return teamHomeRuns;
    }

    public void setTeamHomeRuns(String teamHomeRuns) {
        this.teamHomeRuns = teamHomeRuns;
    }

    public String getTeamGuestRuns() {
        return teamGuestRuns;
    }

    public void setTeamGuestRuns(String teamGuestRuns) {
        this.teamGuestRuns = teamGuestRuns;
    }

    public String getTeamWin() {
        return teamWin;
    }

    public void setTeamWin(String teamWin) {
        this.teamWin = teamWin;
    }

    public String getTeamLose() {
        return teamLose;
    }

    public void setTeamLose(String teamLose) {
        this.teamLose = teamLose;
    }
}
