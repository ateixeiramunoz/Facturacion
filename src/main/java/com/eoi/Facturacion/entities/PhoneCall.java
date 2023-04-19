package com.eoi.Facturacion.entities;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhoneCall {

    @Id // Indica que esta variable es la llave primaria de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indica que el valor de esta variable será generado automáticamente por la base de datos
    private long id;

    @Column(name = "call_date") // Indica que esta variable será mapeada a una columna llamada "call_date"
    @DateTimeFormat(pattern = "yyyy-MM-dd") // Indica que el valor de esta variable será formateado como una fecha con el patrón especificado
    @JsonFormat(pattern="yyyy-MM-dd") // Indica que el valor de esta variable será formateado como una fecha en formato JSON con el patrón especificado
    private LocalDate callDate;

    private String origin; // Indica que esta variable será mapeada a una columna llamada "origin"
    private String destination; // Indica que esta variable será mapeada a una columna llamada "destination"
    private BigDecimal duration; // Indica que esta variable será mapeada a una columna llamada "duration"
    private BigDecimal secondUnits; // Indica que esta variable será mapeada a una columna llamada "second_units"

    @Column(name = "call_price") // Indica que esta variable será mapeada a una columna llamada "call_price"
    private BigDecimal callPrice;

    @ManyToOne(fetch = FetchType.LAZY) // Indica que esta relación es una asociación many-to-one (muchos PhoneCall pueden pertenecer a un Invoice)
    @JoinColumn(name = "invoice_id") // Indica que esta relación está mapeada a una columna llamada "invoice_id"
    private Invoice invoice; // Indica que esta variable es la llave foránea que se utilizará para realizar la asociación con la entidad Invoice

    // getters y setters
}


