package com.juandevs.prue11.service.interfaces;

import java.util.Optional;


import com.juandevs.prue11.entity.Cliente;
import com.juandevs.prue11.request.Response;

public interface IClienteService {
    
    public Response<Iterable<Cliente>> findAll();

    public Response<Optional<Cliente>> findById(String id);

    public Response<Cliente> save(Cliente cliente);

    public Response<Optional<Cliente>> deleteById(String id);
    
    public Response<Cliente> update(Cliente cliente);

}
