package com.booking.controller;

import com.booking.payload.BusOperatorDTO;
import com.booking.service.BusOperatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Bus-Booking")
public class BusOperatorController {
    private final BusOperatorService busOperatorService;

    public BusOperatorController(BusOperatorService busOperatorService) {
        this.busOperatorService = busOperatorService;
    }


    @PostMapping("/Operator")
    public ResponseEntity<?> createBusOperator(@RequestBody BusOperatorDTO busOperatorDto) {
        BusOperatorDTO savedBusOperator = busOperatorService.saveBusOperator(busOperatorDto);
        return new ResponseEntity<>(savedBusOperator, HttpStatus.CREATED);
    }
}