package com.example.fistservicerouterest.security;

import io.jsonwebtoken.ExpiredJwtException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Provider
public class JwtTokenFilter implements ContainerRequestFilter {

    private Map<String, String> accessList;
    @Inject
    private JwtTokenProvider provider;

    @PostConstruct
    private void init() {
        accessList = new HashMap<>();

        accessList.put("/(.*)", "ADMIN");
        accessList.put("/routes/([0-9]*)", "USER");
        accessList.put("/routes/specific", "USER");
        accessList.put("/routes/distance/sum", "USER");
        accessList.put("/routes/coordinates/max", "USER");

    }
    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        return;
       String token = provider.resolveToken(containerRequestContext);
       try {

       if(token != null) {
           if(provider.isTokenExpired(token)) {
               Response unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED)
                       .entity("User token expired")
                       .build();

               containerRequestContext.abortWith(unauthorizedStatus);
           }

           String role = provider.getUserRole(token);

           String uri = containerRequestContext.getUriInfo().getRequestUri().getPath();
           boolean hasAccess = false;
           for (Map.Entry<String, String> entry: accessList.entrySet()) {

               if(uri.matches(entry.getKey()) && entry.getValue().equals(role)) {
                   hasAccess = true;
                   return;
               }
           }
       }
       Response unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED)
               .entity("User has no access to this resource")
               .build();

       containerRequestContext.abortWith(unauthorizedStatus);

       }catch (ExpiredJwtException ex) {
           Response unauthorizedStatus = Response.status(Response.Status.FORBIDDEN)
                   .entity("User token expired")
                   .build();

           containerRequestContext.abortWith(unauthorizedStatus);
       }
    }

}
