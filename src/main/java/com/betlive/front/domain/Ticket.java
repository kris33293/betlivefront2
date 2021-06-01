package com.betlive.front.domain;

import java.util.List;

public class Ticket {

    private int ticketId;
    private List<Type> types;
    private double totdalOdds;
    private double totdalStake;
    private double toWin;
    private String ticketStatus;
    private Betslip betslip;

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public double getTotdalOdds() {
        return totdalOdds;
    }

    public void setTotdalOdds(double totdalOdds) {
        this.totdalOdds = totdalOdds;
    }

    public double getTotdalStake() {
        return totdalStake;
    }

    public void setTotdalStake(double totdalStake) {
        this.totdalStake = totdalStake;
    }

    public double getToWin() {
        return toWin;
    }

    public void setToWin(double toWin) {
        this.toWin = toWin;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public Betslip getBetslip() {
        return betslip;
    }

    public void setBetslip(Betslip betslip) {
        this.betslip = betslip;
    }
}
