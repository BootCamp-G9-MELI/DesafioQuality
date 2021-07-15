package br.com.meli.seu.imovel.unit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.apache.coyote.ajp.AjpAprProtocol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.meli.seu.imovel.entity.Property;
import br.com.meli.seu.imovel.entity.Room;
import br.com.meli.seu.imovel.exception.NoRoomException;
import br.com.meli.seu.imovel.repository.RoomRepository;
import br.com.meli.seu.imovel.service.PropertyService;

@SpringBootTest
public class PropertyServiceTest {

	private PropertyService propertyService;
	
	@Mock
	private RoomRepository roomRepository;
	
	@BeforeEach
	private void init() {
		propertyService = new PropertyService(roomRepository);
	}
	
	@Test
	void mustCalculateM2OfProperty(){
		
		Property property = new Property(1, "Casa 01", null);
		
		List<Room> rooms = new ArrayList<>();
		rooms.add(new Room(1, "Banheiro", 20, 20, property));
		rooms.add(new Room(2, "Quarto", 20, 20, property));
		rooms.add(new Room(3, "Cozinha", 20, 20, property));
		rooms.add(new Room(4, "Sala", 20, 20, property));
		
		
		Mockito.when(roomRepository.findByProperty(1l)).thenReturn(rooms);
		
		assertEquals(propertyService.calculateM2(1), 1600.0);
		
	}
	
	@Test
	void mustThrowExceptionWhenPropertyHasNoRooms(){
		
		Property property = new Property(1, "Casa 01", null);
		List<Room> rooms = new ArrayList<>();
		Mockito.when(roomRepository.findByProperty(1l)).thenReturn(rooms);
		
		assertThrows(NoRoomException.class, () -> propertyService.calculateM2(1));
		
	}
	
}
