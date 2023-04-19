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
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String codigoFactura;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate billingDate;
    private BigDecimal amount;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany
    @JoinTable(
            name = "factura_impuesto",
            joinColumns = @JoinColumn(name = "factura_id"),
            inverseJoinColumns = @JoinColumn(name = "impuesto_id")
    )
    private List<Tax> impuestos;

    @OneToMany(mappedBy = "invoice")
    @OrderBy("callDate ASC")
    private List<PhoneCall> phoneCalls;

    public BigDecimal getImpuestoTotalPorTasa(BigDecimal tasa) {
        BigDecimal totalImpuesto = BigDecimal.ZERO;
        for (Tax impuesto : impuestos) {
            if (impuesto.getPercentage().equals(tasa)) {
                totalImpuesto = totalImpuesto.add(amount.multiply(impuesto.getPercentage().divide(BigDecimal.valueOf(100))));
            }
        }
        return totalImpuesto;
    }

}