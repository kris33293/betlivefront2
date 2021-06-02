package com.betlive.front;

import com.betlive.front.domain.*;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@PageTitle("BetLive v1")
@Route("")
public class VaadinUI extends VerticalLayout {

    JsonService service;

    Button home = new Button("Home",this::home);

    Button draw = new Button("Draw",this::draw);

    Button away = new Button("Away",this::away);


    Button createTicket = new Button("Create Ticket",this::createTicket);

    private void createTicket(ClickEvent<Button> buttonClickEvent) {
        createTicketFromBetslip();

    }

    private void createTicketFromBetslip() {
        Betslip betslip = service.findBetslip(1);
        betslip.setTotalStake(stakeAmmount.getValue());
        double totalOdd = 0;

        for (Type type : betslip.getTypes()) {
            totalOdd = totalOdd + type.getOdd();
        }

        betslip.setTotalOdds(new BigDecimal(totalOdd));
        betslip.setToWin(new BigDecimal(totalOdd).multiply(stakeAmmount.getValue()));

        service.createAndSaveBetslip(betslip);
        service.createTicket(betslip.getBetslipId());
    }

    BigDecimalField stakeAmmount = new BigDecimalField("Stake Ammount");

    HorizontalLayout typeGrid = new HorizontalLayout(
            home,draw,away,stakeAmmount,createTicket
    );

    Button makeDeposit = new Button("Make Deposit",this::deposit);

    private void deposit(ClickEvent<Button> buttonClickEvent) {
        service.makeDeposit(ammount.getValue(),userId);
        userGrid.setItems(service.getAllUsers());
    }

    Button withdrawMoney = new Button("Withdraw Money",this::withdraw);

    private void withdraw(ClickEvent<Button> buttonClickEvent) {
        service.withdrawMoney(ammount.getValue(),userId);
        userGrid.setItems(service.getAllUsers());
    }

    BigDecimalField ammount = new BigDecimalField("Ammount");

    Button showTickets = new Button("Show Tickets",this::showTickets);

    private void showTickets(ClickEvent<Button> buttonClickEvent) {
        service.getAllUserTickets(userId);
    }

    HorizontalLayout userLayout = new HorizontalLayout(
            showTickets, makeDeposit,withdrawMoney,ammount
    );



    Grid<Type> type = new Grid<>();
    Grid<Bet> bets = new Grid<>();
    private Grid<User> userGrid = new Grid<>();

    private Grid<Ticket> userTicketsGrid = new Grid<>();

    private int typeId,userId;
    private int a=0;
    private String homeTeam,awayTeam,eventDate;
    private Double oddDraw,oddHome,oddAway;

    Button availableBets = new Button("Available Bets", this::showBets);

    Button user = new Button("User", this::user);

    HorizontalLayout barLayout = new HorizontalLayout(
            availableBets,user
    );

    public VaadinUI(JsonService service) {
    this.service = service;

        if (a==0){
            service.createUser();
            a++;
        }



        userLayout.setVisible(false);
        userGrid.addColumn(user1 -> user1.getUserName()).setFlexGrow(1).setHeader("Username");
        userGrid.addColumn(user1 -> user1.getBalance()).setFlexGrow(1).setHeader("Balance");
        userGrid.setVisible(false);

        userTicketsGrid.addColumn(user -> user.getTicketStatus()).setFlexGrow(1).setHeader("Ticket Status");
        userTicketsGrid.addColumn(user -> user.getToWin()).setFlexGrow(1).setHeader("To Win");
        userTicketsGrid.addColumn(user -> user.getTypes()).setFlexGrow(1).setHeader("Types");
        userTicketsGrid.setVisible(false);



        bets.addColumn(bet -> bet.getHomeTeam()).setFlexGrow(1).setHeader("Home Team");
        bets.addColumn(bet -> bet.getAwayTeam()).setFlexGrow(1).setHeader("Away Team");
        bets.addColumn(bet -> bet.getOddHome()).setFlexGrow(1).setHeader("Odd Home");
        bets.addColumn(bet -> bet.getOddAway()).setFlexGrow(1).setHeader("Odd Away");
        bets.addColumn(bet -> bet.getOddDraw()).setFlexGrow(1).setHeader("Odd Draw");
        bets.addColumn(bet -> bet.getEventDate()).setFlexGrow(1).setHeader("Event Date");

        bets.setSelectionMode(Grid.SelectionMode.SINGLE);

        bets.addSelectionListener(selectionEvent -> {
            selectionEvent.getFirstSelectedItem().ifPresent(bet -> {

                typeId = bet.getBetId();
                homeTeam = bet.getHomeTeam();
                awayTeam = bet.getAwayTeam();
                eventDate = bet.getEventDate();

                oddDraw = bet.getOddDraw();
                oddAway = bet.getOddAway();
                oddHome = bet.getOddHome();

            });
        }) ;


        type.addColumn(type -> type.getHomeTeam()).setFlexGrow(1).setHeader("Home Team");
        type.addColumn(type -> type.getAwayTeam()).setFlexGrow(1).setHeader("Away Team");
        type.addColumn(type -> type.getOdd()).setFlexGrow(1).setHeader("Odd");



        userGrid.setSelectionMode(Grid.SelectionMode.SINGLE);

        userGrid.addSelectionListener(selectionEvent -> {
            selectionEvent.getFirstSelectedItem().ifPresent(user -> {

                userId= user.getUserId();

            });
        }) ;


     // type.setItems(service.getAllTypesFromBetslip());
        userGrid.setItems(service.getAllUsers());
        bets.setItems(service.getAllBets());
        add(barLayout,bets,typeGrid,type,userGrid,userLayout,userTicketsGrid);


    }

    private void user(ClickEvent clickEvent) {
        bets.setVisible(false);
        type.setVisible(false);
        typeGrid.setVisible(false);
        userGrid.setVisible(true);
        userTicketsGrid.setVisible(true);
        userLayout.setVisible(true);

    }

    private void showBets(ClickEvent clickEvent) {
        refreshMatces();

        bets.setVisible(true);
        type.setVisible(true);
        typeGrid.setVisible(true);
        userGrid.setVisible(false);
        userTicketsGrid.setVisible(false);
        userLayout.setVisible(false);
    }

    private void refreshMatces() {
        service.deleteMatches();
        service.saveMatches();
        bets.setItems(service.getAllBets());
    }

    private void away(ClickEvent<Button> buttonClickEvent) {
        typeValue(typeId,homeTeam,awayTeam,eventDate,oddHome,"AWAY_WIN");
    }

    private void draw(ClickEvent clickEvent) {
        typeValue(typeId,homeTeam,awayTeam,eventDate,oddHome,"DRAW");
    }


    public void home(ClickEvent clickEvent) {
        typeValue(typeId,homeTeam,awayTeam,eventDate,oddHome,"HOME_WIN");
    }

    public void typeValue(int typeId, String homeTeam, String awayTeam, String eventDate, Double odd,String yourType){
        Type type = new Type();
        type.setTypeId(typeId);
        type.setHomeTeam(homeTeam);
        type.setAwayTeam(awayTeam);
        type.setEventDate(eventDate);
        type.setOdd(odd);
        type.setYourType(yourType);
        Set<Betslip> betslips = new HashSet<>();
        type.setBetslips(betslips);
        Set<Ticket> tickets = new HashSet<>();
        type.setTickets(tickets);
        Type createdType = service.createType(type);
        service.addTypeToBetslip(createdType.getTypeId());
    }

}
