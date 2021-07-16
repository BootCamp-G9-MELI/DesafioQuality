package br.com.meli.seu.imovel.service;

import br.com.meli.seu.imovel.entity.Property;
import br.com.meli.seu.imovel.entity.Room;
import br.com.meli.seu.imovel.exception.RoomNotFoundException;
import br.com.meli.seu.imovel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class RoomService {
	private final RoomRepository roomRepository;

	@Autowired

	public RoomService(RoomRepository roomRepository) {
		this.roomRepository = roomRepository;
	}

	public List<Room> getRoomsByProperty(Property property){
		List<Room> rooms = roomRepository.findByProperty(property.getId());
		if (rooms.isEmpty()) throw new RoomNotFoundException("Rooms not found.");
		return rooms;
	}

	public Room getBiggestRoom(List<Room> rooms) {
		if (rooms.isEmpty()) throw new RoomNotFoundException("Rooms not found.");
		
		return rooms.stream().reduce(new Room(), ((room, nextRoom) -> {
			if(room.getLength() * room.getWidth() > nextRoom.getLength() * nextRoom.getWidth())
				return room;
			return nextRoom;
		}));
	}
}
