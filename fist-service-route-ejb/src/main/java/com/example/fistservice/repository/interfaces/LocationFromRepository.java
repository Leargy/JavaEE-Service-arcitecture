package com.example.fistservice.repository.interfaces;

import dto.LocationFromDTOImpl;
import model.LocationFrom;

import java.util.List;

public interface LocationFromRepository extends CRUDCommands{

    List<LocationFrom> findBy(LocationFromDTOImpl locationFromDTO) throws RuntimeException;

    void detach(LocationFrom from);
}
