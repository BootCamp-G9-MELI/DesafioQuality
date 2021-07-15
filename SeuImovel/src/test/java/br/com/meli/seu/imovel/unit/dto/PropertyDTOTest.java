package br.com.meli.seu.imovel.unit.dto;

import br.com.meli.seu.imovel.dto.PropertyDTO;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PropertyDTOTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void validateSuccessfulCreation() {
        PropertyDTO dto = new PropertyDTO("Casa do Joao 23");
        Set<ConstraintViolation<PropertyDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void validateBlankName() {
        PropertyDTO dto = new PropertyDTO("");
        Set<ConstraintViolation<PropertyDTO>> violations = validator.validate(dto);
        System.out.println(violations.size());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getMessage().equals("O nome da propriedade não pode estar vazio.")));
    }

    @Test
    public void validateNameSize() {
        PropertyDTO dto = new PropertyDTO("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        Set<ConstraintViolation<PropertyDTO>> violations = validator.validate(dto);
        assertEquals("O comprimento do nome da propriedade não pode exceder 30 caracteres.",
                violations.iterator().next().getMessage());
    }

    @Test
    public void validateUppercase() {
        PropertyDTO dto = new PropertyDTO("casa pequena");
        Set<ConstraintViolation<PropertyDTO>> violations = validator.validate(dto);
        assertEquals("O nome da propriedade deve começar com uma letra maiúscula.",
                violations.iterator().next().getMessage());
    }

}
