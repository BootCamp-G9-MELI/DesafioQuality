package br.com.meli.seu.imovel.integration.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.meli.seu.imovel.dto.PropertyPayloadDTO;
import br.com.meli.seu.imovel.dto.RoomAreaDTO;
import br.com.meli.seu.imovel.dto.RoomDTO;
import br.com.meli.seu.imovel.entity.District;
import br.com.meli.seu.imovel.entity.Property;
import br.com.meli.seu.imovel.entity.Room;
import br.com.meli.seu.imovel.service.CreateDatabaseService;
import br.com.meli.seu.imovel.service.PropertyService;

@SpringBootTest
@AutoConfigureMockMvc
public class PropertyControllerTest {

	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ObjectMapper mapper;
	

	@Autowired
	private PropertyService propertyService;

	@Autowired
	private CreateDatabaseService createDatabaseService;

	@BeforeEach
	public void init() {
		createDatabaseService.populateDatabase();
	}

	@Test
	public void mustReturnPropertyAreaDTO() throws Exception {
		long id = 1;
		Property property = propertyService.findPropertyById(id);
		List<Room> roomList = propertyService.getRoomsByProperty(property);
		double propertyArea = propertyService.calculateM2(roomList);

		mock.perform(get("/property/{id}/area", id))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.area").value(propertyArea));
	}

	@Test
	public void mustThrowPropertyNotFoundExceptionArea() throws Exception {
		long id = 100;
		mock.perform(get("/property/{id}/area", id))
		.andExpect(status().is4xxClientError());
	}

	@Test
	public void mustReturnPropertyPriceIsM2() throws Exception {
		long id = 1;
		Property property = propertyService.findPropertyById(id);
		List<Room> roomList = propertyService.getRoomsByProperty(property);
		double propertyArea = propertyService.calculateM2(roomList);
		BigDecimal priceProperty = propertyService.calculatePropertyPrice(property, propertyArea);
		mock.perform(get("/property/{id}/price", id))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.price").value(priceProperty.doubleValue()));
	}

	@Test
	public void mustThrowPropertyNotFoundExceptionPrice() throws Exception {
		long id = 100;
		mock.perform(get("/property/{id}/price", id))
		.andExpect(status().is4xxClientError());
	}

	@Test
	public void mustReturnBiggestPropertyRoom() throws Exception {
		long id = 1;
		Property property = propertyService.findPropertyById(id);
		List<Room> roomList = propertyService.getRoomsByProperty(property);
		Room biggestRoom = propertyService.getBiggestRoom(roomList);
		RoomDTO roomDTO = RoomDTO.convert(biggestRoom);

		mock.perform(get("/property/{id}/biggest-room", id))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.name").value(roomDTO.getName()))
		.andExpect(jsonPath("$.width").value(roomDTO.getWidth()))
		.andExpect(jsonPath("$.length").value(roomDTO.getLength()));
	}


	@Test
	public void mustThrowPropertyNotFoundExceptionBiggestRoom() throws Exception {
		long id = 100;
		mock.perform(get("/property/{id}/biggest-room", id))
		.andExpect(status().is4xxClientError());
	}

	@Test
	public void mustReturnPropertyRoomArea() throws Exception {
		long id = 1;
		Property property = propertyService.findPropertyById(id);
		List<Room> roomList = propertyService.getRoomsByProperty(property);

		List<RoomAreaDTO> roomAreaDTOs = propertyService.getListRoomArea(roomList);

		mock.perform(get("/property/{id}/rooms-area",id))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.name").value(property.getName()))
		.andExpect(jsonPath("$.roomAreaDTOs").isArray())
		.andExpect(jsonPath("$.roomAreaDTOs",hasSize(roomAreaDTOs.size())))
		.andExpect(jsonPath("$.roomAreaDTOs[0].name").value(roomAreaDTOs.get(0).getName()))
		.andExpect(jsonPath("$.roomAreaDTOs[0].aream2").value(roomAreaDTOs.get(0).getAream2()));
	}
	
	@Test
	public void mustThrowPropertyNotFoundExceptionPropertyRoomArea() throws Exception {
		long id = 100;
		mock.perform(get("/property/{id}/rooms-area", id))
		.andExpect(status().is4xxClientError());
	}
	
	@Test
	public void mustCreateProperty() throws Exception {
		List<RoomDTO> roomDTO = Arrays.asList(new RoomDTO("Sala", 10, 10));
		PropertyPayloadDTO propertyPayloadDTO = new PropertyPayloadDTO("Casa Criada", "Morumbi", roomDTO);
		
		String payload = mapper.writeValueAsString(propertyPayloadDTO);
		
		mock.perform(post("/property/create").contentType(MediaType.APPLICATION_JSON)
				.content(payload)).andExpect(status().isCreated());
	}
	
	@Test
	public void mustThrowDistrictNotFoundException() throws Exception {
		List<RoomDTO> roomDTO = Arrays.asList(new RoomDTO("Sala", 10, 10));
		PropertyPayloadDTO propertyPayloadDTO = new PropertyPayloadDTO("Casa Criada", "DNER", roomDTO);
		
		String payload = mapper.writeValueAsString(propertyPayloadDTO);
		
		mock.perform(post("/property/create").contentType(MediaType.APPLICATION_JSON)
				.content(payload)).andExpect(status().is4xxClientError());
	}
	
	public void mustReturnProperty() throws Exception {
		long id = 1;
		District d = new District("Vila Mariana", new BigDecimal(10000));
		Property p = new Property("Casa 123",d);
		
		 mock.perform(get("/property/{id}"))
			 .andExpect(status().isOk())
			 .andExpect(content().contentType(MediaType.APPLICATION_JSON))
			 .andExpect(jsonPath("$.name").value(p.getName()));
	}
	
	@Test
	public void mustThrowPropertyNotFoundException() throws Exception {
		long id = 100;
		
		mock.perform(get("/property/{id}", id))
			.andExpect(status().is4xxClientError());
	}
	

}
