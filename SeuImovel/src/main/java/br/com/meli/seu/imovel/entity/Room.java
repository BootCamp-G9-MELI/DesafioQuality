package br.com.meli.seu.imovel.entity;

import javax.persistence.*;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private double width;
    private double length;

    @ManyToOne
    @JoinColumn(name = "property_id" , nullable = false)
    private Property property;

    public Room(long id, String name, double width, double length, Property property) {
        this.id = id;
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
}
