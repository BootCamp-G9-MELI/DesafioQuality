package br.com.meli.seu.imovel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.meli.seu.imovel.entity.Room;
import br.com.meli.seu.imovel.exception.NoRoomException;
import br.com.meli.seu.imovel.repository.RoomRepository;

public class PropertyService {

	
	private RoomRepository roomRepository;
	
	@Autowired
	public PropertyService(RoomRepository roomRepository) {
		this.roomRepository = roomRepository;
	}

	public double calculateM2(long id) {
		List<Room> rooms = roomRepository.findByProperty(id);
		if (rooms.isEmpty()) {
			throw new NoRoomException("Propriedade id: " + id + " não tem cômodos.");
		}
		return rooms.stream().map(r -> r.getWidth()*r.getLength()).reduce(0.0, Double::sum);			
	}
	
}
