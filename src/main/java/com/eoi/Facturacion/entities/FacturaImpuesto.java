package com.eoi.Facturacion.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "factura_impuesto")
public class FacturaImpuesto {
    @EmbeddedId
    private FacturaImpuestoId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("facturaId")
    @JoinColumn(name = "factura_id")
    private Invoice factura;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("impuestoId")
    @JoinColumn(name = "impuesto_id")
    private Tax impuesto;

    @Column(name = "total_impuesto")
    private Double totalImpuesto;

    // getters y setters
}


