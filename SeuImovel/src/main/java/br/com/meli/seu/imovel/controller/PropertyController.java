package br.com.meli.seu.imovel.controller;

import br.com.meli.seu.imovel.dto.DistrictDTO;
import br.com.meli.seu.imovel.dto.PropertyAreaDTO;
import br.com.meli.seu.imovel.dto.PropertyDTO;
import br.com.meli.seu.imovel.dto.PropertyPayloadDTO;
import br.com.meli.seu.imovel.dto.PropertyPriceDTO;
import br.com.meli.seu.imovel.dto.PropertyRoomAreaDTO;
import br.com.meli.seu.imovel.dto.RoomAreaDTO;
import br.com.meli.seu.imovel.dto.RoomDTO;
import br.com.meli.seu.imovel.entity.District;
import br.com.meli.seu.imovel.entity.Property;
import br.com.meli.seu.imovel.entity.Room;
import br.com.meli.seu.imovel.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
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
    
    @GetMapping("/{id}/price")
    public ResponseEntity<PropertyPriceDTO> getPropertyPrice(@PathVariable long id){
    	Property property = propertyService.findPropertyById(id);
    	District district = property.getDistrict();
    	DistrictDTO districtDTO = new DistrictDTO(district.getName(), district.getPriceM2());
    	double area = propertyService.calculateM2(propertyService.getRoomsByProperty(property));
    	BigDecimal priceArea = propertyService.calculatePropertyPrice(property, area);
    	
    	PropertyPriceDTO propertyPriceDTO = new PropertyPriceDTO(
    			districtDTO, 
    			property.getName(),
    			area,
    			priceArea
    	);
    	return new ResponseEntity<>(propertyPriceDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}/biggest-room")
    public ResponseEntity<RoomDTO> getBiggestRoom(@PathVariable long id){
        Property property = propertyService.findPropertyById(id);
        List<Room> roomList = propertyService.getRoomsByProperty(property);
        Room biggestRoom = propertyService.getBiggestRoom(roomList);
        RoomDTO roomDTO = RoomDTO.convert(biggestRoom);

        return new ResponseEntity<>(roomDTO, HttpStatus.OK);
    }
    
    @GetMapping("/{id}/rooms-area")
    public ResponseEntity<PropertyRoomAreaDTO> getRoomsArea(@PathVariable long id){
    	Property property = propertyService.findPropertyById(id);
        List<Room> roomList = propertyService.getRoomsByProperty(property);
        List<RoomAreaDTO> roomAreaDTOs = propertyService.getListRoomArea(roomList);
        PropertyRoomAreaDTO propertyRoomAreaDTO = new PropertyRoomAreaDTO(property.getName(), roomAreaDTOs);
        
        return new ResponseEntity<PropertyRoomAreaDTO>(propertyRoomAreaDTO, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PropertyDTO> getById(@PathVariable long id){
    	PropertyDTO propertyDTO = propertyService.findById(id);
    	return new ResponseEntity<PropertyDTO>(propertyDTO, HttpStatus.OK);
    }
    
    
    
    @PostMapping("/create")
    public ResponseEntity<Long> create(@RequestBody PropertyPayloadDTO propertyPayloadDTO, UriComponentsBuilder builder){
    	long id = propertyService.create(propertyPayloadDTO);
    	
    	URI uri = builder.path("/property/{id}").buildAndExpand(id).toUri();
    	
    	return ResponseEntity.created(uri).build();
    }

}



