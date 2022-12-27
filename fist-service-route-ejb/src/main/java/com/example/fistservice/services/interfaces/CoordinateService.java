package com.example.fistservice.services.interfaces;


import dto.CoordinatesDTOImpl;
import model.Coordinates;

public interface CoordinateService {

    Coordinates getIdMaxCoordinates();

    CoordinatesDTOImpl coordinatesToDTO(Coordinates route);
}
