package br.com.meli.seu.imovel.service;

import br.com.meli.seu.imovel.entity.District;
import br.com.meli.seu.imovel.entity.Property;
import br.com.meli.seu.imovel.entity.Room;
import br.com.meli.seu.imovel.repository.DistrictRepository;
import br.com.meli.seu.imovel.repository.PropertyRepository;
import br.com.meli.seu.imovel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
public class CreateDatabaseService {

    private final DistrictRepository districtRepository;
    private final PropertyRepository propertyRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public CreateDatabaseService(DistrictRepository districtRepository, PropertyRepository propertyRepository, RoomRepository roomRepository) {
        this.districtRepository = districtRepository;
        this.propertyRepository = propertyRepository;
        this.roomRepository = roomRepository;
    }

    public void populateDatabase() {
        List<District> districts = this.populateDistrict();
        List<Property> properties = this.populateProperty(districts);
        this.populateRooms(properties);
    }

    public List<District> populateDistrict() {
        List<District> districts = List.of(
                new District("Vila Mariana", new BigDecimal(10000)),
                new District("Morumbi", new BigDecimal(15000)),
                new District("Campo Limpo", new BigDecimal(5900)),
                new District("Jardim Peri", new BigDecimal(4500)),
                new District("Jardim Angela", new BigDecimal(4700))
        );
        districts.forEach(districtRepository::save);
        return districts;
    }

    public List<Property> populateProperty(List<District> districts) {
        List<Property> properties = List.of(
                new Property("Casa 123", districts.get(0)),
                new Property("Edifício dos castores", districts.get(0)),
                new Property("Mansao thug stronda", districts.get(1)),
                new Property("Casa suja", districts.get(2)),
                new Property("Casa do Jesus", districts.get(3)),
                new Property("Casa da Angela", districts.get(4))
        );
        properties.forEach(propertyRepository::save);
        return properties;
    }

    private void populateRooms(List<Property> properties) {
        Arrays.asList(
                new Room("Banheiro", 3, 4, properties.get(0)),
                new Room("Sala", 8, 7, properties.get(0)),
                new Room("Cozinha", 6, 5, properties.get(0)),
                new Room("Banheiro", 5, 5, properties.get(1)),
                new Room("Sala", 8, 8, properties.get(1)),
                new Room("Cozinha", 7, 7, properties.get(1)),
                new Room("Banheiro", 3, 2, properties.get(2)),
                new Room("Sala", 3, 3, properties.get(2)),
                new Room("Cozinha", 5, 5, properties.get(2)),
                new Room("Quarto", 10, 10, properties.get(2)),
                new Room("Sala", 6, 7, properties.get(3)),
                new Room("Cozinha", 9, 9, properties.get(3)),
                new Room("Quarto", 6, 6, properties.get(3)),
                new Room("Sala", 6, 7, properties.get(4)),
                new Room("Quarto", 10, 15, properties.get(4)),
                new Room("Corredor", 1, 1, properties.get(5)),
                new Room("Banheiro", 6, 6, properties.get(5))
        ).forEach(roomRepository::save);
    }

}
