package com.juandevs.prue11.service.interfaces;

import java.util.Optional;

import com.juandevs.prue11.entity.FormasDePago;
import com.juandevs.prue11.request.Response;


public interface IFormaDePagoService {

    public Response<Iterable<FormasDePago>> findAll();

    public Response<Optional<FormasDePago>> findById(int id);

    public Response<FormasDePago> save(FormasDePago formaDePago);

    public Response<FormasDePago> update(FormasDePago formaDepago);

    public Response<Optional<FormasDePago>> deleteById(int id);
}
