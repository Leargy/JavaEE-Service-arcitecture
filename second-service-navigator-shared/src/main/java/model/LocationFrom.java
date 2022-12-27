package model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Table(name = "location_from")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class LocationFrom implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @XmlElement
    private int id;

    @XmlElement
    private double x;
    @XmlElement
    private Float y; //Поле не может быть null
    @XmlElement
    private Double z; //Поле не может быть null

    public LocationFrom(double x, Float y, Double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public LocationFrom() {}


    public void setId(int id) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    public Double getZ() {
        return z;
    }
}
