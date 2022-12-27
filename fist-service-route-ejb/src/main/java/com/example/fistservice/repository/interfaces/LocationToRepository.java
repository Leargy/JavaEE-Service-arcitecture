package com.example.fistservice.repository.interfaces;


import dto.LocationToDTOImpl;
import model.LocationTo;

import java.util.List;

public interface LocationToRepository extends CRUDCommands{
    List<LocationTo> findBy(LocationToDTOImpl locationToDTOImpl) throws RuntimeException;

    void detach(LocationTo to);
}
