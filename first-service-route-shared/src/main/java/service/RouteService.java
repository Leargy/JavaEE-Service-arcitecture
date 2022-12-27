package service;


import dto.ParamBeanDTOImpl;
import dto.RequestRouteDTOImpl;
import dto.RouteDTOImpl;
import exceptions.RouteServiceException;
import model.Route;

import javax.jws.WebService;
import java.util.List;

@WebService
public interface RouteService {
    Route createRoute(RequestRouteDTOImpl routeDTO) throws RouteServiceException;
    boolean deleteRoute(Integer routeId) throws RouteServiceException;
    boolean updateRoute(Integer routeId, RequestRouteDTOImpl routeDTO) throws RouteServiceException;
    List<Route> findRouteBySpecifiedFields(ParamBeanDTOImpl paramBeanDTO) throws RouteServiceException;
    Route findRouteById(Integer routeId) throws RouteServiceException;
    Float getDistanceSum() throws RouteServiceException;
    Route getRouteWithMaxCoordinates();

    Integer getNumRoutesWithDistanceLessThan(Float distanceValue);

    RouteDTOImpl routeToDTO(Route route);

    Integer countRouteBySpecifiedFields(ParamBeanDTOImpl paramBeanDTO) throws RouteServiceException;
}
