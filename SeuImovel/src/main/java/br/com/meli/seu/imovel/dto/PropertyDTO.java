package br.com.meli.seu.imovel.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PropertyDTO {

    @NotBlank(message = "O nome da propriedade não pode estar vazio.")
    @Size(max = 30, message = "O comprimento do nome da propriedade não pode exceder 30 caracteres.")
    @Pattern(regexp = "^([A-Z]{1})([a-z 0-9A-Z]{1,})$", message = "O nome da propriedade deve começar com uma letra maiúscula.")
    private String name;

    public PropertyDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
