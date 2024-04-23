package com.lld.parkinglot.controller;


import com.lld.parkinglot.dtos.CreateTicketRequest;
import com.lld.parkinglot.exception.ParkingSlotNotAvailableException;
import com.lld.parkinglot.exception.VehicleNumberMissingException;
import com.lld.parkinglot.models.Ticket;
import com.lld.parkinglot.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/ticket")
public class TicketController {

    private TicketService ticketService;

    @PostMapping
    public Ticket createTicket(@RequestBody CreateTicketRequest request) throws ParkingSlotNotAvailableException {
        validate(request);
        return ticketService.createTicket(request);
    }

    private void validate(CreateTicketRequest request) {
        if(request.getVehicleNumber() == null){
            throw new VehicleNumberMissingException("Vehicle number is missing.");
        }
    }
}
