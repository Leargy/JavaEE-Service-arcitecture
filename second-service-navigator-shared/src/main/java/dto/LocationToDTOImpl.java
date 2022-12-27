package dto;

import dto.annotations.SearchAttribute;

import java.io.Serializable;

public class LocationToDTOImpl implements Serializable {
    private Integer id;
    @SearchAttribute
    private Double x;
    @SearchAttribute
    private Float y;
    @SearchAttribute
    private Integer z;

    public LocationToDTOImpl(Double x, Float y, Integer z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public LocationToDTOImpl() {
    }

    public Double getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    public Integer getZ() {
        return z;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public void setZ(Integer z) {
        this.z = z;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
