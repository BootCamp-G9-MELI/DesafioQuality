package br.com.meli.seu.imovel.unit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.meli.seu.imovel.entity.Property;
import br.com.meli.seu.imovel.entity.Room;
import br.com.meli.seu.imovel.exception.RoomNotFoundException;
import br.com.meli.seu.imovel.repository.RoomRepository;
import br.com.meli.seu.imovel.service.PropertyService;
import br.com.meli.seu.imovel.service.RoomService;

public class RoomServiceTest {
	
	@Autowired
	private RoomService roomService;
	
	@Mock
	RoomRepository roomRepository;
	
	@BeforeEach
	private void init() {
		roomService = new RoomService(roomRepository);
	}
	
	
	@Test
	public void mustReturnBiggestRoom() {
		Property property = new Property(1, "Casa 01", null);
		List<Room> rooms = new ArrayList<>();
		
		rooms.add(new Room(1, "Banheiro", 4, 3, property));
		rooms.add(new Room(2, "Quarto", 8, 7, property));
		rooms.add(new Room(3, "Cozinha", 15, 10, property));
		rooms.add(new Room(4, "Sala", 20, 20, property));
		
		Room maiorRoom = roomService.getBiggestRoom(rooms);
		
		assertEquals("Sala",maiorRoom.getName());
		
	}
	
	@Test
	public void mustReturnWhenListRoomIsEmpty() {
		List<Room> rooms = new ArrayList<>();
		
		assertThrows(RoomNotFoundException.class,() -> roomService.getBiggestRoom(rooms));
		
	}

}
