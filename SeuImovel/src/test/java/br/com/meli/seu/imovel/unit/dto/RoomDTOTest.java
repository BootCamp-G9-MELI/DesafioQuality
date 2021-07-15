package br.com.meli.seu.imovel.unit.dto;

import br.com.meli.seu.imovel.dto.PropertyDTO;
import br.com.meli.seu.imovel.dto.RoomDTO;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoomDTOTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void validateSuccessfulCreation() {
        RoomDTO dto = new RoomDTO("Banheiro", 20, 15);
        Set<ConstraintViolation<RoomDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void validateBlankName() {
        RoomDTO dto = new RoomDTO("", 20, 15);
        Set<ConstraintViolation<RoomDTO>> violations = validator.validate(dto);
        assertTrue(violations.stream()
                .anyMatch(v -> v.getMessage().equals("O nome do cômodo não pode estar vazio.")));
    }

    @Test
    public void validateNameSize() {
        RoomDTO dto = new RoomDTO("Banheiro banheiro banheiro banheiro", 20, 15);
        Set<ConstraintViolation<RoomDTO>> violations = validator.validate(dto);
        assertEquals("O comprimento do nome do cômodo não pode exceder 30 caracteres.",
                violations.iterator().next().getMessage());
    }

    @Test
    public void validateUppercase() {
        RoomDTO dto = new RoomDTO("banheirinho", 20, 15);
        Set<ConstraintViolation<RoomDTO>> violations = validator.validate(dto);
        assertEquals("O nome do cômodo deve começar com uma letra maiúscula.",
                violations.iterator().next().getMessage());
    }

    @Test
    public void validateEmptyLength() {
        RoomDTO dto = new RoomDTO("Banheiro", 20, 0);
        Set<ConstraintViolation<RoomDTO>> violations = validator.validate(dto);
        assertEquals("O valor do comprimento não pode ser vazio.",
                violations.iterator().next().getMessage());
    }

    @Test
    public void validateLengthSize() {
        RoomDTO dto = new RoomDTO("Banheiro", 20, 34);
        Set<ConstraintViolation<RoomDTO>> violations = validator.validate(dto);
        assertEquals("O comprimento máximo permitido por cômodo é de 33 metros.",
                violations.iterator().next().getMessage());
    }

    @Test
    public void validateEmptyWidth() {
        RoomDTO dto = new RoomDTO("Banheiro", 0, 15);
        Set<ConstraintViolation<RoomDTO>> violations = validator.validate(dto);
        assertEquals("O valor da largura não pode ser vazio.",
                violations.iterator().next().getMessage());
    }

    @Test
    public void validateWidthSize() {
        RoomDTO dto = new RoomDTO("Banheiro", 26, 15);
        Set<ConstraintViolation<RoomDTO>> violations = validator.validate(dto);
        assertEquals("A largura máxima permitida por cômodo é de 25 metros.",
                violations.iterator().next().getMessage());
    }

}
