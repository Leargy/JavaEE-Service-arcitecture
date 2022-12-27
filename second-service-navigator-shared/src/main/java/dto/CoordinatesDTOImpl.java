package dto;


import dto.annotations.SearchAttribute;

import java.io.Serializable;

public class CoordinatesDTOImpl implements Serializable {
    @SearchAttribute
    private Integer x;
    @SearchAttribute
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
