package com.booking.controller;

import com.booking.payload.RouteDTO;
import com.booking.service.RouteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bus/route")
public class RouteController {
   private RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @PostMapping("/route")
    public ResponseEntity<RouteDTO>craeteRoute(@RequestBody RouteDTO routeDTO) {
        RouteDTO routeDTO1=routeService.save(routeDTO);
        return new ResponseEntity<>(routeDTO1, HttpStatus.CREATED);


    }
}
