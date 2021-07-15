package br.com.meli.seu.imovel.repository;

import br.com.meli.seu.imovel.entity.District;
import br.com.meli.seu.imovel.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property,Long> {
}
