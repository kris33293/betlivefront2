package com.betlive.front.domain;

import java.math.BigDecimal;
import java.util.List;

public class User {

    private int userId;
    private String userName;
    private BigDecimal balance;
    private List<Ticket> ticekts;
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

    public List<Ticket> getTicekts() {
        return ticekts;
    }

    public void setTicekts(List<Ticket> ticekts) {
        this.ticekts = ticekts;
    }
}
