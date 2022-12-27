package com.example.fistservice.repository.interfaces;



import dto.CoordinatesDTOImpl;
import model.Coordinates;

import java.util.List;

public interface CoordinatesRepository extends CRUDCommands{
    List<Coordinates> getAllCoordinates();

    List<Coordinates> findBy(CoordinatesDTOImpl coordinatesDTOImpl) throws RuntimeException;
}
