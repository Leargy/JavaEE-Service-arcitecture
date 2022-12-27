package model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import javax.persistence.*;
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

@Entity
@Table(name = "route")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

public class Route implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    @XmlElement
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    @Column(name="name", nullable=false)
    @XmlElement
    private String name; //Поле не может быть null, Строка не может быть пустой
    @XmlElement
    private float distance; //Значение поля должно быть больше 1

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "coordinates_id", referencedColumnName = "id")
    @XmlElement
    private Coordinates coordinates; //Поле не может быть null


    @JsonDeserialize(using = CustomDateDeserializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    @XmlElement
    @XmlJavaTypeAdapter(type=LocalDate.class, value = LocalDateXmlConvertor.class)
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @ManyToOne
    @JoinColumn(name = "location_from_id" , referencedColumnName = "id")
    @XmlElement
    private LocationFrom from; //Поле не может быть null
    @ManyToOne
    @JoinColumn(name = "location_to_id" , referencedColumnName = "id")
    @XmlElement
    private LocationTo to; //Поле может быть null

    public Route(Long id, String name, float distance, Coordinates coordinates, LocalDate creationDate, LocationFrom from, LocationTo to) {
        this.id = id;
        this.name = name;
        this.distance = distance;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.from = from;
        this.to = to;
    }

    public Route() {}

    public Long getId() {
        return id;
    }

    public void setId(Long routeId) {
        this.id = routeId;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public LocationFrom getFrom() {
        return from;
    }

    public LocationTo getTo() {
        return to;
    }

    public float getDistance() {
        return distance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setFrom(LocationFrom from) {
        this.from = from;
    }

    public void setTo(LocationTo to) {
        this.to = to;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }


    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
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
        return LocalDate.parse(v,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String marshal(LocalDate v) throws Exception {
        return v.toString();
    }
}
