package com.example.fistservicerouterest.util;

import service.RouteService;

import javax.annotation.ManagedBean;
import javax.inject.Singleton;
import javax.xml.namespace.QName;
import javax.xml.ws.*;
import java.net.MalformedURLException;
import java.net.URL;

@ManagedBean
@Singleton
@WebServiceClient(name = "routes",targetNamespace = "http://services.fistservice.example.com/", wsdlLocation = "http://localhost:8081/routes?wsdl")
public class RouteWebService extends Service {

    private final static URL ROUTES_WSDL_LOCATION;
    private final static WebServiceException ROUTES_EXCEPTION;
    private final static QName ROUTES_QNAME = new QName("http://services.fistservice.example.com/", "routes");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8081/routes?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        ROUTES_WSDL_LOCATION = url;
        ROUTES_EXCEPTION = e;
    }

    public RouteWebService() {
        super(__getWsdlLocation(), ROUTES_QNAME);
    }

    public RouteWebService(WebServiceFeature... features) {
        super(__getWsdlLocation(), ROUTES_QNAME, features);
    }

    public RouteWebService(URL wsdlLocation) {
        super(wsdlLocation, ROUTES_QNAME);
    }

    public RouteWebService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, ROUTES_QNAME, features);
    }


    protected RouteWebService(URL wsdlDocumentLocation, QName serviceName) {
        super(wsdlDocumentLocation, serviceName);
    }

    protected RouteWebService(URL wsdlDocumentLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlDocumentLocation, serviceName, features);
    }

    @WebEndpoint(name = "RoutesWebServiceImplPort")
    public RouteService getFlatsWebServiceImplPort() {
        return super.getPort(new QName("http://services.fistservice.example.com/", "RouteServiceImplPort"), RouteService.class);
    }

    private static URL __getWsdlLocation() {
        if (ROUTES_EXCEPTION!= null) {
            throw ROUTES_EXCEPTION;
        }
        return ROUTES_WSDL_LOCATION;
    }
}
