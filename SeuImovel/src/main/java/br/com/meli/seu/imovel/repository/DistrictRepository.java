package br.com.meli.seu.imovel.repository;

import br.com.meli.seu.imovel.entity.District;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District,Long> {
	@Query(value = "SELECT * FROM District d WHERE d.name= :nameDistrict LIMIT 1", nativeQuery = true)
	Optional<District> findByName(@Param("nameDistrict") String nameDistrict);
}
