package com.eoi.Facturacion.services;

import com.eoi.Facturacion.entities.Invoice;
import com.eoi.Facturacion.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }
    public Optional<Invoice> findById(Long id) {
        return invoiceRepository.findById(id);
    }
    public Invoice save(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }
    public void deleteById(Long id) {
        invoiceRepository.deleteById(id);
    }
}