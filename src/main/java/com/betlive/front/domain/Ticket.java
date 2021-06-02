package com.betlive.front.domain;

import java.math.BigDecimal;
import java.util.List;

public class Ticket {

    private int ticketId;
    private List<Type> types;
    private User user;
    private BigDecimal totalOdds;
    private BigDecimal totalStake;
    private BigDecimal toWin;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getTotalOdds() {
        return totalOdds;
    }

    public void setTotalOdds(BigDecimal totalOdds) {
        this.totalOdds = totalOdds;
    }

    public BigDecimal getTotalStake() {
        return totalStake;
    }

    public void setTotalStake(BigDecimal totalStake) {
        this.totalStake = totalStake;
    }

    public BigDecimal getToWin() {
        return toWin;
    }

    public void setToWin(BigDecimal toWin) {
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
