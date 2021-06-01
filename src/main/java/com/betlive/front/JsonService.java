package com.betlive.front;

import javax.annotation.PostConstruct;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.betlive.front.domain.Bet;
import com.betlive.front.domain.Betslip;
import com.betlive.front.domain.Type;
import org.atmosphere.inject.annotation.ApplicationScoped;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@ApplicationScoped
public class JsonService {

    private Client client;
    private WebTarget targetBetslip;
    private WebTarget targetAddBetslip;
    private WebTarget targetBet;
    private WebTarget targetTicket;
    private WebTarget targetType;
    private WebTarget targetUser;

    @PostConstruct
    protected void init() {
        client = ClientBuilder.newClient();

        targetBetslip = client.target(
                "http://localhost:8080/v1/betslip/getBetslip");

        targetBet = client.target(
                "http://localhost:8080/v1/bet/getAllBets");
    }

    public Betslip getAllTypesFromBetslip() {
        return  targetBetslip.request(MediaType.APPLICATION_JSON)
                .get(Betslip.class);

    }

    public Type createType(Type type) {
        targetType = client.target(
                "http://localhost:8080/v1/type/createType").queryParam("type",type);
        return  targetType.request(MediaType.APPLICATION_JSON)
                .get(Type.class);

    }

    public List<Bet> getAllBets() {
        return  targetBet.request(MediaType.APPLICATION_JSON)
                .get(Response.class)
                .readEntity(new GenericType<List<Bet>>() {
                });

    }

    public void addTypeToBetslip(int typeId) {
        targetAddBetslip = client.target(
                "http://localhost:8080/v1/type/createType")
                .queryParam("betslipId",1)
                .queryParam("typeId",typeId);
        targetAddBetslip.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(Type.class, MediaType.APPLICATION_JSON));
    }
}
