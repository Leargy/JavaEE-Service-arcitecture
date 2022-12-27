package com.example.secondservicenavigatorrest.util;

import com.orbitz.consul.Consul;
import com.orbitz.consul.model.health.Service;
import com.orbitz.consul.model.health.ServiceHealth;
import dto.RouteDTOImpl;
import service.CRUDService;
import service.RouteService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;
import java.util.List;

@ApplicationScoped
public class JNDIUtil {
    private Context context;

    @Inject
    Consul consul;

    @PostConstruct
    public void init() {
        List<ServiceHealth> nodes = consul.healthClient().getHealthyServiceInstances("second-service-ejb").getResponse();
        Service service = nodes.get(0).getService();
        String providerUrl = "remote+http://" + service.getAddress() + ":" + service.getPort();
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        jndiProperties.put(Context.PROVIDER_URL, providerUrl);
        try {
            context = new InitialContext(jndiProperties);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    public RouteService getRouteServiceEJBInstance() {
        try {
            return (RouteService) context.lookup("ejb:/second-service-navigator-ejb-1.0-SNAPSHOT/RouteServiceImpl!service.RouteService");
        } catch (NamingException e) {
            throw new RuntimeException("Error getting EJB instance", e);
        }

    }

    public CRUDService getCRUDServiceEJBInstance() {
        try {
            return (CRUDService) context.lookup("ejb:/second-service-navigator-ejb-1.0-SNAPSHOT/CRUDServiceImpl!service.CRUDService");
        } catch (NamingException e) {
            throw new RuntimeException("Error getting EJB instance", e);
        }

    }

    public String getHealthyCRUDRouteServiceUrl() {
//        List<ServiceHealth> nodes = consul.healthClient().getHealthyServiceInstances("first-service-rest-soap").getResponse();
//        Service service = nodes.get(0).getService();
//        return "http://" + service.getAddress() + ":" + service.getPort();
        return "http://127.0.0.1:8888";
    }
}
