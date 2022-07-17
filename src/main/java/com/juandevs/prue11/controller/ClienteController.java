package com.juandevs.prue11.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.juandevs.prue11.entity.Cliente;
import com.juandevs.prue11.request.Response;
import com.juandevs.prue11.service.ClienteServiceImpl;


@RestController

@RequestMapping("/api/cliente")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })

public class ClienteController {

    @Autowired
    ClienteServiceImpl clienteService;


    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<?> findAll(@RequestHeader(value = "Authorization") String token) {
        
        Response<Iterable<Cliente>> response = clienteService.findAll(token);
        if (!response.isStatus())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody Cliente cliente, @RequestHeader(value = "Authorization") String token) {
        
        Response<Cliente> response = clienteService.save(cliente, token);
        if (!response.isStatus())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findById(@PathVariable String id, @RequestHeader(value = "Authorization") String token) {        
        Response<Optional<Cliente>> response = clienteService.findById(id, token);
        if (!response.isStatus())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody Cliente clienteDetail, @PathVariable String id, @RequestHeader(value = "Authorization") String token) {
               
        Response<Cliente> response = clienteService.update(clienteDetail, id, token);
        if(!response.isStatus()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable String id, @RequestHeader(value = "Authorization") String token) {
       
        var response = clienteService.deleteById(id,token);
        if (!response.isStatus())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

}
