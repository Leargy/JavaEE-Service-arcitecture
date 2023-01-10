package com.example.secondservicenavigatorrest.services;


import dto.*;
import exceptions.CRUDServiceException;
import model.Routeeeeeeee;
import service.CRUDService;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ManagedBean
public class CRUDServiceImpl implements CRUDService {

    private Client client;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
    }

    @Override
    public RouteDTOImpl getRouteById(Integer id, String token, String uri) throws CRUDServiceException {
        WebTarget target = client.target(uri);
        WebTarget service;
        Routeeeeeeee routeDTO;
        Response response;
        try {
            service = target.path("routes/" + id);
            response = service.request().header("Authorization", token).get();
            if(response.getStatus() != 200) {
                throw new CRUDServiceException(response.readEntity(String.class), response.getStatus());
            }
            routeDTO = response.readEntity(Routeeeeeeee.class);

        } catch (ProcessingException e) {
            throw new CRUDServiceException(e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        }


        return new RouteDTOImpl(routeDTO.getId(),routeDTO.getName(),routeDTO.getCreationDate(), new CoordinatesDTOImpl(routeDTO.getCoordinates().getX(),routeDTO.getCoordinates().getY()),new LocationFromDTOImpl(routeDTO.getFrom().getX(),routeDTO.getFrom().getY(),routeDTO.getFrom().getZ()),new LocationToDTOImpl(routeDTO.getTo().getX(),routeDTO.getTo().getY(),routeDTO.getTo().getZ()),routeDTO.getDistance());
    }

    @Override
    public void createRoute(RequestRouteDTOImpl routeDTO, String token, String uri) throws CRUDServiceException {
        WebTarget target = client.target(uri);
        WebTarget service;
        Response response;
        try {
            service = target.path("routes");
            response = service.request().header("Authorization", token).header("Content-Type", "application/json").post(Entity.json(routeDTO));
            if(response.getStatus() != 201) {
                throw new CRUDServiceException(response.readEntity(String.class), response.getStatus());
            }
        } catch (ProcessingException e) {
            throw new CRUDServiceException(e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        }
    }

    @Override
    public RoutesList findRoutesBy(ParamBeanDTOImpl paramBeanDTO, String token, String uri) throws CRUDServiceException {
        WebTarget target = client.target(uri);
        WebTarget service;
        Response response = null;
        Invocation invocation;
        try {
            service = target.path("routes/specific");

            service = service.queryParam("id", paramBeanDTO.getId());
            service = service.queryParam("name", paramBeanDTO.getName());
            service = service.queryParam("coordinateX", paramBeanDTO.getCoordinatesX());
            service = service.queryParam("coordinateY", paramBeanDTO.getCoordinatesY());
            service = service.queryParam("creationDate", paramBeanDTO.getCreationDate());
            service = service.queryParam("fromX", paramBeanDTO.getFromX());
            service = service.queryParam("fromY", paramBeanDTO.getFromY());
            service = service.queryParam("fromZ", paramBeanDTO.getFromZ());
            service = service.queryParam("toX", paramBeanDTO.getToX());
            service = service.queryParam("toY", paramBeanDTO.getToY());
            service = service.queryParam("toZ", paramBeanDTO.getToZ());
            service = service.queryParam("distance", paramBeanDTO.getDistance());
            service = service.queryParam("orderBy", paramBeanDTO.getOrderBy());
            service = service.queryParam("pageSize", paramBeanDTO.getPageSize());
            service = service.queryParam("offSet", paramBeanDTO.getOffSet());
            service = service.queryParam("isDecr", paramBeanDTO.isDecr());

            response = service.request().accept( MediaType.APPLICATION_JSON ).header("Authorization", token).get();
            if(response.getStatus() != 200) {
                throw new CRUDServiceException(response.readEntity(String.class), response.getStatus());
            }
        } catch (ProcessingException e) {
            throw new CRUDServiceException(e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        }

        return response.readEntity(RoutesList.class);
    }
}
