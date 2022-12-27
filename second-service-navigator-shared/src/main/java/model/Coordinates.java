package model;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Table(name = "coordinates")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Coordinates implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @XmlElement
    private int id;

    @XmlElement
    private Integer x; //Максимальное значение поля: 736, Поле не может быть null
    @XmlElement
    private Long y; //Значение поля должно быть больше -119, Поле не может быть null

    public Coordinates(Integer x, Long y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates() {
    }

    public int getId() {
        return id;
    }

    public Integer getX() {
        return x;
    }

    public Long getY() {
        return y;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public void setY(Long y) {
        this.y = y;
    }
}
