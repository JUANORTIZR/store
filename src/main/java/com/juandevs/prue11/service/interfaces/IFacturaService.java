package com.juandevs.prue11.service.interfaces;

import java.util.Optional;


import com.juandevs.prue11.entity.Factura;
import com.juandevs.prue11.request.Response;

public interface IFacturaService {

    public Response<Iterable<Factura>> findAll();

    public Response<Iterable<Factura>> findByIdCliente(String id);

    public Response<Optional<Factura>> findById(int id);

    public Response<Factura> save(Factura factura);

    public Response<Factura> update(Factura factura);

}
