package com.juandevs.prue11.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "facturas")
@Getter
@Setter
public class Factura {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "estado")
    private String estado;
    @Column(name = "fechaVenta")
    private Date fechaVenta;
    @Column(name = "fechaEntrega")
    private Date fechaEntrega;
    @Column(name = "direccionEntrega")
    private String direccionEntrega;
    @Column(name = "total")
    private float total;
    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;
    @OneToMany(cascade = CascadeType.ALL, targetEntity = DetalleFactura.class)
    @JoinColumn(name = "idFactura", referencedColumnName = "id")
    private List<DetalleFactura> detallesDeFacturas;
    @ManyToMany
    @JoinColumn(name = "idFactura")
    private List<FormasDePago> formaDePago;
}
