package br.com.meli.seu.imovel.repository;

import br.com.meli.seu.imovel.entity.Room;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {

	@Query("select r from Room r where r.property.id = :idProperty")
	List<Room> findByProperty(@Param("idProperty") long id);
}
