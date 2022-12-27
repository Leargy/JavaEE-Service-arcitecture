package dto;

public interface RouteDTO extends CoordinatesDTO, LocationFromDTO, LocationToDTO {

    String getName();
    CoordinatesDTOImpl getCoordinates();
    LocationFromDTOImpl getFrom();
    LocationToDTOImpl getTo();
    Float getDistance();
    void setName(String name);
    void setCoordinates(CoordinatesDTOImpl coordinates);
    void setFrom(LocationFromDTOImpl from);
    void setTo(LocationToDTOImpl to);
    void setDistance(float distance);
}
