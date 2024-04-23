package com.lld.parkinglot.service;

import com.lld.parkinglot.dtos.CreateTicketRequest;
import com.lld.parkinglot.exception.ParkingSlotNotAvailableException;
import com.lld.parkinglot.models.ParkingSpot;
import com.lld.parkinglot.models.SpotStatus;
import com.lld.parkinglot.models.Ticket;
import com.lld.parkinglot.models.VehicleType;
import com.lld.parkinglot.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class TicketService {

    private VehicleService vehicleService;
    private ParkingSpotService parkingSpotService;
    private TicketRepository ticketRepository;
    public Ticket createTicket(CreateTicketRequest request) throws ParkingSlotNotAvailableException {

        //check if parking lot is full
        ParkingSpot parkingSpot = parkingSpotService.allocateSlot(request.getParkingLotId(), request.getVehicleType());
        if(parkingSpot == null){
            throw new ParkingSlotNotAvailableException("Parking slot not available.");
        }
        //update slot status
        parkingSpot.setSpotStatus(SpotStatus.OCCUPIED);

        //Persist slot
        parkingSpotService.update(parkingSpot);

        //create ticket and persist ticket
        Ticket ticket = Ticket
                .builder()
                .entryTime(new Date())
                .spotId(parkingSpot.getSpotId())
                .entryGateId(request.getEntryGateId())
                .vehicle(vehicleService.findOrCreate(request.getVehicleNumber(), request.getVehicleType()))
                .build();
        return ticketRepository.save(ticket);
    }
}
