package com.example.fistservice.services;

import com.example.fistservice.repository.interfaces.LocationFromRepository;
import com.example.fistservice.services.interfaces.LocationFromService;
import dto.LocationFromDTOImpl;
import model.LocationFrom;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@ManagedBean
public class LocationFromServiceImpl implements LocationFromService, Serializable {

    @Inject
    private LocationFromRepository locationFromRepository;

    @Override
    public LocationFromDTOImpl locationToToDTO(LocationFrom route) {
        LocationFromDTOImpl locationFromDTO = new LocationFromDTOImpl();

        locationFromDTO.setX(route.getX());
        locationFromDTO.setY(route.getY());
        locationFromDTO.setZ(route.getZ());
        locationFromDTO.setId(route.getId());

        return locationFromDTO;
    }

    @Override
    public LocationFrom getLocationByIfExists(LocationFromDTOImpl from) {
        List<LocationFrom> locationTos = locationFromRepository.findBy(from);

        if(locationTos.size() != 0) return locationTos.get(0);
        return null;
    }

    @Override
    public void detach(LocationFrom from) {
        locationFromRepository.detach(from);
    }
}
