package com.juandevs.prue11.service.interfaces;

import java.util.Optional;


import com.juandevs.prue11.entity.Producto;
import com.juandevs.prue11.request.Response;

public interface IProductoService {
    public Response<Iterable<Producto>> findAll();


    public Response<Optional<Producto>> findById(int id);

    public Response<Producto> save(Producto producto);

    public Response<Optional<Producto>> deleteById(int id);

    public Response<Producto> update(Producto producto);

    
}
