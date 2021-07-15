package br.com.meli.seu.imovel.unit.dto;

import br.com.meli.seu.imovel.dto.DistrictDTO;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class DistrictDTOTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void validateSuccessfulCreation() {
        DistrictDTO dto = new DistrictDTO("Vila Matias", new BigDecimal(55000));
        Set<ConstraintViolation<DistrictDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void validateBlankName() {
        DistrictDTO dto = new DistrictDTO("", new BigDecimal(55000));
        Set<ConstraintViolation<DistrictDTO>> violations = validator.validate(dto);
        assertEquals("O nome do bairro não pode estar vazio.", violations.iterator().next().getMessage());
    }

    @Test
    public void validateNameSize() {
        DistrictDTO dto = new DistrictDTO("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                new BigDecimal(55000));
        Set<ConstraintViolation<DistrictDTO>> violations = validator.validate(dto);
        assertEquals("O comprimento do nome do bairro não pode exceder 45 caracteres.",
                violations.iterator().next().getMessage());
    }

    @Test
    public void validateBlankPrice() {
        DistrictDTO dto = new DistrictDTO("Vila Matias", new BigDecimal("0.0"));
        Set<ConstraintViolation<DistrictDTO>> violations = validator.validate(dto);
        assertEquals("O valor deve ser maior que 0.", violations.iterator().next().getMessage());
    }

    @Test
    public void validateOverprice() {
        DistrictDTO dto = new DistrictDTO("Vila Matias", new BigDecimal("11111111111111.11"));
        Set<ConstraintViolation<DistrictDTO>> violations = validator.validate(dto);
        assertEquals("Máximo permitido de 13 digitos e 2 casas decimais.",
                violations.iterator().next().getMessage());
    }
}
