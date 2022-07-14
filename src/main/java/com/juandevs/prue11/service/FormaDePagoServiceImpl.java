package com.juandevs.prue11.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juandevs.prue11.entity.FormasDePago;
import com.juandevs.prue11.repository.FormaDePagoRepository;
import com.juandevs.prue11.request.Response;
import com.juandevs.prue11.service.interfaces.IFormaDePagoService;


@Service
public class FormaDePagoServiceImpl implements IFormaDePagoService{

    @Autowired
    FormaDePagoRepository formaDePagoRepository;

    @Override
    public Response<Iterable<FormasDePago>> findAll() {
        try {
            return new Response<Iterable<FormasDePago>>("Consulta realizada con exito", true, formaDePagoRepository.findAll());
        } catch (Exception e) {
            return new Response<Iterable<FormasDePago>>(e.getMessage(), false, null);
        }
    }

    @Override
    public Response<Optional<FormasDePago>> findById(int id) {
        try {
            Optional<FormasDePago> producto = formaDePagoRepository.findById(id);

            if(!producto.isPresent()) return new Response<Optional<FormasDePago>>("Producto no encontrado",false,producto);
        
            return new Response<Optional<FormasDePago>>("FormasDePago encontrado",false,producto);
        } catch (Exception e) {
            return new Response<Optional<FormasDePago>>(e.getMessage(),false,null);
        }
    }

    @Override
    public Response<FormasDePago> save(FormasDePago formasDePago) {
        try {
            if(formasDePago == null) {
                return new Response<FormasDePago>("Los datos son nulos", false, formasDePago);
            }
    
            if(formaDePagoRepository.findById(formasDePago.getId()).isPresent()) {
                return new Response<FormasDePago>("La forma de pago ya se encuentra registrado", false, formasDePago);
            }
    
            return new Response<FormasDePago>("Forma de pago guardado", true, formaDePagoRepository.save(formasDePago));
        } catch (Exception e) {
            return new Response<FormasDePago>(e.getMessage(), false, formasDePago);
        }
    }

    @Override
    public Response<FormasDePago> update(FormasDePago formaDepago) {
        try {
            if(formaDepago == null) {
                return new Response<FormasDePago>("Los datos son nulos", false, formaDepago);
            }
    
            if(!formaDePagoRepository.findById(formaDepago.getId()).isPresent()) {
                return new Response<FormasDePago>("El producto no se encuentra registrado", false, formaDepago);
            }
    
            return new Response<FormasDePago>("Forma de pago actualizada", true, formaDePagoRepository.save(formaDepago));
        } catch (Exception e) {
            return new Response<FormasDePago>(e.getMessage(), true, formaDepago);
        }
    }

    @Override
    public Response<Optional<FormasDePago>> deleteById(int id) {
        try {
            Optional<FormasDePago> formaDePago = formaDePagoRepository.findById(id);

            if(!formaDePago.isPresent()) return new Response<Optional<FormasDePago>>("Forma de pago no encontrada",false,formaDePago);
            
            formaDePagoRepository.deleteById(id);
            return new Response<Optional<FormasDePago>>("Formas de pago eliminado",false,formaDePago);
        } catch (Exception e) {
            return new Response<Optional<FormasDePago>>(e.getMessage(),false,null);
        }
    }
    
}
