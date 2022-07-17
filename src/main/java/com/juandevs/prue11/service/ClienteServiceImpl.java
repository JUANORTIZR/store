package com.juandevs.prue11.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juandevs.prue11.entity.Cliente;
import com.juandevs.prue11.repository.ClienteRepository;
import com.juandevs.prue11.request.Response;
import com.juandevs.prue11.service.interfaces.IClienteService;
import com.juandevs.prue11.security.JWTUtil;

@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    @Transactional(readOnly = true)
    public Response<Iterable<Cliente>> findAll(String token) {
        try {
            String nombreUsuario = jwtUtil.getKey(token);
        if (nombreUsuario == null) return new Response<Iterable<Cliente>>("token no valido", false, null);

            return new Response<Iterable<Cliente>>("Consulta realizada con exito", true, clienteRepository.findAll());
        } catch (Exception e) {
            return new Response<Iterable<Cliente>>(e.getMessage(), false, null);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Response<Optional<Cliente>> findById(String id, String token) {
        try {
            String nombreUsuario = jwtUtil.getKey(token);
            if (nombreUsuario == null)
                return new Response<Optional<Cliente>>("token no valido", false, null);

            Optional<Cliente> cliente = clienteRepository.findById(id);

            if (!cliente.isPresent())
                return new Response<Optional<Cliente>>("Cliente no encontrado", false, cliente);

            return new Response<Optional<Cliente>>("Cliente encontrado", true, cliente);
        } catch (Exception e) {
            return new Response<Optional<Cliente>>(e.getMessage(), false, null);
        }
    }

    @Override
    @Transactional
    public Response<Cliente> save(Cliente cliente, String token) {
        try {
            String nombreUsuario = jwtUtil.getKey(token);
            if (nombreUsuario == null)
                return new Response<Cliente>("token no valido", false, null);
            if (cliente == null) {
                return new Response<Cliente>("Los datos son nulos", false, cliente);
            }

            if (clienteRepository.findById(cliente.getIdentificacion()).isPresent()) {
                return new Response<Cliente>("El cliente ya se encuentra registrado", false, cliente);
            }

            return new Response<Cliente>("Cliente guardado", true, clienteRepository.save(cliente));
        } catch (Exception e) {
            return new Response<Cliente>(e.getMessage(), false, cliente);
        }
    }

    @Override
    public Response<Cliente> update(Cliente clienteDetail, String id, String token) {

        try {
            if (clienteDetail == null)
                return new Response<Cliente>("Los datos son nulos", false, clienteDetail);

            String nombreUsuario = jwtUtil.getKey(token);
            if (nombreUsuario == null)
                return new Response<Cliente>("token no valido", false, null);

            Optional<Cliente> cliente = clienteRepository.findById(id);
            if (!cliente.isPresent())
                return new Response<Cliente>("El cliente no se encuentra registrado", false, clienteDetail);

            cliente.get().setNombres(clienteDetail.getNombres());
            cliente.get().setApellidos(clienteDetail.getApellidos());
            cliente.get().setCorreo(clienteDetail.getCorreo());

            return new Response<Cliente>("Cliente actualizado", true, clienteRepository.save(cliente.get()));
        } catch (Exception e) {
            return new Response<Cliente>(e.getMessage(), true, clienteDetail);
        }

    }

    @Override
    @Transactional
    public Response<Optional<Cliente>> deleteById(String id, String token) {
        try {
            String nombreUsuario = jwtUtil.getKey(token);
            if (nombreUsuario == null)
                return new Response<Optional<Cliente>>("token no valido", false, null);

            Optional<Cliente> cliente = clienteRepository.findById(id);

            if (!cliente.isPresent())
                return new Response<Optional<Cliente>>("Cliente no encontrado", false, cliente);

            clienteRepository.deleteById(id);
            return new Response<Optional<Cliente>>("Cliente eliminado", true, cliente);
        } catch (Exception e) {
            return new Response<Optional<Cliente>>(e.getMessage(), false, null);
        }
    }

}
