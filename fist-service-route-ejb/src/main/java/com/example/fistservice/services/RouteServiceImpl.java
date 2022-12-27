package com.example.fistservice.services;


import com.example.fistservice.repository.interfaces.RouteRepository;
import com.example.fistservice.services.interfaces.CoordinateService;
import com.example.fistservice.services.interfaces.LocationFromService;
import com.example.fistservice.services.interfaces.LocationToService;
import dto.ParamBeanDTOImpl;
import dto.RequestRouteDTOImpl;
import dto.RouteDTOImpl;
import exceptions.RouteServiceException;
import model.Coordinates;
import model.LocationFrom;
import model.LocationTo;
import model.Route;
import service.RouteService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jws.WebService;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
@WebService(endpointInterface = "service.RouteService", serviceName = "routes")
public class RouteServiceImpl implements RouteService {

    @Inject
    private RouteRepository routeRepository;
    @Inject
    private CoordinateService coordinateService;
    @Inject
    private LocationFromService locationFromService;
    @Inject
    private LocationToService locationToService;

    @Override
    public Route createRoute(RequestRouteDTOImpl routeDTO) throws RouteServiceException {

        LocationTo locationTo = locationToService.getLocationToIfExists(routeDTO.getTo());
        LocationFrom locationFrom = locationFromService.getLocationByIfExists(routeDTO.getFrom());

        Route route = new Route();
        if(locationTo != null) {
            route.setTo(locationTo);
        }else {
            route.setTo(new LocationTo(routeDTO.getTo().getX(), routeDTO.getTo().getY(), routeDTO.getTo().getZ()));
        }

        if(locationFrom != null) {
            route.setFrom(locationFrom);
        }else {
            route.setFrom(new LocationFrom(routeDTO.getFrom().getX(),routeDTO.getFrom().getY(), routeDTO.getFrom().getZ()));
        }

        route.setName(routeDTO.getName());
        route.setCoordinates(new Coordinates(routeDTO.getCoordinates().getX(), routeDTO.getCoordinates().getY()));
        route.setDistance(routeDTO.getDistance());
        route.setCreationDate(LocalDate.now());

        routeRepository.create(route);
        return route;
    }

    @Override
    public boolean deleteRoute(Integer routeId) throws RouteServiceException {
        ParamBeanDTOImpl paramBeanDTO = new ParamBeanDTOImpl();
        paramBeanDTO.setId(routeId);
        List<Route> routes = routeRepository.findBy(paramBeanDTO);
        if(routes.size() != 0) {
            routeRepository.delete(routes.get(0));
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean updateRoute(Integer routeId, RequestRouteDTOImpl routeDTO) throws RouteServiceException {
        Route route = findRouteById(routeId);
        if(route == null) {
            return false;
        }

        LocationFrom foundLocationFrom = locationFromService.getLocationByIfExists(routeDTO.getFrom());
        LocationTo foundLocationTo = locationToService.getLocationToIfExists(routeDTO.getTo());

        if(foundLocationFrom != null && !foundLocationFrom.equals(route.getFrom())) {
            locationFromService.detach(route.getFrom());
            route.setFrom(foundLocationFrom);
        }else if(foundLocationFrom == null) {
            locationFromService.detach(route.getFrom());
            route.setFrom(new LocationFrom(routeDTO.getFrom().getX(),routeDTO.getFrom().getY(), routeDTO.getFrom().getZ()));
        }

        if(foundLocationTo != null && !foundLocationTo.equals(route.getTo())) {
            locationToService.detach(route.getTo());
            route.setTo(foundLocationTo);
        }else if(foundLocationFrom == null) {
            locationToService.detach(route.getTo());
            route.setTo(new LocationTo(routeDTO.getTo().getX(), routeDTO.getTo().getY(), routeDTO.getTo().getZ()));
        }

        route.setName(routeDTO.getName());
        route.setCoordinates(new Coordinates(routeDTO.getCoordinates().getX(), routeDTO.getCoordinates().getY()));
        route.setDistance(routeDTO.getDistance());
        routeRepository.update(route);
        return true;
    }

    @Override
    public List<Route> findRouteBySpecifiedFields(ParamBeanDTOImpl paramBeanDTO) throws RouteServiceException {
        List<Route> routList;
        try {
            routList = routeRepository.findBy(paramBeanDTO);
        }catch (RuntimeException ex) {
            throw new RouteServiceException(ex.getMessage());
        }
        return routList;
    }

    @Override
    public Integer countRouteBySpecifiedFields(ParamBeanDTOImpl paramBeanDTO) throws RouteServiceException {
        List<Route> routList;
        Long pageSize = paramBeanDTO.getPageSize();
        Long offset = paramBeanDTO.getOffSet();
        paramBeanDTO.setPageSize(null);
        paramBeanDTO.setOffSet(null);
        try {
            routList = routeRepository.findBy(paramBeanDTO);
        }catch (RuntimeException ex) {
            throw new RouteServiceException(ex.getMessage());
        }
        paramBeanDTO.setPageSize(pageSize);
        paramBeanDTO.setOffSet(offset);
        return routList.size();
    }

    @Override
    public Route findRouteById(Integer routeId) throws RouteServiceException {
        Route rout;
        ParamBeanDTOImpl paramBeanDTO = new ParamBeanDTOImpl();
        paramBeanDTO.setId(routeId);
        try {
            List<Route> routList = routeRepository.findBy(paramBeanDTO);
            if(routList.size() == 0) return null;
            rout = routList.get(0);
        }catch (RuntimeException ex) {
            throw new RouteServiceException(ex.getMessage());
        }
        return rout;
    }

    @Override
    public Float getDistanceSum() throws RouteServiceException {
        return routeRepository.getRoutesDistanceSum();
    }

    @Override
    public Route getRouteWithMaxCoordinates() {
        Coordinates maxCoordinates = coordinateService.getIdMaxCoordinates();
        if(maxCoordinates == null) return null;
        return routeRepository.findRouteByCoordinatesId(maxCoordinates.getId());
    }

    @Override
    public Integer getNumRoutesWithDistanceLessThan(Float distanceValue) {
        return routeRepository.getRoutesDistanceLess(distanceValue).size();
    }

    @Override
    public RouteDTOImpl routeToDTO(Route route) {
        RouteDTOImpl routeDTO = new RouteDTOImpl();

        routeDTO.setId(route.getId());
        routeDTO.setName(route.getName());
        routeDTO.setCreationDate(route.getCreationDate());
        routeDTO.setFrom(locationFromService.locationToToDTO(route.getFrom()));
        routeDTO.setTo(locationToService.locationToToDTO(route.getTo()));
        routeDTO.setCoordinates(coordinateService.coordinatesToDTO(route.getCoordinates()));
        routeDTO.setDistance(route.getDistance());

        return routeDTO;
    }
}
