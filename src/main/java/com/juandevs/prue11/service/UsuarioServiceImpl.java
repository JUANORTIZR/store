package com.juandevs.prue11.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juandevs.prue11.entity.Usuario;
import com.juandevs.prue11.repository.UsuarioRepository;
import com.juandevs.prue11.security.Encritar;
import com.juandevs.prue11.service.interfaces.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;
    


    @Override
    @Transactional(readOnly = true)
    public Iterable<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> findById(String nombreUsuario) {
        return usuarioRepository.findById(nombreUsuario);
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        usuario.setClave(Encritar.getEncrypt(usuario.getClave()));
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public void deleteById(String nombreUsuario) {
        usuarioRepository.deleteById(nombreUsuario);
    }

    @Override
    @Transactional
    public Usuario verificarDatos(Usuario usuario) {
        usuario.setClave(Encritar.getEncrypt(usuario.getClave()));

        var usuarioComparar = usuarioRepository.findById(usuario.getNombreUsuario());
        if(!usuarioComparar.isPresent()) return null;
   
        if(usuarioComparar.get().getClave().equals(usuario.getClave())) {return usuarioComparar.get();}

        return null;
    }
    
}
