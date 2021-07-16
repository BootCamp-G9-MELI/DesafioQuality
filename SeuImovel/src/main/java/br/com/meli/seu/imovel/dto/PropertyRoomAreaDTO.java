package br.com.meli.seu.imovel.dto;

import java.util.List;

public class PropertyRoomAreaDTO {
	private String name;
	private List<RoomAreaDTO> roomAreaDTOs;
	
	public PropertyRoomAreaDTO(String name, List<RoomAreaDTO> roomAreaDTOs) {
		super();
		this.name = name;
		this.roomAreaDTOs = roomAreaDTOs;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<RoomAreaDTO> getRoomAreaDTOs() {
		return roomAreaDTOs;
	}
	public void setRoomAreaDTOs(List<RoomAreaDTO> roomAreaDTOs) {
		this.roomAreaDTOs = roomAreaDTOs;
	}
	
	
}
