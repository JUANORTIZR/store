package com.juandevs.prue11.service.interfaces;

import java.util.Optional;

import com.juandevs.prue11.entity.Rol;

public interface IRolService {
    public Iterable<Rol> findAll();

    public Optional<Rol> findById(int id);

    public Rol save(Rol rol);

    public void deleteById(int id);
}
