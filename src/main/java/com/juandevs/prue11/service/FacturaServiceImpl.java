package com.juandevs.prue11.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juandevs.prue11.entity.Factura;
import com.juandevs.prue11.repository.FacturaRepository;
import com.juandevs.prue11.request.Response;
import com.juandevs.prue11.service.interfaces.IFacturaService;
import com.juandevs.prue11.security.JWTUtil;

@Service
public class FacturaServiceImpl implements IFacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    @Transactional(readOnly = true)
    public Response<Iterable<Factura>> findAll(String token) {
        try {

            String nombreUsuario = jwtUtil.getKey(token);
            if (nombreUsuario == null)
                return new Response<Iterable<Factura>>("token no valido", false, null);

            return new Response<Iterable<Factura>>("Consulta realizada con exito", true, facturaRepository.findAll());
        } catch (Exception e) {
            return new Response<Iterable<Factura>>(e.getMessage(), false, null);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Response<Iterable<Factura>> findByIdCliente(String id, String token) {
        try {
            String nombreUsuario = jwtUtil.getKey(token);
            if (nombreUsuario == null)
                return new Response<Iterable<Factura>>("token no valido", false, null);

            return new Response<Iterable<Factura>>("Consulta realizada con exito", true,
                    facturaRepository.findAllByCliente(id));
        } catch (Exception e) {
            return new Response<Iterable<Factura>>(e.getMessage(), false, null);
        }
    }

    @Override
    public Response<Optional<Factura>> findById(int id, String token) {
        try {

            String nombreUsuario = jwtUtil.getKey(token);
            if (nombreUsuario == null)
                return new Response<Optional<Factura>>("token no valido", false, null);

            Optional<Factura> factura = facturaRepository.findById(id);

            if (!factura.isPresent())
                return new Response<Optional<Factura>>("Factura no encontrado", false, factura);

            return new Response<Optional<Factura>>("Factura encontrada", false, factura);
        } catch (Exception e) {
            return new Response<Optional<Factura>>(e.getMessage(), false, null);
        }
    }

    @Override
    public Response<Factura> save(Factura factura, String token) {
        try {
            String nombreUsuario = jwtUtil.getKey(token);
            if (nombreUsuario == null)
                return new Response<Factura>("token no valido", false, null);

            if (factura == null) {
                return new Response<Factura>("Los datos son nulos", false, factura);
            }

            if (facturaRepository.findById(factura.getId()).isPresent()) {
                return new Response<Factura>("La factura ya se encuentra registrada", false, factura);
            }

            return new Response<Factura>("Factura guardado", true, facturaRepository.save(factura));
        } catch (Exception e) {
            return new Response<Factura>(e.getMessage(), false, factura);
        }
    }

    @Override
    public Response<Factura> update(Factura facturaDetail, int id, String token) {
        try {
            String nombreUsuario = jwtUtil.getKey(token);
        if (nombreUsuario == null)
            return new Response<Factura>("token no valido", false, null);
        
            if (facturaDetail == null) {
                return new Response<Factura>("Los datos son nulos", false, facturaDetail);
            }
            Optional<Factura> factura = facturaRepository.findById(id);
            if (!factura.isPresent()) {
                return new Response<Factura>("la factura no se encuentra registrada", false, facturaDetail);
            }

            factura.get().setEstado(facturaDetail.getEstado());

            return new Response<Factura>("Factura actualizada", true, facturaRepository.save(factura.get()));
        } catch (Exception e) {
            return new Response<Factura>(e.getMessage(), true, facturaDetail);
        }

    }

}
