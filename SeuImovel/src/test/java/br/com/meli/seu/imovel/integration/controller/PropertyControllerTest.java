package br.com.meli.seu.imovel.integration.controller;

import br.com.meli.seu.imovel.entity.Property;
import br.com.meli.seu.imovel.entity.Room;
import br.com.meli.seu.imovel.service.CreateDatabaseService;
import br.com.meli.seu.imovel.service.DropDatabaseService;
import br.com.meli.seu.imovel.service.PropertyService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
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

    @AfterEach
    public void delete() {
        dropDatabaseService.dropDatabase();
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
    public void mustThrowPropertyNotFoundException() throws Exception {
        long id = 100;
        mock.perform(get("/property/{id}/area", id))
                .andExpect(status().is4xxClientError());
    }

}