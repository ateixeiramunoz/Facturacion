package com.eoi.Facturacion.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tax {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private BigDecimal amount;

    private BigDecimal percentage;

    @JsonIgnore
    @OneToMany(mappedBy = "tax", cascade = CascadeType.ALL)
    private Set<InvoiceTax> invoiceTaxes = new HashSet<>();


}