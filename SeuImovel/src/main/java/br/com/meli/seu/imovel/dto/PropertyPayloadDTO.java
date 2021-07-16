package br.com.meli.seu.imovel.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PropertyPayloadDTO {
	@NotBlank(message = "O nome da propriedade não pode estar vazio.")
    @Size(max = 30, message = "O comprimento do nome da propriedade não pode exceder 30 caracteres.")
    @Pattern(regexp = "^([A-Z]{1})([a-z 0-9A-Z]{1,})$", message = "O nome da propriedade deve começar com uma letra maiúscula.")
    private String name;
	private String districtName;
	private List<RoomDTO> rooms;
	
	public PropertyPayloadDTO(String name, String districtName, List<RoomDTO> rooms) {
		super();
		this.name = name;
		this.districtName = districtName;
		this.rooms = rooms;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public List<RoomDTO> getRooms() {
		return rooms;
	}

	public void setRooms(List<RoomDTO> rooms) {
		this.rooms = rooms;
	}

	@Override
	public String toString() {
		return "PropertyPayloadDTO [name=" + name + ", districtName=" + districtName + ", rooms=" + rooms + "]";
	}
	
	
}
