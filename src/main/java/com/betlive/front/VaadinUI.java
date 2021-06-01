package com.betlive.front;

import com.betlive.front.domain.Bet;
import com.betlive.front.domain.Type;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinRequest;
import org.springframework.beans.factory.annotation.Autowired;


import javax.inject.Inject;

@PageTitle("BetLive v1")
@Route("")
public class VaadinUI extends VerticalLayout {

    JsonService service;

    private int typeId;
    private String homeTeam,awayTeam,eventDate;
    private Double oddDraw,oddHome,oddAway;


    public VaadinUI(JsonService service) {
    this.service = service;

        Button home = new Button("Home",this::home);

        Button draw = new Button("Draw");

        Button away = new Button("Away");

        HorizontalLayout typeGrid = new HorizontalLayout(
                home,draw,away
        );

        Grid<Type> betslip = new Grid<>();
        Grid<Bet> bets = new Grid<>();

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


        betslip.addColumn(type -> type.getHomeTeam()).setFlexGrow(1).setHeader("Home Team");
        betslip.addColumn(type -> type.getAwayTeam()).setFlexGrow(1).setHeader("Away Team");
        betslip.addColumn(type -> type.getOdd()).setFlexGrow(1).setHeader("Odd");

        betslip.setItems(service.getAllTypesFromBetslip().getTypes());
        bets.setItems(service.getAllBets());
        add(bets,typeGrid,betslip);


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
        Type createdType = service.createType(type);
        service.addTypeToBetslip(createdType.getTypeId());
    }

}
