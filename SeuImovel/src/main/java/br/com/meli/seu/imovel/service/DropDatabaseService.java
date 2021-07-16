package br.com.meli.seu.imovel.service;

import br.com.meli.seu.imovel.repository.DistrictRepository;
import br.com.meli.seu.imovel.repository.PropertyRepository;
import br.com.meli.seu.imovel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DropDatabaseService {
    private final DistrictRepository districtRepository;
    private final PropertyRepository propertyRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public DropDatabaseService(DistrictRepository districtRepository, PropertyRepository propertyRepository, RoomRepository roomRepository) {
        this.districtRepository = districtRepository;
        this.propertyRepository = propertyRepository;
        this.roomRepository = roomRepository;
    }

    public void dropDatabase() {
        roomRepository.deleteAll();
        propertyRepository.deleteAll();
        districtRepository.deleteAll();
    }
}
