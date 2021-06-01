package com.betlive.front.domain;

public class Bet {

    private int betId;
    private double oddHome;
    private double oddAway;
    private double oddDraw;
    private String homeTeam;
    private String awayTeam;
    private String eventDate;

    public int getBetId() {
        return betId;
    }

    public void setBetId(int betId) {
        this.betId = betId;
    }

    public double getOddHome() {
        return oddHome;
    }

    public void setOddHome(double oddHome) {
        this.oddHome = oddHome;
    }

    public double getOddAway() {
        return oddAway;
    }

    public void setOddAway(double oddAway) {
        this.oddAway = oddAway;
    }

    public double getOddDraw() {
        return oddDraw;
    }

    public void setOddDraw(double oddDraw) {
        this.oddDraw = oddDraw;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }
}
