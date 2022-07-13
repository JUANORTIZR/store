package com.juandevs.prue11.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "detallesDeFacturas")
@Getter
@Setter
public class DetalleFactura {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "cantidad", nullable = false)
    private int cantidad;
    @Column(name = "precioUnitario", nullable = false)
    private float precioUnitario;
    @Column(name = "iva", nullable = false)
    private float iva;
    @Column(name = "total", nullable = false)
    private float total;
    @ManyToOne
    @JoinColumn(name = "idProducto")
    private Producto producto;
    
}
