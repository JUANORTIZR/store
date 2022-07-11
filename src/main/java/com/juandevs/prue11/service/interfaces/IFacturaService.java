package com.juandevs.prue11.service.interfaces;

import java.util.Optional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;

import com.juandevs.prue11.entity.Factura;

public interface IFacturaService {

    public Iterable<Factura> findAll();

    public Iterable<Factura> findByIdCliente(int id);

    public Page<Factura> findAll(Pageable pageable);

    public Optional<Factura> findById(int id);

    public Factura save(Factura factura);

    public void deleteById(int id);
}
