package model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.json.bind.annotation.JsonbDateFormat;
import java.io.Serializable;
import java.time.LocalDate;

public class Routeeeeeeee implements Serializable {

    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private float distance; //Значение поля должно быть больше 1
    private Coordinates coordinates; //Поле не может быть null

    @JsonbDateFormat
    @JsonDeserialize(using = CustomDateDeserializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private LocationFrom from; //Поле не может быть null
    private LocationTo to; //Поле может быть null

    public Routeeeeeeee(Long id, String name, float distance, Coordinates coordinates, LocalDate creationDate, LocationFrom from, LocationTo to) {
        this.id = id;
        this.name = name;
        this.distance = distance;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.from = from;
        this.to = to;
    }

    public Routeeeeeeee() {}

    public Long getId() {
        return id;
    }

    public void setId(Long routeId) {
        this.id = routeId;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public LocationFrom getFrom() {
        return from;
    }

    public LocationTo getTo() {
        return to;
    }

    public float getDistance() {
        return distance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setFrom(LocationFrom from) {
        this.from = from;
    }

    public void setTo(LocationTo to) {
        this.to = to;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }


    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
