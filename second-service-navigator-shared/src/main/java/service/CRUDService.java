package service;


import dto.ParamBeanDTOImpl;
import dto.RequestRouteDTOImpl;
import dto.RouteDTOImpl;
import dto.RoutesList;
import exceptions.CRUDServiceException;
import model.Routeeeeeeee;

public interface CRUDService {
    RouteDTOImpl getRouteById(Integer id, String token, String uri) throws CRUDServiceException;
    void createRoute(RequestRouteDTOImpl routeDTO, String token, String uri) throws CRUDServiceException;
    RoutesList findRoutesBy(ParamBeanDTOImpl paramBeanDTO, String token, String uri)throws CRUDServiceException;
}
