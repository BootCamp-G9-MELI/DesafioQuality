package br.com.meli.seu.imovel.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.meli.seu.imovel.dto.PropertyDTO;
import br.com.meli.seu.imovel.dto.PropertyPayloadDTO;
import br.com.meli.seu.imovel.dto.RoomAreaDTO;
import br.com.meli.seu.imovel.dto.RoomDTO;
import br.com.meli.seu.imovel.entity.District;
import br.com.meli.seu.imovel.entity.Property;
import br.com.meli.seu.imovel.entity.Room;
import br.com.meli.seu.imovel.exception.PropertyNotFoundException;
import br.com.meli.seu.imovel.repository.DistrictRepository;
import br.com.meli.seu.imovel.repository.PropertyRepository;

@Service
public class PropertyService {

	private final PropertyRepository propertyRepository;
	private final RoomService roomService;
	private final DistrictService districtService;

	@Autowired
	public PropertyService(PropertyRepository propertyRepository, RoomService roomService, DistrictService districtService) {
		this.propertyRepository = propertyRepository;
		this.roomService = roomService;
		this.districtService = districtService;
	}

	public Property findPropertyById(long id){
		return propertyRepository.findById(id).orElseThrow(() -> new PropertyNotFoundException("Property not found."));
	}

	public List<Room> getRoomsByProperty(Property property) {
		return roomService.getRoomsByProperty(property);
	}

	public double calculateM2(List<Room> rooms) {
		return rooms.stream().map(r -> r.getWidth()*r.getLength()).reduce(0.0, Double::sum);
	}

	public BigDecimal calculatePropertyPrice(Property property, double propertySize) {
		return property.getDistrict().getPriceM2().multiply(new BigDecimal(propertySize));
	}

	public Room getBiggestRoom(List<Room> roomList) {
		return roomService.getBiggestRoom(roomList);
	}
	
	public List<RoomAreaDTO> getListRoomArea(List<Room> rooms){
		return rooms.stream()
				.map(room -> new RoomAreaDTO(room.getName(), roomService.getRoomArea(room)))
				.collect(Collectors.toList());
		
	}
	
	public long create(PropertyPayloadDTO propertyPayloadDTO) {
		District district = districtService.findByName(propertyPayloadDTO.getDistrictName());
		
		
		Property property = new Property(propertyPayloadDTO.getName(), district);
	    Property saveProperty = propertyRepository.save(property);
	    
	    List<Room> rooms = propertyPayloadDTO.getRooms().stream().
	    		map(room -> new Room(room.getName(), room.getWidth(), room.getLength(),saveProperty))
		        .collect(Collectors.toList());
	    
	    roomService.createList(rooms);
	    
	    return saveProperty.getId();

	}

	public PropertyDTO findById(long id) {
		Optional<Property> property = propertyRepository.findById(id);
		if(property.isEmpty()) 
			throw new PropertyNotFoundException("Property not found!");
		PropertyDTO propertyDTO = new PropertyDTO(property.get().getName());
		
		return propertyDTO;
	}
}
