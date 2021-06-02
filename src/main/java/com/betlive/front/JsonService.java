package com.betlive.front;

import javax.annotation.PostConstruct;
import javax.ws.rs.client.*;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.betlive.front.domain.*;
import com.vaadin.flow.templatemodel.BeanModelType;
import org.atmosphere.inject.annotation.ApplicationScoped;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@ApplicationScoped
public class JsonService {

    private Client client;
    private Client clientPost;
    private WebTarget targetBetslip;
    private WebTarget targetAddBetslip;
    private WebTarget targetBet;
    private WebTarget targetUserTicket;
    private WebTarget targetType;
    private WebTarget targetUser;

    @PostConstruct
    protected void init() {
        client = ClientBuilder.newClient();

        targetUser = client.target(
                "http://localhost:8080/v1/user/getAllUsers");

        targetBet = client.target(
                "http://localhost:8080/v1/bet/getAllBets");
    }

    public List<Type> getAllTypesFromBetslip() {
        targetBetslip = client.target(
                "http://localhost:8080/v1/betslip/getAllTypes").queryParam("Content-Type","application/json");
        return  targetBetslip.request(MediaType.APPLICATION_JSON)
                .get(Response.class)
                .readEntity(new GenericType<List<Type>>() {
                });

    }

    public Type createType(Type type) {
        targetType = client.target(
                "http://localhost:8080/v1/type/createType");
        Invocation.Builder invocationBuilder =  targetType.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(type, MediaType.APPLICATION_JSON));
        return  response.readEntity(Type.class);
    }

    public Betslip findBetslip(int betslipId) {
        targetAddBetslip = clientPost.target(
                "http://localhost:8080/v1/betslip/getBetslip")
                .queryParam("betslipId",1);
        return  targetAddBetslip.request(MediaType.APPLICATION_JSON)
                .get(Betslip.class);

    }

    public List<Bet> getAllBets() {
        return  targetBet.request(MediaType.APPLICATION_JSON)
                .get(Response.class)
                .readEntity(new GenericType<List<Bet>>() {
                });

    }

    public void addTypeToBetslip(int typeId) {
        clientPost = ClientBuilder.newClient();
        targetAddBetslip = clientPost.target(
                "http://localhost:8080/v1/betslip/addType")
                .queryParam("betslipId",1)
                .queryParam("typeId",typeId);
        Invocation.Builder invocationBuilder =  targetAddBetslip.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(Type.class, MediaType.APPLICATION_JSON));

        System.out.println(response.getEntity());

    }

    public List<User> getAllUsers() {
        return  targetUser.request(MediaType.APPLICATION_JSON)
                .get(Response.class)
                .readEntity(new GenericType<>() {
                });

    }

    public void makeDeposit(BigDecimal ammount, int userId) {
        clientPost = ClientBuilder.newClient();
        targetAddBetslip = clientPost.target(
                "http://localhost:8080/v1/user/makeDeposit")
                .queryParam("userId",userId)
                .queryParam("ammount", ammount);
        Invocation.Builder invocationBuilder =  targetAddBetslip.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.put(Entity.entity(User.class, MediaType.APPLICATION_JSON));

        System.out.println(response.getEntity());

    }

    public void withdrawMoney(BigDecimal ammount, int userId) {
        clientPost = ClientBuilder.newClient();
        targetAddBetslip = clientPost.target(
                "http://localhost:8080/v1/user/withdrawMoney")
                .queryParam("userId",userId)
                .queryParam("ammount", ammount);
        Invocation.Builder invocationBuilder =  targetAddBetslip.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.put(Entity.entity(User.class, MediaType.APPLICATION_JSON));

        System.out.println(response.getEntity());

    }

    public List<Ticket> getAllUserTickets(int userId) {
        targetUserTicket = client.target(
                "http://localhost:8080/v1/tickets/getUserTickets").queryParam("userId",userId);
        return  targetUserTicket.request(MediaType.APPLICATION_JSON)
                .get(Response.class)
                .readEntity(new GenericType<List<Ticket>>() {
                });

    }

    public Ticket createTicket(int betslipId) {
        targetAddBetslip = clientPost.target(
                "http://localhost:8080/v1/betslip/createTicket")
                .queryParam("betslipId",betslipId);
        Invocation.Builder invocationBuilder =  targetAddBetslip.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(Ticket.class, MediaType.APPLICATION_JSON));

        return  response.readEntity(Ticket.class);
    }

    public Betslip createAndSaveBetslip(Betslip betslip) {
        targetType = client.target(
                "http://localhost:8080/v1/betslip/saveBetslip");
        Invocation.Builder invocationBuilder =  targetType.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(betslip, MediaType.APPLICATION_JSON));
        return  response.readEntity(Betslip.class);

    }

    public void saveMatches() {
        clientPost = ClientBuilder.newClient();
        targetAddBetslip = clientPost.target(
                "http://localhost:8080/v1/bet/savePremierleagueMatches");
        Invocation.Builder invocationBuilder =  targetAddBetslip.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(Bet.class, MediaType.APPLICATION_JSON));

        System.out.println(response.getEntity());

    }
    public void deleteMatches() {
        clientPost = ClientBuilder.newClient();
        targetAddBetslip = clientPost.target(
                "http://localhost:8080/v1/bet/deleteAllBets");
        Invocation.Builder invocationBuilder =  targetAddBetslip.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.delete();

        System.out.println(response.getEntity());

    }

    public User createUser() {
        User createdUser = new User();
        createdUser.setUserId(1);
        createdUser.setBalance(new BigDecimal(150));
        createdUser.setUserName("FIRST USER");
        createdUser.setTickets(new ArrayList<>());
        createdUser.setBetslips(new ArrayList<>());
        targetType = client.target(
                "http://localhost:8080/v1/user/createUser");
        Invocation.Builder invocationBuilder =  targetType.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(createdUser, MediaType.APPLICATION_JSON));
        return  response.readEntity(User.class);
    }


}
