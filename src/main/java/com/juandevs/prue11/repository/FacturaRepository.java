package com.juandevs.prue11.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.juandevs.prue11.entity.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer> {
    
    @Transactional
    @Query(value = "SELECT * FROM facturas where id_cliente = :identificacion;", nativeQuery = true)
    List<Factura> findAllByCliente(String identificacion);
}
