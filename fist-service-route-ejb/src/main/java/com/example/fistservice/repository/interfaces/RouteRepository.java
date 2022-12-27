package com.example.fistservice.repository.interfaces;


import dto.ParamBeanDTOImpl;
import model.Route;

import java.util.List;

public interface RouteRepository extends CRUDCommands<Route,Integer>{
    List<Route> findBy(ParamBeanDTOImpl paramBean) throws RuntimeException;
    Route findRouteByCoordinatesId(Integer id);
    Float getRoutesDistanceSum();

    List<Route> getRoutesDistanceLess(Float distanceValue) throws RuntimeException;
}
