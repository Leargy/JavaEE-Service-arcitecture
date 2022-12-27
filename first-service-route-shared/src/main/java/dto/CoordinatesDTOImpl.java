package dto;

import dto.annotations.SearchAttribute;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CoordinatesDTOImpl implements Serializable {
    @SearchAttribute
    @XmlElement
    private Integer x;
    @SearchAttribute
    @XmlElement
    private Long y;

    public CoordinatesDTOImpl(Integer x, Long y) {
        this.x = x;
        this.y = y;
    }

    public CoordinatesDTOImpl() {
    }

    public Integer getX() {
        return x;
    }

    public Long getY() {
        return y;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public void setY(Long y) {
        this.y = y;
    }
}
