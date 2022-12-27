package dto;

import dto.annotations.SearchAttribute;

import java.io.Serializable;

public class LocationFromDTOImpl implements Serializable {
    private Integer id;
    @SearchAttribute
    private Double x;
    @SearchAttribute
    private Float y;
    @SearchAttribute
    private Double z;

    public LocationFromDTOImpl(double x, Float y, Double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public LocationFromDTOImpl() {
    }

    public Double getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    public Double getZ() {
        return z;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public void setZ(Double z) {
        this.z = z;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setX(Double x) {
        this.x = x;
    }
}
