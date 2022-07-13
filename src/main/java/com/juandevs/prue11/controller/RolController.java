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

import com.juandevs.prue11.entity.Rol;
import com.juandevs.prue11.service.RolServiceImpl;

@RestController
@RequestMapping("/api/rol")
public class RolController {

    @Autowired
    private RolServiceImpl rolService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody Rol rol){
        return ResponseEntity.status(HttpStatus.OK).body(rolService.save(rol));
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Iterable<Rol> findAll() {
        return rolService.findAll();
    }


    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findById(@PathVariable int id) {
        Optional<Rol> usuario = rolService.findById(id);

        if(!usuario.isPresent()) return ResponseEntity.notFound().build();
        
        return ResponseEntity.ok(usuario);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody Rol rolDetail, @PathVariable int id){
        Optional<Rol> rol = rolService.findById(id);

        if(!rol.isPresent()) return ResponseEntity.notFound().build();
    
        rol.get().setNombre(rolDetail.getNombre());
        
        return ResponseEntity.status(HttpStatus.CREATED).body(rolService.save(rol.get()));
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable int id){
        Optional<Rol> rol = rolService.findById(id);

        if(!rol.isPresent()) return ResponseEntity.notFound().header("Rol eliminado","No encontrado").build();

        rolService.deleteById(id);

        return ResponseEntity.ok().header("Rol eliminado","Correcto").build();
    }
    
}
