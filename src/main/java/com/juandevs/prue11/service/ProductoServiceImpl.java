package com.juandevs.prue11.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juandevs.prue11.entity.Producto;
import com.juandevs.prue11.repository.ProductoRepository;
import com.juandevs.prue11.request.Response;
import com.juandevs.prue11.service.interfaces.IProductoService;
import com.juandevs.prue11.security.JWTUtil;

@Service
public class ProductoServiceImpl implements IProductoService {

    Response<Producto> response;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    @Transactional
    public Response<Iterable<Producto>> findAll(String token) {
        try {
            String nombreUsuario = jwtUtil.getKey(token);
            if (nombreUsuario == null)
                return new Response<Iterable<Producto>>("token no valido", false, null);

            return new Response<Iterable<Producto>>("Consulta realizada con exito", true, productoRepository.findAll());
        } catch (Exception e) {
            return new Response<Iterable<Producto>>(e.getMessage(), false, null);
        }

    }

    @Override
    public Response<Optional<Producto>> findById(int id, String token) {
        try {
            String nombreUsuario = jwtUtil.getKey(token);
            if (nombreUsuario == null)
                return new Response<Optional<Producto>>("token no valido", false, null);

            Optional<Producto> producto = productoRepository.findById(id);

            if (!producto.isPresent())
                return new Response<Optional<Producto>>("Producto no encontrado", false, producto);

            return new Response<Optional<Producto>>("Producto encontrado", false, producto);
        } catch (Exception e) {
            return new Response<Optional<Producto>>(e.getMessage(), false, null);
        }

    }

    @Override
    public Response<Producto> save(Producto producto, String token) {

        try {

            String nombreUsuario = jwtUtil.getKey(token);
            if (nombreUsuario == null)
                return new Response<Producto>("token no valido", false, null);

            if (producto == null) {
                return response = new Response<Producto>("Los datos son nulos", false, producto);
            }

            if (productoRepository.findById(producto.getId()).isPresent()) {
                return response = new Response<Producto>("El producto ya se encuentra registrado", false, producto);
            }

            return response = new Response<Producto>("Producto guardado", true, productoRepository.save(producto));
        } catch (Exception e) {
            return response = new Response<Producto>(e.getMessage(), false, producto);
        }

    }

    @Override
    public Response<Producto> update(Producto productoDetail, int id, String token) {

        try {

            String nombreUsuario = jwtUtil.getKey(token);
            if (nombreUsuario == null)
                return new Response<Producto>("token no valido", false, null);

            if (productoDetail == null) {
                return response = new Response<Producto>("Los datos son nulos", false, productoDetail);
            }

            var producto = productoRepository.findById(id);
            if (!producto.isPresent()) {
                return response = new Response<Producto>("El producto no se encuentra registrado", false,
                        productoDetail);
            }

            producto.get().setIva(productoDetail.getIva());
            producto.get().setDescripcion(productoDetail.getDescripcion());
            producto.get().setPrecioUnitario(productoDetail.getPrecioUnitario());

            return response = new Response<Producto>("Producto actualizado", true,
                    productoRepository.save(producto.get()));
        } catch (Exception e) {
            return response = new Response<Producto>(e.getMessage(), true, productoDetail);
        }

    }

    @Override
    public Response<Optional<Producto>> deleteById(int id, String token) {
        try {
            String nombreUsuario = jwtUtil.getKey(token);
            if(nombreUsuario == null) return new Response<Optional<Producto>>("token no valido", false, null);

            Optional<Producto> producto = productoRepository.findById(id);

            if (!producto.isPresent())
                return new Response<Optional<Producto>>("Producto no encontrado", false, producto);

            productoRepository.deleteById(id);
            return new Response<Optional<Producto>>("Producto eliminado", true, producto);
        } catch (Exception e) {
            return new Response<Optional<Producto>>(e.getMessage(), false, null);
        }
    }

}
