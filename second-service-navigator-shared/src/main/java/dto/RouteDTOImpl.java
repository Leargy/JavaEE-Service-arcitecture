package dto;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RouteDTOImpl implements Serializable {
    private Long id;
    private String name;

    private LocalDate creationDate;
    private CoordinatesDTOImpl coordinates;
    private LocationFromDTOImpl from;
    private LocationToDTOImpl to;
    private Float distance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @JsonDeserialize(using = CustomDateDeserializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public CoordinatesDTOImpl getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(CoordinatesDTOImpl coordinates) {
        this.coordinates = coordinates;
    }

    public LocationFromDTOImpl getFrom() {
        return from;
    }

    public void setFrom(LocationFromDTOImpl from) {
        this.from = from;
    }

    public LocationToDTOImpl getTo() {
        return to;
    }

    public void setTo(LocationToDTOImpl to) {
        this.to = to;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public RouteDTOImpl(Long id, String name, LocalDate creationDate, CoordinatesDTOImpl coordinates, LocationFromDTOImpl from, LocationToDTOImpl to, Float distance) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.coordinates = coordinates;
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    public RouteDTOImpl() {
    }
}
class CustomDateSerializer extends StdSerializer<LocalDate> implements Serializable{
    private static final long serialVersionUID = 1L;
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    public CustomDateSerializer() {
        this(null);
    }
    public CustomDateSerializer(Class<LocalDate> t) {
        super(t);
    }
    @Override
    public void serialize(LocalDate value,
                          JsonGenerator generator, SerializerProvider arg2) throws IOException {
        generator.writeString(value.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }
}
class CustomDateDeserializer extends StdDeserializer<LocalDate> implements Serializable{
    private static final long serialVersionUID = 1L;
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    public CustomDateDeserializer() {
        this(null);
    }
    public CustomDateDeserializer(Class<LocalDate> t) {
        super(t);
    }
    @Override
    public LocalDate deserialize(JsonParser parser, DeserializationContext context)
            throws IOException, JsonProcessingException {

        String date = parser.getText();
        try {
            return LocalDate.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}