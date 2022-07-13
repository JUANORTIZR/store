package com.juandevs.prue11.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juandevs.prue11.entity.FormasDePago;
import com.juandevs.prue11.repository.FormaDePagoRepository;
import com.juandevs.prue11.service.interfaces.IFormaDePagoService;


@Service
public class FormaDePagoServiceImpl implements IFormaDePagoService{

    @Autowired
    FormaDePagoRepository formaDePagoRepository;

    @Override
    public Iterable<FormasDePago> findAll() {
        return formaDePagoRepository.findAll();
    }

    @Override
    public Optional<FormasDePago> findById(int id) {
        return formaDePagoRepository.findById(id);
    }

    @Override
    public FormasDePago save(FormasDePago formaDePago) {
        return formaDePagoRepository.save(formaDePago);
    }

    @Override
    public void deleteById(int id) {
        formaDePagoRepository.deleteById(id);
    }
    
}
