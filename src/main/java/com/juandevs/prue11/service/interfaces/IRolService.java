package com.juandevs.prue11.service.interfaces;

import java.util.Optional;

import com.juandevs.prue11.entity.Rol;
import com.juandevs.prue11.request.Response;

public interface IRolService {
    public Response<Iterable<Rol>> findAll();

    public Response<Optional<Rol>> findById(int id);

    public Response<Rol> save(Rol rol);

    public Response<Rol> update(Rol rol, int id);

    public Response<Optional<Rol>> deleteById(int id);
}
