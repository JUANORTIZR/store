package com.juandevs.prue11.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.juandevs.prue11.entity.Producto;
import com.juandevs.prue11.repository.ProductoRepository;
import com.juandevs.prue11.request.Response;
import com.juandevs.prue11.service.interfaces.IProductoService;


@Service
public class ProductoServiceImpl implements IProductoService{

    Response<Producto> response;

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    @Transactional
    public Iterable<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    public Page<Producto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Producto> findById(int id) {
        return productoRepository.findById(id);
    }

    @Override
    public Response<Producto> save(Producto producto) {
           
        if(producto == null) {
            return response = new Response<Producto>("Los datos son nulos", false, producto);
        }

        if(productoRepository.findById(producto.getId()).isPresent()) {
            return response = new Response<Producto>("El producto ya se encuentra registrado", false, producto);
        }

        return response = new Response<Producto>("Producto guardado", true, productoRepository.save(producto));
    }

    @Override
    public Response<Producto> update(Producto producto) {
           
        if(producto == null) {
            return response = new Response<Producto>("Los datos son nulos", false, producto);
        }

        if(!productoRepository.findById(producto.getId()).isPresent()) {
            return response = new Response<Producto>("El producto no se encuentra registrado", false, producto);
        }

        return response = new Response<Producto>("Producto actualizado", true, productoRepository.save(producto));
    }

    @Override
    public void deleteById(int id) {
        productoRepository.deleteById(id);
    }
    
}
