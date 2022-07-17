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

import com.juandevs.prue11.entity.Rol;
import com.juandevs.prue11.request.Response;
import com.juandevs.prue11.service.RolServiceImpl;


@RestController
@RequestMapping("/api/rol")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
public class RolController {

    @Autowired
    private RolServiceImpl rolService;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody Rol rol, @RequestHeader(value = "Authorization") String token) {

        Response<Rol> response = rolService.save(rol, token);
        if (!response.isStatus())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<?> findAll(@RequestHeader(value = "Authorization") String token) {

        Response<Iterable<Rol>> response = rolService.findAll(token);
        if (!response.isStatus())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findById(@PathVariable int id, @RequestHeader(value = "Authorization") String token) {

        Response<Optional<Rol>> response = rolService.findById(id, token);
        if (!response.isStatus())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody Rol rolDetail, @PathVariable int id,
            @RequestHeader(value = "Authorization") String token) {

        Response<Rol> response = rolService.update(rolDetail, id, token);
        if (!response.isStatus())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable int id, @RequestHeader(value = "Authorization") String token) {

        Response<Optional<Rol>> response = rolService.deleteById(id, token);
        if (!response.isStatus())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
