package com.example.fistservice.services;

import com.example.fistservice.repository.interfaces.CoordinatesRepository;
import com.example.fistservice.services.interfaces.CoordinateService;
import dto.CoordinatesDTOImpl;
import model.Coordinates;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

@ManagedBean
public class CoordinateServiceImpl implements CoordinateService, Serializable {

    @Inject
    private CoordinatesRepository coordinatesRepository;

    @Override
    public Coordinates getIdMaxCoordinates() {
        List<Coordinates> coordinatesList = coordinatesRepository.getAllCoordinates();

        Comparator<Coordinates> comparator = (z,i) -> {
            Double vLengthZ = Math.sqrt(Math.pow(z.getX(), 2) + Math.pow(z.getY(), 2));
            Double vLengthI = Math.sqrt(Math.pow(i.getX(), 2) + Math.pow(i.getY(), 2));
            if(vLengthZ.equals(vLengthI)) return 0;
            else if(vLengthZ > vLengthI) return 1;
            else return -1;
        };

        return coordinatesList.stream().max(comparator).get();
    }

    @Override
    public CoordinatesDTOImpl coordinatesToDTO(Coordinates route) {
        CoordinatesDTOImpl coordinatesDTO = new CoordinatesDTOImpl();

        coordinatesDTO.setX(route.getX());
        coordinatesDTO.setY(route.getY());

        return coordinatesDTO;
    }
}
