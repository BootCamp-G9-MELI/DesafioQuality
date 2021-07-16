package br.com.meli.seu.imovel.dto;

import br.com.meli.seu.imovel.entity.Room;

import javax.validation.constraints.*;
import java.util.List;
import java.util.stream.Collectors;

public class RoomDTO {

    @NotBlank(message = "O nome do cômodo não pode estar vazio.")
    @Size(max = 30, message = "O comprimento do nome do cômodo não pode exceder 30 caracteres.")
    @Pattern(regexp = "^([A-Z]{1})([a-z 0-9A-Z]{1,})$", message = "O nome do cômodo deve começar com uma letra maiúscula.")
    private String name;

    @DecimalMin(value = "0", inclusive = false, message = "O valor da largura não pode ser vazio.")
    @DecimalMax(value = "25", message = "A largura máxima permitida por cômodo é de 25 metros.")
    private double width;

    @DecimalMin(value = "0", inclusive = false, message = "O valor do comprimento não pode ser vazio.")
    @DecimalMax(value = "33", message = "O comprimento máximo permitido por cômodo é de 33 metros.")
    private double length;

    public RoomDTO(String name, double width, double length) {
        this.name = name;
        this.width = width;
        this.length = length;
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

    public static List<RoomDTO> convert(List<Room> rooms) {
        return rooms.stream().map(room -> new RoomDTO(room.getName(), room.getWidth(), room.getLength()))
                .collect(Collectors.toList());
    }
    
    

    public static RoomDTO convert(Room room) {
        return new RoomDTO(room.getName(), room.getWidth(), room.getLength());
    }
}
