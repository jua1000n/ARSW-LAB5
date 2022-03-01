/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;


import com.google.gson.Gson;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 *
 * @author hcadavid
 */
@RestController
@RequestMapping(value = "/blueprint")
public class BlueprintAPIController {

    @Autowired
    BlueprintsServices blueprintsServices;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> manejadorGetRecursoXX() {
        try {
            Set<Blueprint> blueprintSet = blueprintsServices.getAllBlueprints();
            //obtener datos que se enviarán a través del API
            System.out.println(blueprintSet);
            return new ResponseEntity<>(new Gson().toJson(blueprintSet), HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(ex.getMessage());
            return new ResponseEntity<>("Error bla bla bla", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{author}")
    public ResponseEntity<?> getBlueprintsByAuthor(@PathVariable String author) {
        try {
            Set<Blueprint> blueprintSet = blueprintsServices.getBlueprintsByAuthor(author);
            //obtener datos que se enviarán a través del API
            System.out.println(blueprintSet);
            return new ResponseEntity<>(new Gson().toJson(blueprintSet), HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(ex.getMessage());
            return new ResponseEntity<>("Error bla bla bla", HttpStatus.NOT_FOUND);
        }
    }

    private String convertStringJson(Set<Blueprint> cadena) {

        List<Blueprint> blueprintList = new ArrayList<>(cadena);
        String bluCadena = "{\"blueprints\": ";
        for (Blueprint blueprint: blueprintList) {
            bluCadena += "{\"Author\": \"" + blueprint.getAuthor() + "\", \"Name\": \"" + blueprint.getName() + "\", \"Points\": \"" + blueprint.getPoints() + "\"}";
        }
        return bluCadena;
    }
}

