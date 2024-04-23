package com.lld.parkinglot.repository;

import com.lld.parkinglot.models.Ticket;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TicketRepository {
    private List<Ticket> tickets = new ArrayList<>();

    public Ticket save(Ticket ticket){
        tickets.add(ticket);
        return ticket;
    }
}
