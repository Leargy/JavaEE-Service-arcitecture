package dto;


import dto.annotations.SearchAttribute;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class LocationToDTOImpl implements Serializable {
    @XmlElement
    private Integer id;
    @SearchAttribute
    @XmlElement
    private Double x;
    @SearchAttribute
    @XmlElement
    private Float y;
    @SearchAttribute
    @XmlElement
    private Integer z;

    public LocationToDTOImpl(Double x, Float y, Integer z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public LocationToDTOImpl(Integer id, Double x, Float y, Integer z) {
        this.id = id;
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
