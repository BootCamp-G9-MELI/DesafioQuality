package br.com.meli.seu.imovel.dto;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class DistrictDTO {
    @NotBlank(message = "O nome do bairro não pode estar vazio.")
    @Size(max = 45, message = "O comprimento do nome do bairro não pode exceder 45 caracteres.")
    private String name;

    @NotNull(message = "O valor não pode ser vazio.")
    @DecimalMin(value = "0.0", inclusive = false, message = "O valor deve ser maior que 0.")
    @Digits(integer = 13, fraction = 2, message = "Máximo permitido de 13 digitos e 2 casas decimais.")
    private BigDecimal priceM2;

    public DistrictDTO(String name, BigDecimal priceM2) {
        this.name = name;
        this.priceM2 = priceM2;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPriceM2() {
        return priceM2;
    }
}
