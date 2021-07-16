package br.com.meli.seu.imovel.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Properties;
import java.util.Set;

@Entity
public class Property {

    @Id
    @SequenceGenerator(name = "property_id_generator", sequenceName = "property_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "property_sequence")
    private long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "district_id" , nullable = false)
    private District district;

    public Property(String name, District district) {
        this.name = name;
        this.district = district;
    }

    public Property(){

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public District getDistrict() {
        return district;
    }

}
