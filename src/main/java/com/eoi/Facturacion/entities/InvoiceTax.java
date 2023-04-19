package com.eoi.Facturacion.entities;

import com.eoi.Facturacion.controllers.CustomerRestController;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "invoice_tax")
public class InvoiceTax {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tax_id")
    private Tax tax;
    private BigDecimal amount;
    public BigDecimal calculateAmount() {
        BigDecimal taxPercentage = tax.getPercentage();
        BigDecimal invoiceAmount = invoice.getAmount();
        BigDecimal invoiceTaxAmount = invoiceAmount.multiply(taxPercentage).divide(BigDecimal.valueOf(100)).add(tax.getAmount());
        return invoiceTaxAmount;
    }

    public InvoiceTax(Invoice invoice, Tax tax, BigDecimal amount) {
        this.invoice = invoice;
        this.tax = tax;
        this.amount = amount;
    }



}