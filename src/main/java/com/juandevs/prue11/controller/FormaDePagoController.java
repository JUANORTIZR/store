package com.juandevs.prue11.controller;

import java.util.Iterator;
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

import com.juandevs.prue11.entity.FormasDePago;
import com.juandevs.prue11.request.Response;
import com.juandevs.prue11.service.FormaDePagoServiceImpl;


@RestController

@RequestMapping("/api/formaDePago")

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
    RequestMethod.DELETE})
public class FormaDePagoController {
    
    @Autowired
    private FormaDePagoServiceImpl formaDePagoService;
  

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<?> findAll(@RequestHeader(value = "Authorization") String token) {
      
        Response<Iterable<FormasDePago>> response = formaDePagoService.findAll(token);
        if(!response.isStatus()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody FormasDePago formaDePago,@RequestHeader(value = "Authorization") String token) {
        
        Response<FormasDePago> response = formaDePagoService.save(formaDePago, token);
        if(!response.isStatus()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findById(@PathVariable int id,@RequestHeader(value = "Authorization") String token) {
        
        Response<Optional<FormasDePago>> response = formaDePagoService.findById(id, token);
        if(!response.isStatus()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody FormasDePago formaDetail, @PathVariable int id,@RequestHeader(value = "Authorization") String token){     
        
        Response<FormasDePago> response = formaDePagoService.update(formaDetail, id, token);
        if(!response.isStatus()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable int id,@RequestHeader(value = "Authorization") String token){
        
        Response<Optional<FormasDePago>> response = formaDePagoService.deleteById(id, token);
        if(response.isStatus()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
