package com.juandevs.prue11.service.interfaces;

import java.util.Optional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;

import com.juandevs.prue11.entity.Producto;
import com.juandevs.prue11.request.Response;

public interface IProductoService {
    public Iterable<Producto> findAll();

    public Page<Producto> findAll(Pageable pageable);

    public Optional<Producto> findById(int id);

    public Response<Producto> save(Producto producto);

    public void deleteById(int id);

    public Response<Producto> update(Producto producto);

    
}
