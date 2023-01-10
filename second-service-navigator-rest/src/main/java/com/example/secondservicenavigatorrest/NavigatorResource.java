package com.example.secondservicenavigatorrest;

import com.example.secondservicenavigatorrest.util.JNDIUtil;
import dto.ParamBeanDTOImpl;
import dto.RequestRouteDTOImpl;
import dto.RouteDTOImpl;
import dto.RoutesList;
import exceptions.CRUDServiceException;
import service.CRUDService;
import service.RouteService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/navigator")
public class NavigatorResource {


    @Inject
    private JNDIUtil jndiUtil;
    @Inject
    private CRUDService crudService;
    @Inject
    private RouteService routeService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/route/add/{id-from}/{id-to}/{distance}")
    public Response createRouteBetween(@HeaderParam("Authorization") String token, @PathParam("id-from") Integer routeIdFrom, @PathParam("id-to") Integer routeIdTo, @PathParam("distance") Float distance) {
        RouteDTOImpl routeDTOTo;
        RouteDTOImpl routeDTOFrom;
        RequestRouteDTOImpl routeDTO;
        try {
            String healthyUrl = jndiUtil.getHealthyCRUDRouteServiceUrl();
            routeDTOTo = crudService.getRouteById(routeIdTo, token, healthyUrl);
            routeDTOFrom = crudService.getRouteById(routeIdFrom, token, healthyUrl);
            routeDTO = routeService.formRouteBetween(routeDTOFrom, routeDTOTo, distance);
            crudService.createRoute(routeDTO, token, healthyUrl);
        } catch (CRUDServiceException e) {
            return Response.status(e.getStatus()).entity(e.getMessage()).build();
        }
        return Response.status(201).entity(routeDTO).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/routes/{id-from}/{id-to}/{order-by}")
    public Response findAndOrderRoutesBetween(@HeaderParam("Authorization") String token, @PathParam("id-from") Integer routeIdFrom, @PathParam("id-to") Integer routeIdTo, @PathParam("order-by") String orderBy) {

        RouteDTOImpl routeDTOFrom;
        RouteDTOImpl routeDTOTo;
        RoutesList routeDTOList;
        ParamBeanDTOImpl paramBeanDTO = new ParamBeanDTOImpl();
        try {
            String healthyUrl = jndiUtil.getHealthyCRUDRouteServiceUrl();
            routeDTOFrom = crudService.getRouteById(routeIdFrom, token, healthyUrl);
            routeDTOTo = crudService.getRouteById(routeIdTo, token,healthyUrl );
            paramBeanDTO.setFromX(routeDTOFrom.getFrom().getX());
            paramBeanDTO.setFromY(routeDTOFrom.getFrom().getY());
            paramBeanDTO.setFromZ(routeDTOFrom.getFrom().getZ());
            paramBeanDTO.setToX(routeDTOTo.getTo().getX());
            paramBeanDTO.setToY(routeDTOTo.getTo().getY());
            paramBeanDTO.setToZ(routeDTOTo.getTo().getZ());

            paramBeanDTO.setOrderBy(orderBy.split("_")[0].split(","));
            paramBeanDTO.setDecr(orderBy.split("_")[1].equals("true"));

            routeDTOList = crudService.findRoutesBy(paramBeanDTO, token, healthyUrl);
        } catch (CRUDServiceException e) {
            return Response.status(e.getStatus()).entity(e.getMessage()).build();
        }

        return Response.status(200).entity(routeDTOList).build();
    }
}