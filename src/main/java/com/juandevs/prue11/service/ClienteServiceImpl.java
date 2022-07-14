package com.juandevs.prue11.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juandevs.prue11.entity.Cliente;
import com.juandevs.prue11.repository.ClienteRepository;
import com.juandevs.prue11.request.Response;
import com.juandevs.prue11.service.interfaces.IClienteService;

@Service
public class ClienteServiceImpl implements IClienteService{

    @Autowired
    private ClienteRepository clienteRepository;


    @Override
    @Transactional(readOnly = true)
    public Response<Iterable<Cliente>> findAll() {
        try {
            return new Response<Iterable<Cliente>>("Consulta realizada con exito", true, clienteRepository.findAll());
        } catch (Exception e) {
            return new Response<Iterable<Cliente>>(e.getMessage(), false, null);
        } 
    }


    @Override
    @Transactional(readOnly = true)
    public Response<Optional<Cliente>> findById(String id) {
        try {
            Optional<Cliente> cliente = clienteRepository.findById(id);

            if(!cliente.isPresent()) return new Response<Optional<Cliente>>("Cliente no encontrado",false,cliente);
        
            return new Response<Optional<Cliente>>("Cliente encontrado",false,cliente);
        } catch (Exception e) {
            return new Response<Optional<Cliente>>(e.getMessage(),false,null);
        }
    }

    @Override
    @Transactional
    public Response<Cliente> save(Cliente cliente) {
        try {
            if(cliente == null) {
                return new Response<Cliente>("Los datos son nulos", false, cliente);
            }
    
            if(clienteRepository.findById(cliente.getIdentificacion()).isPresent()) {
                return  new Response<Cliente>("El cliente ya se encuentra registrado", false, cliente);
            }
    
            return new Response<Cliente>("Cliente guardado", true, clienteRepository.save(cliente));
        } catch (Exception e) {
            return new Response<Cliente>(e.getMessage(), false, cliente);
        }
    }

    @Override
    public Response<Cliente> update(Cliente cliente) {
           
        try {
            if(cliente == null) {
                return new Response<Cliente>("Los datos son nulos", false, cliente);
            }
    
            if(!clienteRepository.findById(cliente.getIdentificacion()).isPresent()) {
                return new Response<Cliente>("El cliente no se encuentra registrado", false, cliente);
            }
    
            return new Response<Cliente>("Cliente actualizado", true, clienteRepository.save(cliente));
        } catch (Exception e) {
            return new Response<Cliente>(e.getMessage(), true, cliente);
        }
        
    }

    @Override
    @Transactional
    public Response<Optional<Cliente>> deleteById(String id) {
        try {
            Optional<Cliente> cliente = clienteRepository.findById(id);

            if(!cliente.isPresent()) return new Response<Optional<Cliente>>("Cliente no encontrado",false,cliente);
            
            clienteRepository.deleteById(id);
            return new Response<Optional<Cliente>>("Cliente eliminado",false,cliente);
        } catch (Exception e) {
            return new Response<Optional<Cliente>>(e.getMessage(),false,null);
        }
    }
    
}
