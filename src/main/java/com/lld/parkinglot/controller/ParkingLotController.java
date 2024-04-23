package com.lld.parkinglot.controller;

import com.lld.parkinglot.dtos.CreateParkingLotRequest;
import com.lld.parkinglot.models.ParkingLot;
import com.lld.parkinglot.service.ParkingLotService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


// 1. Request validation
// 2. Data transformation
@RestController
@RequestMapping("/api/v1/parking-lot")
@AllArgsConstructor
public class ParkingLotController {

    //POST
    ///api/vi/parking-lot/
    private ParkingLotService parkingLotService;

    @PostMapping
    public ParkingLot createParkingLot(@RequestBody CreateParkingLotRequest createParkingLotRequest){

        validate(createParkingLotRequest);
        ParkingLot parkingLot = transform(createParkingLotRequest);
        return parkingLotService.create(parkingLot);

    }

    @GetMapping("/{id}")
    public ParkingLot getParkingLot( @PathVariable("id") Integer id){
        return ParkingLot.builder().id(1l).address("Pune").build();

    }

    private void validate(CreateParkingLotRequest createParkingLotRequest){
       if(createParkingLotRequest.getNumberOfEntryGates() == null){
           throw new RuntimeException("Parking must have gates.");
       }
    }

    private ParkingLot transform(CreateParkingLotRequest createParkingLotRequest){
        return createParkingLotRequest.toParkingLot();
    }
}
