package com.eoi.Facturacion.controllers;

import com.eoi.Facturacion.entities.Invoice;
import com.eoi.Facturacion.entities.Tax;
import com.eoi.Facturacion.repositories.TaxRepository;
import com.eoi.Facturacion.services.InvoiceService;
import com.eoi.Facturacion.services.ProductService;
import com.eoi.Facturacion.services.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private TaxService taxService;

    @GetMapping(value = {"/",""})
    public String showInvoices(Model model) {
        model.addAttribute("dataObject", invoiceService.findAll());
        model.addAttribute("fragmentName", "fragment-invoice-list");
        return "index";
    }

    @GetMapping("/new")
    public String showNewInvoiceForm(Model model) {
        model.addAttribute("invoice", new Invoice());
        return "invoice/invoice-form";
    }

    @PostMapping("/save")
    public String saveInvoice(Invoice invoice) {
        invoiceService.save(invoice);
        return "redirect:/invoices/";
    }

    @GetMapping("/edit/{id}")
    public String showEditInvoiceForm(@PathVariable("id") Long id, Model model) {
        Optional<Invoice> invoice = invoiceService.findById(id);
        if(invoice.isPresent())
        {
           // invoice.get().setSelectedInvoiceTaxes(invoice.get().getInvoiceTaxes());
            List<Tax> taxes = taxService.findAll();
            model.addAttribute("allTaxes", taxes);
            model.addAttribute("invoice", invoice.get());
            return "invoice/customer-invoice-form";
        }
        return "error";
    }

    @GetMapping("/delete/{id}")
    public String deleteInvoice(@PathVariable("id") Long id) {
        invoiceService.deleteById(id);
        return "redirect:/invoices/";
    }
}