package com.example.fistservicerouterest;


import com.example.fistservicerouterest.util.RouteWebService;
import dto.ParamBeanDTOImpl;
import dto.RequestRouteDTOImpl;

import dto.RoutesList;
import dto.validators.ValidatorsFactory;
import exceptions.RouteServiceException;
import model.Route;
import model.Routeeeeeeee;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.ValidationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Path("/routes")
public class BaseOperationsResources {

    @Inject
    private RouteWebService routeWebService;


    @Inject
    private ValidatorsFactory validatorsFactory;

    @GET
    @Path("/{route-id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRouteById(@PathParam("route-id") Integer routeId) {
        Route route = null;
        try {
            route = routeWebService.getRoutesWebServiceImplPort().findRouteById(routeId);
        } catch (RouteServiceException e) {
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
        Routeeeeeeee routeeeeeeee = new Routeeeeeeee(route.getId(),route.getName(), route.getDistance(),route.getCoordinates(),route.getCreationDate(),route.getFrom(),route.getTo());
        if(route != null) {
            return Response.ok().entity(routeeeeeeee).build();
        }else {
            return Response.status(404).entity("Route by specified id " + routeId + "not found").build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createRoute(RequestRouteDTOImpl routeInfo) {

        try {
            validatorsFactory.createRequestRouteValidator().startValidation(routeInfo);
        } catch (ValidationException e) {
            return Response.status(400).entity(e.getMessage()).build();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return Response.status(500).build();
        }

        Route createdRoute = null;
        try {
            createdRoute = routeWebService.getRoutesWebServiceImplPort().createRoute(routeInfo);
        }catch (RouteServiceException e) {
            return Response.status(500).entity(e.getMessage()).build();
        }

        if(createdRoute != null) {
            Routeeeeeeee routeeeeeeee = new Routeeeeeeee(createdRoute.getId(),createdRoute.getName(), createdRoute.getDistance(),createdRoute.getCoordinates(),createdRoute.getCreationDate(),createdRoute.getFrom(),createdRoute.getTo());
            return Response.status(201).entity(routeeeeeeee).build();
        }else {
            return Response.status(401).entity("Route wasn't created").build();
        }
    }

    @GET
    @Path("/specific")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRoutesSpecifiedByFilter(
            @QueryParam("id") Integer id,
            @QueryParam("name") String name,
            @QueryParam("coordinateX") Integer coordinateX,
            @QueryParam("coordinateY") Long coordinateY,
            @QueryParam("creationDate") String creationDate,
            @QueryParam("fromX") Double fromX,
            @QueryParam("fromY") Float fromY,
            @QueryParam("fromZ") Double fromZ,
            @QueryParam("toX") Double toX,
            @QueryParam("toY") Float toY,
            @QueryParam("toZ") Integer toZ,
            @QueryParam("distance") Float distance,
            @QueryParam("orderBy") String orderBy,
            @QueryParam("pageSize") Long pageSize,
            @QueryParam("offSet") Long offSet,
            @QueryParam("isDecr") Boolean isDecr) {

        ParamBeanDTOImpl param = new ParamBeanDTOImpl();
        if(id != null) param.setId(id);
        if(name != null) param.setName(name);
        if(coordinateX != null) param.setCoordinatesX(coordinateX);
        if(coordinateY != null) param.setCoordinatesY(coordinateY);
        if(fromX != null) param.setFromX(fromX);
        if(fromY != null) param.setFromY(fromY);
        if(fromZ != null) param.setFromZ(fromZ);
        if(toX != null) param.setToX(toX);
        if(toY != null) param.setToY(toY);
        if(toZ != null) param.setToZ(toZ);
        if(distance != null) param.setDistance(distance);
        if(orderBy != null) param.setOrderBy(orderBy.split(","));
        if(pageSize != null) param.setPageSize(pageSize);
        if(offSet != null) param.setOffSet(offSet);
        if(creationDate != null) param.setCreationDate(LocalDate.parse(creationDate));
        param.setDecr(isDecr);

        try {
            validatorsFactory.createParamBeanValidator().startValidation(param);
        } catch (ValidationException e) {
            return Response.status(400).entity(e.getMessage()).build();
        } catch (NoSuchMethodException e) {
            return Response.status(500).build();
        }

        List<Route> lr = null;
        Integer sizeForCurrentFilter = null;
        try {
            sizeForCurrentFilter = routeWebService.getRoutesWebServiceImplPort().countRouteBySpecifiedFields(param);
            if(pageSize != null && Math.ceil(sizeForCurrentFilter/pageSize) < offSet) {
                return Response.status(400).entity("Current page selection out of bounds").build();
            }
            lr = routeWebService.getRoutesWebServiceImplPort().findRouteBySpecifiedFields(param);
        } catch (RouteServiceException e) {
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
        List<Routeeeeeeee> reel = new ArrayList<>();
        RoutesList routesList = new RoutesList();
        if(lr != null) {
            for(Route route: lr) {
                reel.add(new Routeeeeeeee(route.getId(),route.getName(), route.getDistance(),route.getCoordinates(),route.getCreationDate(),route.getFrom(),route.getTo()));
            }
            routesList.setRoutes(reel);
            routesList.setTotalSize(sizeForCurrentFilter);
            return Response.ok(routesList).build();
        }else {
            return Response.status(404).entity("Routes by specified params not found").build();
        }

    }

    @PUT
    @Path("/{route-id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateRoute(@PathParam("route-id") Integer routeId, RequestRouteDTOImpl param) {
        boolean isUppdated;
        try {
            isUppdated = routeWebService.getRoutesWebServiceImplPort().updateRoute(routeId, param);
        } catch (RouteServiceException e) {
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }

        if(isUppdated) {
            return Response.ok().build();
        }else {
            return Response.status(400).entity("Route wasn't updated").build();
        }
    }

    @DELETE
    @Path("/{route-id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteRoute(@PathParam("route-id") Integer routeId) {
        boolean isDeleted;
        try {
            isDeleted = routeWebService.getRoutesWebServiceImplPort().deleteRoute(routeId);
        } catch (RouteServiceException e) {
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }

        if(isDeleted) {
            return Response.ok().build();
        }else {
            return Response.status(404).build();
        }
    }
}
