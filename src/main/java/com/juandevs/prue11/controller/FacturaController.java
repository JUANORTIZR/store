package com.juandevs.prue11.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.juandevs.prue11.entity.Factura;
import com.juandevs.prue11.request.Response;
import com.juandevs.prue11.service.FacturaServiceImpl;

@RestController

@RequestMapping("/api/factura")

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
    RequestMethod.DELETE})
public class FacturaController {
    
    @Autowired
    private FacturaServiceImpl facturaService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody Factura factura) {
        var response = facturaService.save(factura);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<?> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(facturaService.findAll());
    }

    @RequestMapping(value = "/findByCliente", method = RequestMethod.GET)
    public ResponseEntity<?> findByCliente( @PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(facturaService.findByIdCliente(id));
    }

    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findById(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(facturaService.findById(id));
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody Factura facturaDetail, @PathVariable int id){
        Response<Optional<Factura>> factura = facturaService.findById(id);   
        factura.getObject().get().setEstado(facturaDetail.getEstado());
        return ResponseEntity.status(HttpStatus.OK).body(facturaService.update(factura.getObject().get()));
    }
}
