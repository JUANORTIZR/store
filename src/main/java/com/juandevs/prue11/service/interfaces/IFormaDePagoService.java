package com.juandevs.prue11.service.interfaces;

import java.util.Optional;

import com.juandevs.prue11.entity.FormasDePago;


public interface IFormaDePagoService {

    public Iterable<FormasDePago> findAll();

    public Optional<FormasDePago> findById(int id);

    public FormasDePago save(FormasDePago formaDePago);

    public void deleteById(int id);
}
