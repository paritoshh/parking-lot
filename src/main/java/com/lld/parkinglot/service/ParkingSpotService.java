package com.lld.parkinglot.service;

import com.lld.parkinglot.models.ParkingLot;
import com.lld.parkinglot.models.ParkingSpot;
import com.lld.parkinglot.models.VehicleType;
import com.lld.parkinglot.repository.ParkingSpotRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ParkingSpotService {

    private ParkingSpotRepository parkingSpotRepository = new ParkingSpotRepository();
    public ParkingSpot createParkingSpot(){
    return null;
    }

    public ParkingSpot allocateSlot(Long parkingLotId, VehicleType vehicleType) {
        return parkingSpotRepository.findOneByVehicleTypeAndStatusAvailable(vehicleType);
    }
    public void createParkingSpots(ParkingLot parkingLot){
        List<ParkingSpot> spots = parkingLot.getFloors()
                .stream().flatMap(f->f.getSpots().stream())
                .collect(Collectors.toList());
        parkingSpotRepository.saveAll(spots);
    }

    public List<ParkingSpot> getParkingSpots(Long parkingLotId){
        return parkingSpotRepository.findAllByParkingLotId(parkingLotId);
    }

    public ParkingSpot getParkingSpot(Long id){
        return parkingSpotRepository.findOneById(id);
    }

    public ParkingSpot update(ParkingSpot filledSpot){
        return parkingSpotRepository.update(filledSpot);
    }
}
