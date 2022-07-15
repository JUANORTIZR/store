package com.juandevs.prue11.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.juandevs.prue11.entity.Usuario;
import com.juandevs.prue11.request.Response;
import com.juandevs.prue11.security.JWTUtil;
import com.juandevs.prue11.service.UsuarioServiceImpl;



@RestController

@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
    RequestMethod.DELETE})

public class AuthController {
    
    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    public Response<String> save(@RequestBody Usuario usuario){
        var usuarioLoguear = usuarioService.verificarDatos(usuario);
        if(usuarioLoguear != null){
            String token = jwtUtil.create(usuarioLoguear.getNombreUsuario(), usuarioLoguear.getNombreUsuario());
            return new Response<String>("Logueado", true, token);
        }
        return new Response<String>("Logueado", true, "Datos incorrectos");
    }

    
}
