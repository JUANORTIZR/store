package com.juandevs.prue11.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "clientes")
@Getter
@Setter
public class Cliente {
    @Id
    @Column(name = "identificacion", nullable = false)
    private String identificacion;
    @Column(name = "nombres", nullable = false)
    private String nombres;
    @Column(name = "apellidos", nullable = false)
    private String apellidos;
    @Column(name = "correo", nullable = false)
    private String correo;
    @OneToMany(cascade = CascadeType.ALL, targetEntity = Telefono.class)
    @JoinColumn(name = "idCliente", referencedColumnName = "identificacion")
    private List<Telefono> telefonos;
    @OneToMany(cascade = CascadeType.ALL, targetEntity = Direccion.class)
    @JoinColumn(name = "idCliente", referencedColumnName = "identificacion")
    private List<Direccion> direcciones;
    @OneToMany(cascade = CascadeType.ALL, targetEntity = Factura.class)
    @JoinColumn(name = "idCliente", referencedColumnName = "identificacion")
    private List<Factura> facturas;
}
