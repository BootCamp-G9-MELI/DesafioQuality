package br.com.meli.seu.imovel.controller;

import br.com.meli.seu.imovel.dto.PropertyAreaDTO;
import br.com.meli.seu.imovel.dto.RoomDTO;
import br.com.meli.seu.imovel.entity.Property;
import br.com.meli.seu.imovel.entity.Room;
import br.com.meli.seu.imovel.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/property")
public class PropertyController {

    private final PropertyService propertyService;

    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping("/{id}/area")
    public ResponseEntity<PropertyAreaDTO> getPropertyArea(@PathVariable long id) {
        Property property = propertyService.findPropertyById(id);
        List<Room> roomList = propertyService.getRoomsByProperty(property);
        double propertyArea = propertyService.calculateM2(roomList);
        PropertyAreaDTO propertyAreaDTO = new PropertyAreaDTO(property.getName(), RoomDTO.convert(roomList), propertyArea);
        return new ResponseEntity<>(propertyAreaDTO, HttpStatus.OK);
    }

}
