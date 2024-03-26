package com.booking.service.impl;

import com.booking.entities.Schedule;
import com.booking.payload.ScheduleDTO;
import com.booking.repository.ScheduleRepository;
import com.booking.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleDTO save(ScheduleDTO scheduleDTO) {
        Schedule schedule = dtoTOSchedule(scheduleDTO);
        Schedule saved = scheduleRepository.save(schedule);
        return ScheduleTOdto(saved);
    }

    private Schedule dtoTOSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        schedule.setId(scheduleDTO.getId());
        schedule.setPrice(scheduleDTO.getPrice());
        schedule.setArrivalTime(scheduleDTO.getArrivalTime());
        return schedule;
    }

    private ScheduleDTO ScheduleTOdto(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(schedule.getId());
        scheduleDTO.setBusId(schedule.getBus().getId());
        scheduleDTO.setRouteId(schedule.getRoute().getId());
        scheduleDTO.setDepartureTime(schedule.getDepartureTime());
        scheduleDTO.setArrivalTime(schedule.getArrivalTime());
        scheduleDTO.setPrice(schedule.getPrice());

        return scheduleDTO;
    }
}