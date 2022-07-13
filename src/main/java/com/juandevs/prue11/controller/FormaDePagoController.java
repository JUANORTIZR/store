package com.juandevs.prue11.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.juandevs.prue11.entity.FormasDePago;
import com.juandevs.prue11.service.FormaDePagoServiceImpl;

@RestController

@RequestMapping("/api/formaDePago")
public class FormaDePagoController {
    
    @Autowired
    private FormaDePagoServiceImpl formaDePagoService;
    

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Iterable<FormasDePago> findAll() {
        return formaDePagoService.findAll();
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody FormasDePago formaDePago) {
        return  ResponseEntity.status(HttpStatus.CREATED).body(formaDePagoService.save(formaDePago));
    }


    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findById(@PathVariable int id) {
        Optional<FormasDePago> formaDePago = formaDePagoService.findById(id);

        if(!formaDePago.isPresent()) return ResponseEntity.notFound().build();
        
        return ResponseEntity.ok(formaDePago);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody FormasDePago clienteDetail, @PathVariable int id){
        Optional<FormasDePago> formaDePago = formaDePagoService.findById(id);

        if(!formaDePago.isPresent()) return ResponseEntity.notFound().build();
    
        formaDePago.get().setFormaDePago(clienteDetail.getFormaDePago());
        
        return ResponseEntity.status(HttpStatus.CREATED).body(formaDePagoService.save(formaDePago.get()));
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable int id){
        Optional<FormasDePago> formasDePago = formaDePagoService.findById(id);

        if(!formasDePago.isPresent()) return ResponseEntity.notFound().header("forma de pago eliminado","No encontrado").build();

        formaDePagoService.deleteById(id);

        return ResponseEntity.ok().header("forma de pago eliminado","Correcto").build();
    }


}
