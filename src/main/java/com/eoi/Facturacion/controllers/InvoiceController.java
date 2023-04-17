package com.eoi.Facturacion.controllers;

import com.eoi.Facturacion.entities.Invoice;
import com.eoi.Facturacion.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping(value = {"/",""})
    public String showInvoices(Model model) {
        model.addAttribute("invoices", invoiceService.findAll());
        return "invoice-list";
    }

    @GetMapping("/new")
    public String showNewInvoiceForm(Model model) {
        model.addAttribute("invoice", new Invoice());
        return "invoice-form";
    }

    @PostMapping("/save")
    public String saveInvoice(@ModelAttribute("invoice") Invoice invoice) {
        invoiceService.save(invoice);
        return "redirect:/invoices/";
    }

    @GetMapping("/edit/{id}")
    public String showEditInvoiceForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("invoice", invoiceService.findById(id));
        return "invoice-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteInvoice(@PathVariable("id") Long id) {
        invoiceService.deleteById(id);
        return "redirect:/invoices/";
    }
}