package dto;

import dto.annotations.SearchAttribute;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class LocationFromDTOImpl implements Serializable {
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
    private Double z;

    public LocationFromDTOImpl(double x, Float y, Double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public LocationFromDTOImpl(Integer id, Double x, Float y, Double z) {
        this.id = id;
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
}
