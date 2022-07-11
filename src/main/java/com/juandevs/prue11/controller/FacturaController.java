package com.juandevs.prue11.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.juandevs.prue11.entity.Factura;
import com.juandevs.prue11.service.FacturaServiceImpl;

@RestController

@RequestMapping("/api/factura")

public class FacturaController {
    
    @Autowired
    private FacturaServiceImpl facturaService;

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody Factura factura) {
        return ResponseEntity.status(HttpStatus.OK).body(facturaService.save(factura));
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<?> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(facturaService.findAll());
    }

    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    public Optional<Factura> findById(@PathVariable int id){
        return facturaService.findById(id);
    }
}
