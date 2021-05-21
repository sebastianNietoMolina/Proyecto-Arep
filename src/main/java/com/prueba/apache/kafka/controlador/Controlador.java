package com.prueba.apache.kafka.controlador;

import com.prueba.apache.kafka.model.CountryInformation;
import com.prueba.apache.kafka.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class Controlador {

    @Autowired
    MainService mainService;

    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public ResponseEntity<?> currentMessage(@RequestBody CountryInformation messages) {
        try {
            return new ResponseEntity<>(mainService.sendMessage(messages), HttpStatus.ACCEPTED);
        } catch (Exception   ex) {
            return new ResponseEntity<>("No se pudo hacer la petición a mensaje 1", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/message2", method = RequestMethod.GET)
    public ResponseEntity<?> currentMessage2(@RequestBody CountryInformation messages) {
        try {
            return new ResponseEntity<>(mainService.sendMessage2(messages), HttpStatus.ACCEPTED);
        } catch (Exception   ex) {
            return new ResponseEntity<>("No se pudo hacer la petición a mensaje2", HttpStatus.NOT_FOUND);
        }
    }

}
