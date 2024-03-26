package com.booking.service.impl;

import com.booking.entities.BusOperator;
import com.booking.payload.BusOperatorDTO;
import com.booking.repository.BusOperatorRepository;
import com.booking.service.BusOperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusOperatorServiceImpl implements BusOperatorService {

    private final BusOperatorRepository busOperatorRepository;

    @Autowired
    public BusOperatorServiceImpl(BusOperatorRepository busOperatorRepository) {
        this.busOperatorRepository = busOperatorRepository;
    }

    @Override
    public BusOperatorDTO saveBusOperator(BusOperatorDTO busOperatorDto) {
        BusOperator busOperator = dtoToBusOperator(busOperatorDto);
        BusOperator savedBusOperator = busOperatorRepository.save(busOperator);
        return entityToDto(savedBusOperator);
    }

    private BusOperator dtoToBusOperator(BusOperatorDTO dto) {
        BusOperator busOperator = new BusOperator();
        busOperator.setOperatorName(dto.getOperatorName());
        busOperator.setContactEmail(dto.getContactEmail());
        busOperator.setContactPhone(dto.getContactPhone());
        busOperator.setLogoUrl(dto.getLogoUrl());
        // Set other properties as needed
        return busOperator;
    }

    private BusOperatorDTO entityToDto(BusOperator busOperator) {
        BusOperatorDTO busOperatorDto = new BusOperatorDTO();
        busOperatorDto.setId(busOperator.getId());
        busOperatorDto.setOperatorName(busOperator.getOperatorName());
        busOperatorDto.setContactEmail(busOperator.getContactEmail());
        busOperatorDto.setContactPhone(busOperator.getContactPhone());
        busOperatorDto.setLogoUrl(busOperator.getLogoUrl());
        // Set other properties as needed
        return busOperatorDto;
    }
}
