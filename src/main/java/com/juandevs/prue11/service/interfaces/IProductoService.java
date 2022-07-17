package com.juandevs.prue11.service.interfaces;

import java.util.Optional;


import com.juandevs.prue11.entity.Producto;
import com.juandevs.prue11.request.Response;

public interface IProductoService {
    public Response<Iterable<Producto>> findAll(String token);

    public Response<Optional<Producto>> findById(int id, String token);

    public Response<Producto> save(Producto producto, String token);

    public Response<Optional<Producto>> deleteById(int id, String token);

    public Response<Producto> update(Producto producto, int id, String token);

    
}
