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

import javax.json.bind.annotation.JsonbDateFormat;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RouteDTOImpl implements Serializable {
    @XmlElement
    private Long id;
    @XmlElement
    private String name;

    @JsonbDateFormat
    @JsonDeserialize(using = CustomDateDeserializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    @XmlElement
    @XmlJavaTypeAdapter(type=LocalDate.class, value = LocalDateXmlConvertor.class)
    private LocalDate creationDate;
    @XmlElement
    private CoordinatesDTOImpl coordinates;
    @XmlElement
    private LocationFromDTOImpl from;
    @XmlElement
    private LocationToDTOImpl to;
    @XmlElement
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


class LocalDateXmlConvertor extends XmlAdapter<String, LocalDate> {

    @Override
    public LocalDate unmarshal(String v) throws Exception {
        return LocalDate.parse(v, DateTimeFormatter.ofPattern("d-MMM-yyyy"));
    }

    @Override
    public String marshal(LocalDate v) throws Exception {
        return v.format(DateTimeFormatter.ofPattern("d-MMM-yyyy"));
    }
}

