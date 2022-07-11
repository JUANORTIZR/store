package com.juandevs.prue11.controller;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.juandevs.prue11.entity.Cliente;
import com.juandevs.prue11.service.ClienteServiceImpl;

@RestController

@RequestMapping("/api/cliente")

public class ClienteController {

    @Autowired
    ClienteServiceImpl clienteServices;
    

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Iterable<Cliente> findAll() {
        return clienteServices.findAll();
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody Cliente cliente) {
        return  ResponseEntity.status(HttpStatus.CREATED).body(clienteServices.save(cliente));
    }


    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findById(@PathVariable String id) {
        Optional<Cliente> user = clienteServices.findById(id);

        if(!user.isPresent()) return ResponseEntity.notFound().build();
        
        return ResponseEntity.ok(user);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody Cliente userDetail, @PathVariable String id){
        Optional<Cliente> user = clienteServices.findById(id);

        if(!user.isPresent()) return ResponseEntity.notFound().build();
    
        user.get().setNombres(userDetail.getNombres());
        user.get().setApellidos(userDetail.getApellidos());
        user.get().setCorreo(userDetail.getCorreo());
        
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteServices.save(user.get()));
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable String id){
        Optional<Cliente> user = clienteServices.findById(id);

        if(!user.isPresent()) return ResponseEntity.notFound().header("Usuario eliminado","No encontrado").build();

        clienteServices.deleteById(id);

        return ResponseEntity.ok().header("Usuario eliminado","Correcto").build();
    }


}
