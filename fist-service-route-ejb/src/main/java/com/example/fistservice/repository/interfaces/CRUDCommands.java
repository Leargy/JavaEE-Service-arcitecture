package com.example.fistservice.repository.interfaces;

public interface CRUDCommands<O,I> {
    void create(O object);
    void update(O object);
    void delete(O object);
}
