package com.juandevs.prue11.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class Usuario {
    
    @Id
    @Column(name = "nombreUsuario", unique = true)
    private String nombreUsuario;
    @Column(name = "clave", nullable = false)
    private String clave;
    @ManyToMany
    @JoinColumn(name = "idUsuario")
    private List<Rol> roles;
}
