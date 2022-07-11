package com.juandevs.prue11.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.juandevs.prue11.entity.Factura;
import com.juandevs.prue11.repository.FacturaRepository;
import com.juandevs.prue11.service.interfaces.IFacturaService;

@Service
public class FacturaServiceImpl implements IFacturaService{

    @Autowired
    private FacturaRepository facturaRepository;

    @Override
    public Iterable<Factura> findAll() {
        return facturaRepository.findAll();
    }

    @Override
    public Iterable<Factura> findByIdCliente(int id) {
        return null;
    }

    @Override
    public Page<Factura> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Factura> findById(int id) {
        return facturaRepository.findById(id);
    }

    @Override
    public Factura save(Factura factura) {
        return facturaRepository.save(factura);
    }

    @Override
    public void deleteById(int id) {
        facturaRepository.deleteById(id);
    }
    
}
