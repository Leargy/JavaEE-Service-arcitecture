package model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Table(name = "location_to")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class LocationTo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @XmlElement
    private int id;

    @XmlElement
    private Double x; //Поле не может быть null
    @XmlElement
    private Float y; //Поле не может быть null
    @XmlElement
    private Integer z; //Поле не может быть null

    public LocationTo(Double x, Float y, Integer z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public LocationTo() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public Integer getZ() {
        return z;
    }

    public void setZ(Integer z) {
        this.z = z;
    }
}
