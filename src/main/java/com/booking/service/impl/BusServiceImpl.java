package com.booking.service.impl;

import com.booking.entities.Bus;
import com.booking.payload.BusDTO;
import com.booking.repository.BusOperatorRepository;
import com.booking.repository.BusRepository;
import com.booking.service.BusService;
import org.springframework.stereotype.Service;

@Service
public class BusServiceImpl implements BusService {
    private BusRepository busRepository;
    private BusOperatorRepository busOperatorRepository;

    public BusServiceImpl(BusRepository busRepository, BusOperatorRepository busOperatorRepository) {
        this.busRepository = busRepository;
        this.busOperatorRepository = busOperatorRepository;
    }

    @Override
    public BusDTO saveBus(BusDTO busDTO) {
        Bus bus = dtoToBus(busDTO);
        Bus savedBus = busRepository.save(bus);
        return entityToDto(savedBus);
    }

    private Bus dtoToBus(BusDTO busDTO) {
        Bus bus = new Bus();
        bus.setBusType(busDTO.getBusType());
        bus.setTotalSeats(busDTO.getTotalSeats());
        bus.setAmenities(busDTO.getAmenities());
      bus.setId(busDTO.getId());


        return bus;
    }

    private BusDTO entityToDto(Bus bus) {
        BusDTO busDTO = new BusDTO();
        busDTO.setBusType(bus.getBusType());
        busDTO.setTotalSeats(bus.getTotalSeats());
        busDTO.setAmenities(bus.getAmenities());
        busDTO.setId(bus.getId());

        return busDTO;
    }
}
