package com.example.fistservice.services.interfaces;


import dto.LocationToDTOImpl;
import model.LocationTo;

public interface LocationToService {

    LocationToDTOImpl locationToToDTO(LocationTo route);

    LocationTo getLocationToIfExists(LocationToDTOImpl to);

    void detach(LocationTo to);
}
