package com.juandevs.prue11.service.interfaces;

import java.util.Optional;

import com.juandevs.prue11.entity.Usuario;

public interface IUsuarioService {
    public Iterable<Usuario> findAll();

    public Optional<Usuario> findById(String nombreUsuario);

    public Usuario save(Usuario usuario);

    public void deleteById(String nombreUsuario);

    public Usuario verificarDatos(Usuario usuario);
}
