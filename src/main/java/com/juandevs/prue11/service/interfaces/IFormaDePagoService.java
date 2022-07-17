package com.juandevs.prue11.service.interfaces;

import java.util.Optional;

import com.juandevs.prue11.entity.FormasDePago;
import com.juandevs.prue11.request.Response;


public interface IFormaDePagoService {

    public Response<Iterable<FormasDePago>> findAll(String token);

    public Response<Optional<FormasDePago>> findById(int id, String token);

    public Response<FormasDePago> save(FormasDePago formaDePago, String token);

    public Response<FormasDePago> update(FormasDePago formaDepago, int i, String token);

    public Response<Optional<FormasDePago>> deleteById(int id, String token);
}
