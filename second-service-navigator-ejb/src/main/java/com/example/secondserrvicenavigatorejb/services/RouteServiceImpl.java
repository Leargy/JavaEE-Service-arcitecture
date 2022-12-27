package com.example.secondserrvicenavigatorejb.services;

import dto.CoordinatesDTOImpl;
import dto.RequestRouteDTOImpl;
import dto.RouteDTOImpl;
import model.Routeeeeeeee;
import org.jboss.ejb3.annotation.Pool;
import service.RouteService;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(RouteService.class)
@Pool(value="routePool")
public class RouteServiceImpl implements RouteService {


    @Override
    public RequestRouteDTOImpl formRouteBetween(RouteDTOImpl routeDTOFrom, RouteDTOImpl routeDTOTo, Float distance) {
        RequestRouteDTOImpl routeDTO = new RequestRouteDTOImpl();

        CoordinatesDTOImpl coordinatesDTO = new CoordinatesDTOImpl();
        CoordinatesDTOImpl coordinatesDTOFrom = routeDTOFrom.getCoordinates();
        CoordinatesDTOImpl coordinatesDTOTo = routeDTOTo.getCoordinates();
        coordinatesDTO.setX(coordinatesDTOFrom.getX()/2 + coordinatesDTOTo.getX()/2);
        coordinatesDTO.setY(coordinatesDTOFrom.getY()/2 + coordinatesDTOTo.getY()/2);

        routeDTO.setCoordinates(coordinatesDTO);
        routeDTO.setDistance(distance);

        routeDTO.setFrom(routeDTOFrom.getFrom());
        routeDTO.setTo(routeDTOTo.getTo());
        routeDTO.setName(routeDTOFrom.getName() + "-" + routeDTOTo.getName());
        return routeDTO;
    }
}
