package com.juandevs.prue11.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juandevs.prue11.entity.Rol;
import com.juandevs.prue11.repository.RolRepository;
import com.juandevs.prue11.service.interfaces.IRolService;

@Service
public class RolServiceImpl implements IRolService{

    @Autowired
    private RolRepository rolRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Iterable<Rol> findAll() {
        return rolRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Rol> findById(int id) {
        return rolRepository.findById(id);
    }

    @Override
    @Transactional
    public Rol save(Rol rol) {
        return rolRepository.save(rol);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        rolRepository.deleteById(id);
    }
    
}
