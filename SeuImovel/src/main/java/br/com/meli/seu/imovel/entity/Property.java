package br.com.meli.seu.imovel.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Properties;
import java.util.Set;

@Entity
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "district_id" , nullable = false)
    private District district;


    public Property(long id, String name, District district) {
        this.id = id;
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
