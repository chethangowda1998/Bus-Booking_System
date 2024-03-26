package com.booking.controller;

import com.booking.payload.BusDTO;
import com.booking.service.BusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bus")
public class BusController {
    private BusService busService;

    public BusController(BusService busService) {
        this.busService = busService;
    }
    @PostMapping("/Bus")
    public ResponseEntity<BusDTO>createBus(@RequestBody BusDTO busDTO){
        BusDTO busDTO1 = busService.saveBus(busDTO);
        return new ResponseEntity<>(busDTO1, HttpStatus.CREATED);

    }
}
