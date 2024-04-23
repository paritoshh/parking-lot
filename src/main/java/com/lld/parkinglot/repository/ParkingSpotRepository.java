package com.lld.parkinglot.repository;

import com.lld.parkinglot.models.ParkingSpot;
import com.lld.parkinglot.models.SpotStatus;
import com.lld.parkinglot.models.VehicleType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ParkingSpotRepository {

    List<ParkingSpot> parkingSpots = new ArrayList<>();
    public ParkingSpot save(ParkingSpot parkingSpot){
        parkingSpots.add(parkingSpot);
        return parkingSpot;
    }
    public ParkingSpot findOneByVehicleTypeAndStatusAvailable(VehicleType vehicleType) {

        Optional<ParkingSpot> first = parkingSpots
                .stream()
                .filter(s -> s.getSpotStatus() == SpotStatus.AVAILABLE && s.getVehicleType() == vehicleType)
                .findFirst();
        if(first.isPresent()){
            return first.get();
        }
        return null;
    }

    public ParkingSpot update(ParkingSpot filledSpot){
        ParkingSpot currentSpot = parkingSpots
                .stream()
                .filter(s->s.getSpotId().equals(filledSpot.getSpotId()))
                .findFirst()
                .get();
        if(currentSpot == null){
            throw new RuntimeException("Invalid Parking Spot.");
        }
        parkingSpots.remove(currentSpot);
        parkingSpots.add(filledSpot);
        return filledSpot;
    }

    public void saveAll(List<ParkingSpot> spots){
        parkingSpots.addAll(spots);
    }
    public List<ParkingSpot> findAllByParkingLotId(Long id){
        return parkingSpots;
    }
    public ParkingSpot findOneById(Long id){
        return parkingSpots.stream()
                .filter(s->s.getSpotId().equals(id))
                .findFirst()
                .get();
    }
}
