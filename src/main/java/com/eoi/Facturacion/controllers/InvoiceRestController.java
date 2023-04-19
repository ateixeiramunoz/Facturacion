package com.eoi.Facturacion.controllers;

import com.eoi.Facturacion.entities.Invoice;
import com.eoi.Facturacion.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController  //Genera una API REST
@RequestMapping("/api/invoice")  //url que te devuelve la lista de invoices
public class InvoiceRestController {

    @Autowired

    private InvoiceService invoiceService;

    @GetMapping
    public List<Invoice> findAll() {
        return invoiceService.findAll();
    }

    @GetMapping("/{id}")
    public Invoice findById(@PathVariable Long id) {
        Optional<Invoice> invoice = invoiceService.findById(id);
        if(invoice.isPresent())
        {
            return invoice.get();
        }
        return null;
    }

    @PostMapping

    public Invoice save(@RequestBody Invoice invoice) {
        return invoiceService.save(invoice);
    }

    @DeleteMapping("/{id}")

    public void deleteById(@PathVariable Long id) {
        invoiceService.deleteById(id);
    }

}