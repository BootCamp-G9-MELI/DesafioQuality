package br.com.meli.seu.imovel.controller;

import br.com.meli.seu.imovel.service.CreateDatabaseService;
import io.swagger.annotations.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags="Populate")
public class CreateDatabaseController {

    private final CreateDatabaseService service;

    @Autowired
    public CreateDatabaseController(CreateDatabaseService service) {
        this.service = service;
    }

    @GetMapping("/populate-database")
    public void populateDatabase() {
        service.populateDatabase();
    }

}
