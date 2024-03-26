package com.booking.controller;

import com.booking.payload.ScheduleDTO;
import com.booking.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }


    @PostMapping("/scheduled")
    public ResponseEntity<ScheduleDTO>createSchedule(@RequestBody ScheduleDTO scheduleDTO){
        ScheduleDTO saved = scheduleService.save(scheduleDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);

    }
}
