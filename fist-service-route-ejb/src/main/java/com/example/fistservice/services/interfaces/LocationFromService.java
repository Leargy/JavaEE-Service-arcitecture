package com.example.fistservice.services.interfaces;


import dto.LocationFromDTOImpl;
import model.LocationFrom;

public interface LocationFromService {

    LocationFromDTOImpl locationToToDTO(LocationFrom route);

    LocationFrom getLocationByIfExists(LocationFromDTOImpl from);

    void detach(LocationFrom from);
}
