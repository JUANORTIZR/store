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

import com.juandevs.prue11.entity.Producto;
import com.juandevs.prue11.request.Response;
import com.juandevs.prue11.service.ProductoServiceImpl;
import com.juandevs.prue11.security.JWTUtil;

@RestController
@RequestMapping("/api/producto")

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
    RequestMethod.DELETE})

public class ProductoController {

    @Autowired
    private ProductoServiceImpl productoService;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody Producto producto, @RequestHeader(value = "Authorization") String token) {
        String nombreUsuario = jwtUtil.getKey(token);
        if(nombreUsuario == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("token no valido", false, token));

        return ResponseEntity.status(HttpStatus.OK).body(productoService.save(producto));
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<?> findAll(@RequestHeader(value = "Authorization") String token) {
        String nombreUsuario = jwtUtil.getKey(token);
        if(nombreUsuario == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("token no valido", false, token));

        return ResponseEntity.status(HttpStatus.OK).body(productoService.findAll());
    }

    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findById(@PathVariable int id,@RequestHeader(value = "Authorization") String token) {
        String nombreUsuario = jwtUtil.getKey(token);
        if(nombreUsuario == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("token no valido", false, token));

        return ResponseEntity.status(HttpStatus.OK).body(productoService.findById(id));

    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody Producto productoDetail, @PathVariable int id, @RequestHeader(value = "Authorization") String token) {
        
        String nombreUsuario = jwtUtil.getKey(token);
        if(nombreUsuario == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("token no valido", false, token));

        Optional<Producto> producto = productoService.findById(id).getObject();
        if (!producto.isPresent())
            return ResponseEntity.notFound().build();

        producto.get().setIva(productoDetail.getIva());
        producto.get().setDescripcion(productoDetail.getDescripcion());
        producto.get().setPrecioUnitario(productoDetail.getPrecioUnitario());

        return ResponseEntity.status(HttpStatus.OK).body(productoService.update(producto.get()));

    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable int id, @RequestHeader(value = "Authorization") String token) {
        String nombreUsuario = jwtUtil.getKey(token);
        if(nombreUsuario == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("token no valido", false, token));

        Optional<Producto> producto = productoService.findById(id).getObject();

        if(!producto.isPresent()) return ResponseEntity.notFound().header("Usuario eliminado","No encontrado").build();

        var response = productoService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
