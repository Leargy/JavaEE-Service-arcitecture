package com.example.fistservice.services;

import com.example.fistservice.repository.interfaces.LocationToRepository;
import com.example.fistservice.services.interfaces.LocationToService;
import dto.LocationToDTOImpl;
import model.LocationTo;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@ManagedBean
public class LocationToServiceImpl implements LocationToService, Serializable {

    @Inject
    private LocationToRepository locationToRepository;

    @Override
    public LocationToDTOImpl locationToToDTO(LocationTo route) {
        LocationToDTOImpl locationToDTO = new LocationToDTOImpl();

        locationToDTO.setX(route.getX());
        locationToDTO.setY(route.getY());
        locationToDTO.setZ(route.getZ());
        locationToDTO.setId(route.getId());

        return locationToDTO;
    }

    @Override
    public LocationTo getLocationToIfExists(LocationToDTOImpl to) {
        List<LocationTo> locationTos = locationToRepository.findBy(to);

        if(locationTos.size() != 0) return locationTos.get(0);
        return null;
    }

    @Override
    public void detach(LocationTo to) {
        locationToRepository.detach(to);
    }
}
