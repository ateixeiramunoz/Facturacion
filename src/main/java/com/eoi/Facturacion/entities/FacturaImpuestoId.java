package com.eoi.Facturacion.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class FacturaImpuestoId implements Serializable {
    @Column(name = "factura_id")
    private Long facturaId;

    @Column(name = "impuesto_id")
    private Long impuestoId;

    // getters y setters
}