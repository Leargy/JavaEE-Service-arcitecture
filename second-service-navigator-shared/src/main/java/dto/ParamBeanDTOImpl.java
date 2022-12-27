package dto;

import dto.annotations.AttributeName;
import dto.annotations.OptionalAttribute;
import dto.annotations.SearchAttribute;
import dto.annotations.SearchAttributeType;

import java.io.Serializable;
import java.time.LocalDate;

public class ParamBeanDTOImpl implements Serializable {
    @SearchAttribute
    private Integer id;
    @SearchAttribute
    private String name;
    @SearchAttribute
    private LocalDate creationDate;

    @SearchAttribute(SearchAttributeType.CLASS)
    private CoordinatesDTOImpl coordinates;
    @SearchAttribute(SearchAttributeType.CLASS)
    private LocationFromDTOImpl from;
    @SearchAttribute(SearchAttributeType.CLASS)
    private LocationToDTOImpl to;
    @SearchAttribute
    private Float distance;

    @OptionalAttribute(AttributeName.ORDER)
    private String[] orderBy;
    @OptionalAttribute(AttributeName.PAGESIZE)
    private Long pageSize;
    @OptionalAttribute(AttributeName.OFFSET)
    private Long offSet;
    @OptionalAttribute(AttributeName.ISDECR)
    private boolean isDecr;


    public ParamBeanDTOImpl() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getCoordinatesX() {
        if(coordinates == null) {
            return null;
        }else {
            return coordinates.getX();
        }
//        coordinates.setX(coordinatesX);
//        return coordinatesX;
    }

    public Long getCoordinatesY() {
        if(coordinates == null) {
            return null;
        }else {
            return coordinates.getY();
        }
//        return coordinatesY;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Double getFromX() {
        if(from == null) {
            return null;
        }else {
            return from.getX();
        }
//        return fromX;
    }

    public Float getFromY() {
        if(from == null) {
            return null;
        }else {
            return from.getY();
        }
//        return fromY;
    }

    public Double getFromZ() {
        if(from == null) {
            return null;
        }else {
            return from.getZ();
        }
//        return fromZ;
    }

    public Double getToX() {
        if(to == null) {
            return null;
        }else {
            return to.getX();
        }
//        return toX;
    }

    public Float getToY() {
        if(to == null) {
            return null;
        }else {
            return to.getY();
        }
//        return toY;
    }

    public Integer getToZ() {
        if(to == null) {
            return null;
        }else {
            return to.getZ();
        }
//        return toZ;
    }

    public Float getDistance() {
        return distance;
    }

    public String[] getOrderBy() {
        return orderBy;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public Long getOffSet() {
        return offSet;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinatesX(Integer coordinatesX) {
        if(coordinates == null) {
            coordinates = new CoordinatesDTOImpl();
        }
        coordinates.setX(coordinatesX);
//        this.coordinatesX = coordinatesX;
    }

    public void setCoordinatesY(Long coordinatesY) {
        if(coordinates == null) {
            coordinates = new CoordinatesDTOImpl();
        }
        coordinates.setY(coordinatesY);
//        this.coordinatesY = coordinatesY;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setFromX(double fromX) {
        if(from == null) {
            from = new LocationFromDTOImpl();
        }
        from.setX(fromX);
//        this.fromX = fromX;
    }

    public void setFromY(Float fromY) {
        if(from == null) {
            from = new LocationFromDTOImpl();
        }
        from.setY(fromY);
//        this.fromY = fromY;
    }

    public void setFromZ(Double fromZ) {
        if(from == null) {
            from = new LocationFromDTOImpl();
        }
        from.setZ(fromZ);
//        this.fromZ = fromZ;
    }

    public void setToX(Double toX) {
        if(to == null) {
            to = new LocationToDTOImpl();
        }
        to.setX(toX);
//        this.toX = toX;
    }

    public void setToY(Float toY) {
        if(to == null) {
            to = new LocationToDTOImpl();
        }
        to.setY(toY);
//        this.toY = toY;
    }

    public void setToZ(Integer toZ) {
        if(to == null) {
            to = new LocationToDTOImpl();
        }
        to.setZ(toZ);
//        this.toZ = toZ;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public void setOrderBy(String[] orderBy) {
        this.orderBy = orderBy;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public void setOffSet(Long offSet) {
        this.offSet = offSet;
    }


    public boolean isDecr() {
        return isDecr;
    }

    public void setDecr(boolean decr) {
        isDecr = decr;
    }
}
