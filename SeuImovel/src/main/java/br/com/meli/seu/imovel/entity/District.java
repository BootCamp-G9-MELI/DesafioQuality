package br.com.meli.seu.imovel.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private BigDecimal priceM2;

    public District(long id, String name, BigDecimal priceM2) {
        this.id = id;
        this.name = name;
        this.priceM2 = priceM2;
    }

    public District(){

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPriceM2() {
        return priceM2;
    }

}
