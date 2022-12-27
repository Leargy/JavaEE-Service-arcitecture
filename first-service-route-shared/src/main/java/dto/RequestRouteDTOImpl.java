package dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RequestRouteDTOImpl implements Serializable {
    @XmlElement
    private String name;
    @XmlElement
    private CoordinatesDTOImpl coordinates;
    @XmlElement
    private LocationFromDTOImpl from;
    @XmlElement
    private LocationToDTOImpl to;
    @XmlElement
    private float distance;

    public RequestRouteDTOImpl(String name, CoordinatesDTOImpl coordinates, LocationFromDTOImpl from, LocationToDTOImpl to, float distance) {
        this.name = name;
        this.coordinates = coordinates;
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    public RequestRouteDTOImpl() {
    }

    public String getName() {
        return name;
    }

    public CoordinatesDTOImpl getCoordinates() {
        return coordinates;
    }

    public LocationFromDTOImpl getFrom() {
        return from;
    }

    public LocationToDTOImpl getTo() {
        return to;
    }

    public Float getDistance() {
        return distance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(CoordinatesDTOImpl coordinates) {
        this.coordinates = coordinates;
    }

    public void setFrom(LocationFromDTOImpl from) {
        this.from = from;
    }

    public void setTo(LocationToDTOImpl to) {
        this.to = to;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }


//    @Override
//    public Integer getCoordinatesX() {
//        if(coordinates != null) {
//            return coordinates.getCoordinatesX();
//        }else return null;
//    }
//
//    @Override
//    public Long getCoordinatesY() {
//        if(coordinates != null) {
//            return coordinates.getCoordinatesY();
//        }else return null;
//    }
//
//    @Override
//    public void setCoordinatesX(Integer x) {
//        if(coordinates == null) {
//            coordinates = new CoordinatesDTOImpl();
//        }
//        coordinates.setCoordinatesX(x);
//    }
//
//    @Override
//    public void setCoordinatesY(Long y) {
//        if(coordinates == null) {
//            coordinates = new CoordinatesDTOImpl();
//        }
//        coordinates.setCoordinatesY(y);
//    }
//
//    @Override
//    public Double getFromX() {
//        if(from != null) {
//            return from.getFromX();
//        }else return null;
//    }
//
//    @Override
//    public Float getFromY() {
//        if(from != null) {
//            return from.getFromY();
//        }else return null;
//    }
//
//    @Override
//    public Double getFromZ() {
//        if(from != null) {
//            return from.getFromZ();
//        }else return null;
//    }
//
//    @Override
//    public void setFromX(double x) {
//        if(from == null) {
//            from = new LocationFromDTOImpl();
//        }
//        from.setFromX(x);
//    }
//
//    @Override
//    public void setFromY(Float y) {
//        if(from == null) {
//            from = new LocationFromDTOImpl();
//        }
//        from.setFromY(y);
//    }
//
//    @Override
//    public void setFromZ(Double z) {
//        if(from == null) {
//            from = new LocationFromDTOImpl();
//        }
//        from.setFromZ(z);
//    }
//
//    @Override
//    public Double getToX() {
//        if(to != null) {
//            return to.getToX();
//        }else return null;
//    }
//
//    @Override
//    public Float getToY() {
//        if(to != null) {
//            return to.getToY();
//        }else return null;
//    }
//
//    @Override
//    public Integer getToZ() {
//        if(to != null) {
//            return to.getToZ();
//        }else return null;
//    }
//
//    @Override
//    public void setToX(Double x) {
//        if(to == null) {
//            to = new LocationToDTOImpl();
//        }
//        to.setToX(x);
//    }
//
//    @Override
//    public void setToY(Float y) {
//        if(to == null) {
//            to = new LocationToDTOImpl();
//        }
//        to.setToY(y);
//    }
//
//    @Override
//    public void setToZ(Integer z) {
//        if(to == null) {
//            to = new LocationToDTOImpl();
//        }
//        to.setToZ(z);
//    }
}
