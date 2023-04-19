package com.eoi.Facturacion.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clase que representa el ID compuesto de la relación entre una factura y un impuesto.
 */
@Embeddable // indica que esta clase se puede embeber en otra entidad
public class FacturaImpuestoId implements Serializable {

    /**
     * ID de la factura.
     */
    @Column(name = "factura_id") // indica el nombre de la columna en la tabla que almacena el ID de la factura
    private Long facturaId;

    /**
     * ID del impuesto.
     */
    @Column(name = "impuesto_id") // indica el nombre de la columna en la tabla que almacena el ID del impuesto
    private Long impuestoId;

    /**
     * Devuelve el ID de la factura.
     * @return el ID de la factura
     */
    public Long getFacturaId() {
        return facturaId;
    }

    /**
     * Establece el ID de la factura.
     * @param facturaId el ID de la factura
     */
    public void setFacturaId(Long facturaId) {
        this.facturaId = facturaId;
    }

    /**
     * Devuelve el ID del impuesto.
     * @return el ID del impuesto
     */
    public Long getImpuestoId() {
        return impuestoId;
    }

    /**
     * Establece el ID del impuesto.
     * @param impuestoId el ID del impuesto
     */
    public void setImpuestoId(Long impuestoId) {
        this.impuestoId = impuestoId;
    }

    /**
     * Método generado automáticamente para comparar objetos de esta clase.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacturaImpuestoId that = (FacturaImpuestoId) o;
        return Objects.equals(facturaId, that.facturaId) && Objects.equals(impuestoId, that.impuestoId);
    }

    /**
     * Método generado automáticamente para obtener el código hash de un objeto de esta clase.
     */
    @Override
    public int hashCode() {
        return Objects.hash(facturaId, impuestoId);
    }
}

