package br.com.meli.seu.imovel.unit.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.meli.seu.imovel.entity.District;
import br.com.meli.seu.imovel.repository.PropertyRepository;
import br.com.meli.seu.imovel.service.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import br.com.meli.seu.imovel.entity.Property;
import br.com.meli.seu.imovel.entity.Room;
import br.com.meli.seu.imovel.service.DistrictService;
import br.com.meli.seu.imovel.service.PropertyService;

public class PropertyServiceTest {

	private PropertyService propertyService;

	@Mock
	private PropertyRepository propertyRepository;

	@Mock
	private RoomService roomService;
	
	@Mock
	private DistrictService districtService;

	@BeforeEach
	private void init() {
		propertyService = new PropertyService(propertyRepository, roomService, districtService);
	}
	
	@Test
	void mustCalculateM2OfProperty(){
		Property property = new Property("Casa 01", null);
		
		List<Room> rooms = new ArrayList<>();
		rooms.add(new Room("Banheiro", 20, 20, property));
		rooms.add(new Room("Quarto", 20, 20, property));
		rooms.add(new Room("Cozinha", 20, 20, property));
		rooms.add(new Room("Sala", 20, 20, property));

		assertEquals(1600.0, propertyService.calculateM2(rooms));
	}

	@Test
	void mustReturnZeroWhenListIsEmpty(){
		List<Room> rooms = new ArrayList<>();
		assertEquals(0.0, propertyService.calculateM2(rooms));
	}

	@Test
	void mustCalculatePropertyPriceWhenGivenProperty(){
		District district = new District("Cerqueira César",new BigDecimal("10000.0"));
		Property property = new Property("Casa 01", district);

		assertEquals(new BigDecimal("16000000.0"), propertyService.calculatePropertyPrice(property, 1600.0));
	}
	
	
}
