package br.com.meli.seu.imovel.repository;

import br.com.meli.seu.imovel.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District,Long> {
}
