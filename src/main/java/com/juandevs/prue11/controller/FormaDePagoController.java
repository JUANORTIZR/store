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

import com.juandevs.prue11.entity.FormasDePago;
import com.juandevs.prue11.request.Response;
import com.juandevs.prue11.service.FormaDePagoServiceImpl;
import com.juandevs.prue11.security.JWTUtil;

@RestController

@RequestMapping("/api/formaDePago")

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
    RequestMethod.DELETE})
public class FormaDePagoController {
    
    @Autowired
    private FormaDePagoServiceImpl formaDePagoService;
    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<?> findAll(@RequestHeader(value = "Authorization") String token) {

        String nombreUsuario = jwtUtil.getKey(token);
        if(nombreUsuario == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("token no valido", false, token));


        return ResponseEntity.status(HttpStatus.OK).body(formaDePagoService.findAll());
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody FormasDePago formaDePago,@RequestHeader(value = "Authorization") String token) {
        String nombreUsuario = jwtUtil.getKey(token);
        if(nombreUsuario == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("token no valido", false, token));

        return  ResponseEntity.status(HttpStatus.CREATED).body(formaDePagoService.save(formaDePago));
    }


    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findById(@PathVariable int id,@RequestHeader(value = "Authorization") String token) {
        String nombreUsuario = jwtUtil.getKey(token);
        if(nombreUsuario == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("token no valido", false, token));

        return ResponseEntity.status(HttpStatus.OK).body(formaDePagoService.findById(id));
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody FormasDePago formaDetail, @PathVariable int id,@RequestHeader(value = "Authorization") String token){
      
        String nombreUsuario = jwtUtil.getKey(token);
        if(nombreUsuario == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("token no valido", false, token));
      
        Optional<FormasDePago> formaDePago = formaDePagoService.findById(id).getObject();

        if(!formaDePago.isPresent()) return ResponseEntity.notFound().build();
    
        formaDePago.get().setFormaDePago(formaDetail.getFormaDePago());
        
        return ResponseEntity.status(HttpStatus.CREATED).body(formaDePagoService.update(formaDePago.get()));
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable int id,@RequestHeader(value = "Authorization") String token){
        String nombreUsuario = jwtUtil.getKey(token);
        if(nombreUsuario == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("token no valido", false, token));

        return ResponseEntity.status(HttpStatus.OK).body(formaDePagoService.deleteById(id));
    }


}
