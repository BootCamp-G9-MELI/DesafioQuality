package br.com.meli.seu.imovel.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Room {

    @Id
    @SequenceGenerator(name = "room_id_generator", sequenceName = "room_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_sequence")
    private long id;
    private String name;
    private double width;
    private double length;

    @ManyToOne
    @JoinColumn(name = "property_id" , nullable = false)
    private Property property;

    public Room(String name, double width, double length, Property property) {
        this.name = name;
        this.width = width;
        this.length = length;
        this.property = property;
    }
    public Room(){

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }

    public Property getProperty() {
        return property;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id && Double.compare(room.width, width) == 0 && Double.compare(room.length, length) == 0 && Objects.equals(name, room.name) && Objects.equals(property, room.property);
    }

}
