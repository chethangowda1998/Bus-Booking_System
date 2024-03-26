package com.booking.service.impl;

import com.booking.entities.Route;
import com.booking.payload.RouteDTO;
import com.booking.repository.RouteRepository;
import com.booking.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteServiceImpl implements RouteService {
    @Autowired
    private RouteRepository routeRepository;

    public RouteServiceImpl(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @Override
    public RouteDTO save(RouteDTO routeDTO) {
        Route route = dtoTORoute(routeDTO);
        Route saved = routeRepository.save(route);
        return RouteTOdto(saved);




        }
    private Route dtoTORoute(RouteDTO routeDTO){
        Route route=new Route();
        route.setId(routeDTO.getId());
        route.setOrigin(routeDTO.getOrigin());
        route.setDestination(routeDTO.getDestination());
        route.setDistance(routeDTO.getDistance());
        return route;
    }
    private RouteDTO RouteTOdto(Route route){
        RouteDTO routeDTO=new RouteDTO();
        routeDTO.setId(route.getId());
        routeDTO.setDestination(route.getDestination());
        routeDTO.setDistance(route.getDistance());
        routeDTO.setOrigin(route.getOrigin());
        return routeDTO;
    }
}
