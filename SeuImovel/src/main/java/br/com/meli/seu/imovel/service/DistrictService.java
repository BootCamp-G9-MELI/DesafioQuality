package br.com.meli.seu.imovel.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meli.seu.imovel.entity.District;
import br.com.meli.seu.imovel.exception.DistrictNotFoundException;
import br.com.meli.seu.imovel.repository.DistrictRepository;

@Service
public class DistrictService {
	
	private final DistrictRepository districtRepository;
	
	@Autowired
	public DistrictService(DistrictRepository districtRepository){
		this.districtRepository = districtRepository;
	}
	
	public District findByName(String name) {
		Optional<District> distriOptional = districtRepository.findByName(name);
		if(distriOptional.isEmpty())
			throw new DistrictNotFoundException("Disctrict not found!");
		return distriOptional.get();
	}

}
