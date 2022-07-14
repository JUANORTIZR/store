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

import com.juandevs.prue11.entity.Cliente;
import com.juandevs.prue11.service.ClienteServiceImpl;

@RestController

@RequestMapping("/api/cliente")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
    RequestMethod.DELETE})


public class ClienteController {

    @Autowired
    ClienteServiceImpl clienteService;
    

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findAll());
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody Cliente cliente) {
        return  ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(cliente));
    }


    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findById(id));
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody Cliente clienteDetail, @PathVariable String id){
        Optional<Cliente> cliente = clienteService.findById(id).getObject();

        if(!cliente.isPresent()) return ResponseEntity.notFound().build();
     
        cliente.get().setNombres(clienteDetail.getNombres());
        cliente.get().setApellidos(clienteDetail.getApellidos());
        cliente.get().setCorreo(clienteDetail.getCorreo());
        
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.update(cliente.get()));
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable String id){
        Optional<Cliente> cliente = clienteService.findById(id).getObject();

        if(!cliente.isPresent()) return ResponseEntity.notFound().header("Cliente eliminado","No encontrado").build();

        var response = clienteService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    
    }


}
