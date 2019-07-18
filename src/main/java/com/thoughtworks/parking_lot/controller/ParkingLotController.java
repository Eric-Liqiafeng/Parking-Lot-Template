package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ParkingLotController {
    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @PostMapping("/parking-lots")
    public ResponseEntity createParkingLots(@RequestBody ParkingLot parkingLot) {
        try{
            ParkingLot newParkingLot = parkingLotRepository.saveAndFlush(parkingLot);
            return ResponseEntity.ok(newParkingLot);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/parking-lots/{id}")
    public ResponseEntity deleteParkingLots(@PathVariable int id){
        int statusCode = parkingLotRepository.deleteAllById(id);
        return ResponseEntity.ok(statusCode);
    }

    @GetMapping("/parking-lots")
    public ResponseEntity getParkingLots(@RequestParam(defaultValue = "all") String parkingLotName){
        if (!parkingLotName.equals("all")) {
            return ResponseEntity.ok(parkingLotRepository.findAllByName(parkingLotName));
        }
        return ResponseEntity.ok(parkingLotRepository.findAll());
    }


}