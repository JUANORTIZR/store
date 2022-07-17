package com.juandevs.prue11.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juandevs.prue11.entity.Rol;
import com.juandevs.prue11.repository.RolRepository;
import com.juandevs.prue11.request.Response;
import com.juandevs.prue11.service.interfaces.IRolService;
import com.juandevs.prue11.security.JWTUtil;

@Service
public class RolServiceImpl implements IRolService {

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    @Transactional(readOnly = true)
    public Response<Iterable<Rol>> findAll(String token) {
        try {
            String nombreUsuario = jwtUtil.getKey(token);
            if (nombreUsuario == null)
                return new Response<Iterable<Rol>>("token no valido", false, null);

            return new Response<Iterable<Rol>>("Consulta realizada con exito", true, rolRepository.findAll());
        } catch (Exception e) {
            return new Response<Iterable<Rol>>(e.getMessage(), false, null);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Response<Optional<Rol>> findById(int id, String token) {
        try {
            String nombreUsuario = jwtUtil.getKey(token);
            if (nombreUsuario == null)
                return new Response<Optional<Rol>>("token no valido", false, null);

            Optional<Rol> rol = rolRepository.findById(id);

            if (!rol.isPresent())
                return new Response<Optional<Rol>>("Rol no encontrado", false, rol);

            return new Response<Optional<Rol>>("Rol encontrado", false, rol);
        } catch (Exception e) {
            return new Response<Optional<Rol>>(e.getMessage(), false, null);
        }
    }

    @Override
    @Transactional
    public Response<Rol> save(Rol rol, String token) {
        try {
            String nombreUsuario = jwtUtil.getKey(token);
            if (nombreUsuario == null)
                return new Response<Rol>("token no valido", false, null);

            if (rol == null) {
                return new Response<Rol>("Los datos son nulos", false, rol);
            }

            if (rolRepository.findById(rol.getId()).isPresent()) {
                return new Response<Rol>("El rol ya se encuentra registrado", false, rol);
            }

            return new Response<Rol>("Rol guardado", true, rolRepository.save(rol));
        } catch (Exception e) {
            return new Response<Rol>(e.getMessage(), false, rol);
        }
    }

    @Override
    public Response<Rol> update(Rol rolDetail, int id, String token) {

        try {

            String nombreUsuario = jwtUtil.getKey(token);
            if (nombreUsuario == null)
                return new Response<Rol>("token no valido", false, null);

            if (rolDetail == null) {
                return new Response<Rol>("Los datos son nulos", false, rolDetail);
            }

            Optional<Rol> rol = rolRepository.findById(id);
            if (!rol.isPresent()) {
                return new Response<Rol>("El rol no se encuentra registrado", false, rolDetail);
            }
            rol.get().setNombre(rolDetail.getNombre());
            return new Response<Rol>("Rol actualizado", true, rolRepository.save(rol.get()));
        } catch (Exception e) {
            return new Response<Rol>(e.getMessage(), true, rolDetail);
        }

    }

    @Override
    @Transactional
    public Response<Optional<Rol>> deleteById(int id, String token) {
        try {
            String nombreUsuario = jwtUtil.getKey(token);
            if (nombreUsuario == null)
                return new Response<Optional<Rol>>("token no valido", false, null);

            Optional<Rol> rol = rolRepository.findById(id);

            if (!rol.isPresent())
                return new Response<Optional<Rol>>("Rol no encontrado", false, rol);

            rolRepository.deleteById(id);
            return new Response<Optional<Rol>>("Rol eliminado", true, rol);
        } catch (Exception e) {
            return new Response<Optional<Rol>>(e.getMessage(), false, null);
        }
    }

}
