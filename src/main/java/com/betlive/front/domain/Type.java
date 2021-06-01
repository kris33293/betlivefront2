package com.betlive.front.domain;

import java.util.Set;

public class Type {

    int typeId;
    String homeTeam;
    String awayTeam;
    String eventDate;
    double odd;
    String yourType;
    Set<Betslip> betslips;
    Set<Ticket> tickets;


    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
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

    public double getOdd() {
        return odd;
    }

    public void setOdd(double odd) {
        this.odd = odd;
    }

    public String getYourType() {
        return yourType;
    }

    public void setYourType(String yourType) {
        this.yourType = yourType;
    }

    public Set<Betslip> getBetslips() {
        return betslips;
    }

    public void setBetslips(Set<Betslip> betslips) {
        this.betslips = betslips;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }
}
