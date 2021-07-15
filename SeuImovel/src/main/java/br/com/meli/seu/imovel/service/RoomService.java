package br.com.meli.seu.imovel.service;

import br.com.meli.seu.imovel.entity.Property;
import br.com.meli.seu.imovel.entity.Room;
import br.com.meli.seu.imovel.exception.RoomNotFoundException;
import br.com.meli.seu.imovel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
