package com.betlive.front.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class Betslip {

    int betslipId;
    Set<Type> types;
    User user;
    BigDecimal totalOdds, totalStake, toWin;
    Ticket ticket;

    public int getBetslipId() {
        return betslipId;
    }

    public void setBetslipId(int betslipId) {
        this.betslipId = betslipId;
    }

    public Set<Type> getTypes() {
        return types;
    }

    public void setTypes(Set<Type> types) {
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

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
