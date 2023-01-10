package com.example.fistservicerouterest;

import com.example.fistservicerouterest.util.RouteWebService;
import exceptions.RouteServiceException;
import model.Route;
import model.Routeeeeeeee;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/routes")
public class ExtraOperationsResources {

    @Inject
    private RouteWebService routeWebService;

    @GET
    @Path("/distance/sum")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDistanceSum() {
        Float distanceSum = null;
        try {
            distanceSum = routeWebService.getRoutesWebServiceImplPort().getDistanceSum();
        } catch (RouteServiceException e) {
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
        if(distanceSum != null) {
            return Response.ok(distanceSum).build();
        }else {
            return Response.status(404).build();
        }
    }

    @GET
    @Path("/coordinates/max")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRouteWithMaxCoordinate() {
        Route route = routeWebService.getRoutesWebServiceImplPort().getRouteWithMaxCoordinates();
        Routeeeeeeee routeeeeeeee = new Routeeeeeeee(route.getId(),route.getName(), route.getDistance(),route.getCoordinates(),route.getCreationDate(),route.getFrom(),route.getTo());
        if(route != null) {
            return Response.ok().entity(routeeeeeeee).build();
        }else {
            return Response.status(404).build();
        }
    }

    @GET
    @Path("/distance/less/num")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNumRoutesWithLessDistanceThan(@QueryParam("distance") Float distanceValue) {
        Integer routesNum = routeWebService.getRoutesWebServiceImplPort().getNumRoutesWithDistanceLessThan(distanceValue);
        if(routesNum != null) {
            return Response.ok(routesNum).build();
        }else {
            return Response.status(404).build();
        }
    }
}
