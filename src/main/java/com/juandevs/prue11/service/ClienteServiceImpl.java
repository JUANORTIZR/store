package com.juandevs.prue11.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juandevs.prue11.entity.Cliente;
import com.juandevs.prue11.repository.ClienteRepository;
import com.juandevs.prue11.service.interfaces.IClienteService;

@Service
public class ClienteServiceImpl implements IClienteService{

    @Autowired
    private ClienteRepository userRepository;


    @Override
    @Transactional(readOnly = true)
    public Iterable<Cliente> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Cliente> findAll(Pageable pageable) {
        
        return userRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cliente> findById(String id) {
        
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public Cliente save(Cliente cliente) {
        return userRepository.save(cliente);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }
    
}
