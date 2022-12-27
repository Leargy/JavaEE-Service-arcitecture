package dto;

import dto.annotations.AttributeName;
import dto.annotations.OptionalAttribute;
import dto.annotations.SearchAttribute;
import dto.annotations.SearchAttributeType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.LocalDate;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ParamBeanDTOImpl implements Serializable {
    @SearchAttribute
    @XmlElement
    private Integer id;
    @SearchAttribute
    @XmlElement
    private String name;
//    @SearchAttribute(value = SearchAttributeType.CLASS)
//    private Integer coordinatesX;
//    @SearchAttribute(value = SearchAttributeType.CLASS)
//    private Long coordinatesY;
    @SearchAttribute
    @XmlElement
    private LocalDate creationDate;

    @SearchAttribute(SearchAttributeType.CLASS)
    @XmlElement
    private CoordinatesDTOImpl coordinates;
    @SearchAttribute(SearchAttributeType.CLASS)
    @XmlElement
    private LocationFromDTOImpl from;
    @SearchAttribute(SearchAttributeType.CLASS)
    @XmlElement
    private LocationToDTOImpl to;

//    @SearchAttribute(value = SearchAttributeType.CLASS)
//    private Double fromX;
//    @SearchAttribute(value = SearchAttributeType.CLASS)
//    private Float fromY;
//    @SearchAttribute(value = SearchAttributeType.CLASS)
//    private Double fromZ;
//    @SearchAttribute(value = SearchAttributeType.CLASS)
//    private Double toX;
//    @SearchAttribute(value = SearchAttributeType.CLASS)
//    private Float toY;
//    @SearchAttribute(value = SearchAttributeType.CLASS)
//    private Integer toZ;
    @SearchAttribute
    @XmlElement
    private Float distance;

    @OptionalAttribute(AttributeName.ORDER)
    @XmlElement
    private String[] orderBy;
    @OptionalAttribute(AttributeName.PAGESIZE)
    @XmlElement
    private Long pageSize;
    @OptionalAttribute(AttributeName.OFFSET)
    @XmlElement
    private Long offSet;
    @OptionalAttribute(AttributeName.ISDECR)
    @XmlElement
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

    //
//    @Override
//    public CoordinatesDTOImpl getCoordinates() {
//        return null;
//    }
//
//    @Override
//    public LocationFromDTOImpl getFrom() {
//        return null;
//    }
//
//    @Override
//    public LocationToDTOImpl getTo() {
//        return null;
//    }
//
//    @Override
//    public void setCoordinates(CoordinatesDTOImpl coordinates) {
//
//    }
//
//    @Override
//    public void setFrom(LocationFromDTOImpl from) {
//
//    }
//
//    @Override
//    public void setTo(LocationToDTOImpl to) {
//
//    }
}
