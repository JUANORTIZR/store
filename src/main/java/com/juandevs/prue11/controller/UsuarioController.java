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

import com.juandevs.prue11.entity.Usuario;
import com.juandevs.prue11.service.UsuarioServiceImpl;

@RestController

@RequestMapping("/api/usuario")
public class UsuarioController {
    
    @Autowired
    private UsuarioServiceImpl usuarioService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody Usuario usuario){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.save(usuario));
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Iterable<Usuario> findAll() {
        return usuarioService.findAll();
    }

    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findById(@PathVariable(name = "id") String nombreUsuario) {
        Optional<Usuario> usuario = usuarioService.findById(nombreUsuario);

        if(!usuario.isPresent()) return ResponseEntity.notFound().build();
        
        return ResponseEntity.ok(usuario);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody Usuario usuarioDetail, @PathVariable(name = "id") String id){
        Optional<Usuario> usuario = usuarioService.findById(id);

        if(!usuario.isPresent()) return ResponseEntity.notFound().build();
    
        usuario.get().setClave(usuarioDetail.getClave());
        
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario.get()));
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable(name = "id") String id){
        Optional<Usuario> usuario = usuarioService.findById(id);

        if(!usuario.isPresent()) return ResponseEntity.notFound().header("Usuario eliminado","No encontrado").build();

        usuarioService.deleteById(id);

        return ResponseEntity.ok().header("Usuario eliminado","Correcto").build();
    }

}
