package com.eoi.Facturacion.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/**
 * Representa una factura emitida a un cliente.
 */
public class Invoice {

    /**
     * Identificador único de la factura.
     */
    @Id // indica que esta propiedad es la clave primaria de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY) // indica que la estrategia de generación de la clave es autoincremental
    private long id;

    /**
     * Código de la factura.
     */
    private String codigoFactura;

    /**
     * Fecha de emisión de la factura.
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd") // indica el formato en que se espera la fecha
    @JsonFormat(pattern="yyyy-MM-dd") // indica el formato en que se debe serializar la fecha en JSON
    private LocalDate billingDate;

    /**
     * Monto de la factura.
     */
    private BigDecimal amount;

    /**
     * Cliente asociado a la factura.
     */
    @JsonBackReference // indica que esta propiedad se ignorará al serializar a JSON para evitar referencias cíclicas
    @ManyToOne(fetch = FetchType.LAZY) // indica la relación many-to-one con la entidad Customer, con carga perezosa (LAZY)
    @JoinColumn(name = "customer_id") // indica el nombre de la columna en la tabla que almacena la relación
    private Customer customer;

    /**
     * Lista de impuestos asociados a la factura.
     */
    @ManyToMany // indica la relación many-to-many con la entidad Tax
    @JoinTable(
            name = "factura_impuesto", // indica el nombre de la tabla intermedia que almacena la relación
            joinColumns = @JoinColumn(name = "factura_id"), // indica el nombre de la columna que almacena la clave primaria de Invoice en la tabla intermedia
            inverseJoinColumns = @JoinColumn(name = "impuesto_id")) // indica el nombre de la columna que almacena la clave primaria de Tax en la tabla intermedia
    private List<Tax> impuestos;

    /**
     * Lista de llamadas telefónicas asociadas a la factura.
     */
    @OneToMany(mappedBy = "invoice") // indica la relación one-to-many con la entidad PhoneCall, donde Invoice es la clase dueña de la relación
    @OrderBy("callDate ASC") // indica que la lista de llamadas se ordenará por fecha de llamada ascendente
    private List<PhoneCall> phoneCalls;

    /**
     * Método para calcular el total de impuestos por una tasa dada.
     *
     * @param tasa la tasa de impuesto a utilizar para el cálculo del total
     * @return el total de impuestos para la tasa dada
     */
    public BigDecimal getImpuestoTotalPorTasa(BigDecimal tasa) {
        BigDecimal totalImpuesto = BigDecimal.ZERO;
        for (Tax impuesto : impuestos) {
            if (impuesto.getPercentage().equals(tasa)) {
                totalImpuesto = totalImpuesto.add(amount.multiply(impuesto.getPercentage().divide(BigDecimal.valueOf(100))));
            }
        }
        return totalImpuesto;
    }

    // getters y setters
}

