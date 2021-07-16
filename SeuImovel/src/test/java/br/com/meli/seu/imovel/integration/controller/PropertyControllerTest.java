package br.com.meli.seu.imovel.integration.controller;

import br.com.meli.seu.imovel.dto.RoomDTO;
import br.com.meli.seu.imovel.entity.Property;
import br.com.meli.seu.imovel.entity.Room;
import br.com.meli.seu.imovel.service.CreateDatabaseService;
import br.com.meli.seu.imovel.service.DropDatabaseService;
import br.com.meli.seu.imovel.service.PropertyService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
//@RunWith(JUnit="Plataf")
public class PropertyControllerTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private CreateDatabaseService createDatabaseService;

    @Autowired
    private DropDatabaseService dropDatabaseService;

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

}
