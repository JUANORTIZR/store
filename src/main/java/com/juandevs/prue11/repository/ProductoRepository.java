package com.juandevs.prue11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juandevs.prue11.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{
    
}
