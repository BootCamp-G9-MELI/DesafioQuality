package br.com.meli.seu.imovel.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import br.com.meli.seu.imovel.entity.Property;
import br.com.meli.seu.imovel.exception.PropertyNotFoundException;
import br.com.meli.seu.imovel.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.meli.seu.imovel.entity.Room;
import br.com.meli.seu.imovel.exception.RoomNotFoundException;
import br.com.meli.seu.imovel.repository.RoomRepository;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {

	private final PropertyRepository propertyRepository;
	private final RoomService roomService;

	@Autowired
	public PropertyService(PropertyRepository propertyRepository, RoomService roomService) {
		this.propertyRepository = propertyRepository;
		this.roomService = roomService;
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
}
