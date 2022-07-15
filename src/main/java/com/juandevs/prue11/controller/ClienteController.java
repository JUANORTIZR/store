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
import com.juandevs.prue11.security.JWTUtil;

@RestController

@RequestMapping("/api/cliente")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
    RequestMethod.DELETE})


public class ClienteController {

    @Autowired
    ClienteServiceImpl clienteService;
    
    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<?> findAll(@RequestHeader(value = "Authorization") String token) {
        String nombreUsuario = jwtUtil.getKey(token);
        if(nombreUsuario == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("token no valido", false, token));

        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findAll());
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody Cliente cliente,@RequestHeader(value = "Authorization") String token) {
        String nombreUsuario = jwtUtil.getKey(token);
        if(nombreUsuario == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("token no valido", false, token));

        return  ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(cliente));
    }


    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findById(@PathVariable String id,@RequestHeader(value = "Authorization") String token) {
        String nombreUsuario = jwtUtil.getKey(token);
        if(nombreUsuario == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("token no valido", false, token));

        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findById(id));
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody Cliente clienteDetail, @PathVariable String id,@RequestHeader(value = "Authorization") String token){
        String nombreUsuario = jwtUtil.getKey(token);
        if(nombreUsuario == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("token no valido", false, token));

        Optional<Cliente> cliente = clienteService.findById(id).getObject();

        if(!cliente.isPresent()) return ResponseEntity.notFound().build();
     
        cliente.get().setNombres(clienteDetail.getNombres());
        cliente.get().setApellidos(clienteDetail.getApellidos());
        cliente.get().setCorreo(clienteDetail.getCorreo());
        
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.update(cliente.get()));
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable String id,@RequestHeader(value = "Authorization") String token){
        String nombreUsuario = jwtUtil.getKey(token);
        if(nombreUsuario == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("token no valido", false, token));

        Optional<Cliente> cliente = clienteService.findById(id).getObject();

        if(!cliente.isPresent()) return ResponseEntity.notFound().header("Cliente eliminado","No encontrado").build();

        var response = clienteService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    
    }


}
