package br.com.meli.seu.imovel.dto;

import java.util.List;

public class PropertyAreaDTO {
    private String name;
    private List<RoomDTO> rooms;
    private double area;

    public PropertyAreaDTO(String name, List<RoomDTO> rooms, double area) {
        this.name = name;
        this.rooms = rooms;
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public List<RoomDTO> getRooms() {
        return rooms;
    }

    public double getArea() {
        return area;
    }
}
