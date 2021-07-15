package br.com.meli.seu.imovel.unit.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.meli.seu.imovel.entity.District;
import br.com.meli.seu.imovel.repository.PropertyRepository;
import br.com.meli.seu.imovel.service.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.meli.seu.imovel.entity.Property;
import br.com.meli.seu.imovel.entity.Room;
import br.com.meli.seu.imovel.exception.RoomNotFoundException;
import br.com.meli.seu.imovel.repository.RoomRepository;
import br.com.meli.seu.imovel.service.PropertyService;

public class PropertyServiceTest {

	private PropertyService propertyService;

	@Mock
	private PropertyRepository propertyRepository;

	@BeforeEach
	private void init() {
		propertyService = new PropertyService(propertyRepository);
	}

	@Test
	void mustCalculateM2OfProperty(){
		Property property = new Property(1, "Casa 01", null);
		
		List<Room> rooms = new ArrayList<>();
		rooms.add(new Room(1, "Banheiro", 20, 20, property));
		rooms.add(new Room(2, "Quarto", 20, 20, property));
		rooms.add(new Room(3, "Cozinha", 20, 20, property));
		rooms.add(new Room(4, "Sala", 20, 20, property));

		assertEquals(1600.0, propertyService.calculateM2(rooms));
	}

	@Test
	void mustReturnZeroWhenListIsEmpty(){
		List<Room> rooms = new ArrayList<>();
		assertEquals(0.0, propertyService.calculateM2(rooms));
	}

	@Test
	void mustCalculatePropertyPriceWhenGivenProperty(){
		District district = new District(1, "Cerqueira César",new BigDecimal("10000.0"));
		Property property = new Property(1, "Casa 01", district);

		assertEquals(new BigDecimal("16000000.0"), propertyService.calculatePropertyPrice(property, 1600.0));
	}
}
