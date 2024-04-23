package com.lld.parkinglot.service;

import com.lld.parkinglot.models.Vehicle;
import com.lld.parkinglot.models.VehicleType;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    public Vehicle findOrCreate(String vehicleNumber, VehicleType vehicleType){
        return Vehicle
                .builder()
                .licenseNumber(vehicleNumber)
                .type(vehicleType)
                .build();
    }
}
