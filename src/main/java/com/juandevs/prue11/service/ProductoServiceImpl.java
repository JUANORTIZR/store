package com.juandevs.prue11.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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
    public Response<Iterable<Producto>> findAll() {
        try {
            return new Response<Iterable<Producto>>("Consulta realizada con exito", true, productoRepository.findAll());
        } catch (Exception e) {
            return new Response<Iterable<Producto>>(e.getMessage(), false, null);
        }
        
    }

    
    @Override
    public Response<Optional<Producto>> findById(int id) {
        try {
            Optional<Producto> producto = productoRepository.findById(id);

            if(!producto.isPresent()) return new Response<Optional<Producto>>("Producto no encontrado",false,producto);
        
            return new Response<Optional<Producto>>("Producto encontrado",false,producto);
        } catch (Exception e) {
            return new Response<Optional<Producto>>(e.getMessage(),false,null);
        }
        
    }

    @Override
    public Response<Producto> save(Producto producto) {
        
        try {
            if(producto == null) {
                return response = new Response<Producto>("Los datos son nulos", false, producto);
            }
    
            if(productoRepository.findById(producto.getId()).isPresent()) {
                return response = new Response<Producto>("El producto ya se encuentra registrado", false, producto);
            }
    
            return response = new Response<Producto>("Producto guardado", true, productoRepository.save(producto));
        } catch (Exception e) {
            return response = new Response<Producto>(e.getMessage(), false, producto);
        }
        
    }

    @Override
    public Response<Producto> update(Producto producto) {
           
        try {
            if(producto == null) {
                return response = new Response<Producto>("Los datos son nulos", false, producto);
            }
    
            if(!productoRepository.findById(producto.getId()).isPresent()) {
                return response = new Response<Producto>("El producto no se encuentra registrado", false, producto);
            }
    
            return response = new Response<Producto>("Producto actualizado", true, productoRepository.save(producto));
        } catch (Exception e) {
            return response = new Response<Producto>(e.getMessage(), true, producto);
        }
        
    }

    @Override
    public Response<Optional<Producto>> deleteById(int id) {
        try {
            Optional<Producto> producto = productoRepository.findById(id);

            if(!producto.isPresent()) return new Response<Optional<Producto>>("Producto no encontrado",false,producto);
            
            productoRepository.deleteById(id);
            return new Response<Optional<Producto>>("Producto eliminado",false,producto);
        } catch (Exception e) {
            return new Response<Optional<Producto>>(e.getMessage(),false,null);
        }
    }
    
}
