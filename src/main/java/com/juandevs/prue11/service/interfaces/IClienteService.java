package com.juandevs.prue11.service.interfaces;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.juandevs.prue11.entity.Cliente;

public interface IClienteService {
    
    public Iterable<Cliente> findAll();

    public Page<Cliente> findAll(Pageable pageable);

    public Optional<Cliente> findById(String id);

    public Cliente save(Cliente cliente);

    public void deleteById(String id);

}
