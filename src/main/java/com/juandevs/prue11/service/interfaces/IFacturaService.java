package com.juandevs.prue11.service.interfaces;

import java.util.Optional;


import com.juandevs.prue11.entity.Factura;
import com.juandevs.prue11.request.Response;

public interface IFacturaService {

    public Response<Iterable<Factura>> findAll(String token);

    public Response<Iterable<Factura>> findByIdCliente(String id, String token);

    public Response<Optional<Factura>> findById(int id, String token);

    public Response<Factura> save(Factura factura, String token);

    public Response<Factura> update(Factura factura,int id, String token);

}
