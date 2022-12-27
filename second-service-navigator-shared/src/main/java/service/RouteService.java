package service;


import dto.RequestRouteDTOImpl;
import dto.RouteDTOImpl;
import model.Routeeeeeeee;

public interface RouteService {
    RequestRouteDTOImpl formRouteBetween(RouteDTOImpl routeDTOTo, RouteDTOImpl routeDTOFrom, Float distance);
}
