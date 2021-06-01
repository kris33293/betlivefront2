package com.betlive.front;

import javax.annotation.PostConstruct;
import javax.ws.rs.client.*;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.betlive.front.domain.Bet;
import com.betlive.front.domain.Betslip;
import com.betlive.front.domain.Type;
import com.betlive.front.domain.User;
import org.atmosphere.inject.annotation.ApplicationScoped;
import org.springframework.stereotype.Service;

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
    private WebTarget targetTicket;
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
                .readEntity(new GenericType<List<User>>() {
                });

    }
}
