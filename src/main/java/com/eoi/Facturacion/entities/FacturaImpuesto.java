package com.eoi.Facturacion.entities;

import jakarta.persistence.*;

import java.io.Serializable;

/**
 * Clase que representa la relación entre una factura y un impuesto en la base de datos.
 */
@Entity // indica que esta clase es una entidad que se almacenará en una tabla de la base de datos
@Table(name = "factura_impuesto") // indica el nombre de la tabla en la base de datos que almacena esta entidad
public class FacturaImpuesto {

    /**
     * ID compuesto de la factura y el impuesto.
     */
    @EmbeddedId // indica que esta propiedad es una clave compuesta embebida en otra entidad
    private FacturaImpuestoId id;

    /**
     * Factura asociada al impuesto.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    // indica la relación many-to-one con la entidad Invoice, con carga perezosa (LAZY)
    @MapsId("facturaId")
    // indica que la propiedad facturaId de FacturaImpuestoId se utilizará como clave externa hacia la entidad Invoice
    @JoinColumn(name = "factura_id") // indica el nombre de la columna en la tabla que almacena la relación con Invoice
    private Invoice factura;

    /**
     * Impuesto asociado a la factura.
     */
    @ManyToOne(fetch = FetchType.LAZY) // indica la relación many-to-one con la entidad Tax, con carga perezosa (LAZY)
    @MapsId("impuestoId")
    // indica que la propiedad impuestoId de FacturaImpuestoId se utilizará como clave externa hacia la entidad Tax
    @JoinColumn(name = "impuesto_id") // indica el nombre de la columna en la tabla que almacena la relación con Tax
    private Tax impuesto;

    /**
     * Total del impuesto.
     */
    @Column(name = "total_impuesto") // indica el nombre de la columna en la tabla que almacena el total del impuesto
    private Double totalImpuesto;

    /**
     * Devuelve el ID compuesto de la factura y el impuesto.
     *
     * @return el ID compuesto de la factura y el impuesto
     */
    public FacturaImpuestoId getId() {
        return id;
    }

    /**
     * Establece el ID compuesto de la factura y el impuesto.
     *
     * @param id el ID compuesto de la factura y el impuesto
     */
    public void setId(FacturaImpuestoId id) {
        this.id = id;
    }

    /**
     * Devuelve la factura asociada al impuesto.
     *
     * @return la factura asociada al impuesto
     */
    public Invoice getFactura() {
        return factura;
    }

    /**
     * Establece la factura asociada al impuesto.
     *
     * @param factura la factura asociada al impuesto
     */
    public void setFactura(Invoice factura) {
        this.factura = factura;
    }

    /**
     * Devuelve el impuesto asociado a la factura.
     *
     * @return el impuesto asociado a la factura
     */
    public Tax getImpuesto() {
        return impuesto;
    }

    /**
     * Establece el impuesto asociado a la factura.
     *
     * @param impuesto el impuesto asociado a la factura
     */
    public void setImpuesto(Tax impuesto) {
        this.impuesto = impuesto;
    }

    /**
     * Devuelve el total del impuesto.
     *
     * @return el total del impuesto
     */
    public Double getTotalImpuesto() {
        return totalImpuesto;
    }
}