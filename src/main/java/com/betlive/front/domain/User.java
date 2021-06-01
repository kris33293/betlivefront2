package com.betlive.front.domain;

import java.math.BigDecimal;
import java.util.List;

public class User {

    private int userId;
    private String userName;
    private BigDecimal balance;
    private List<Ticket> tickets;
    private List<Betslip> betslips;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void setBetslips(List<Betslip> betslips) {
        this.betslips = betslips;
    }

    public List<Betslip> getBetslips() {
        return betslips;
    }
}
