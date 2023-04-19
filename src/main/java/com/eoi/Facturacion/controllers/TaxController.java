package com.eoi.Facturacion.controllers;

import com.eoi.Facturacion.entities.Tax;
import com.eoi.Facturacion.services.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/taxes")
public class TaxController {

    @Autowired
    private TaxService taxService;

    @GetMapping(value = {"/",""})
    public String showTaxes(Model model) {
        model.addAttribute("taxes", taxService.findAll());
        return "tax/tax-list";
    }

    @GetMapping("/{id}")
    public String getTaxById(@PathVariable Long id, Model model) {
        Optional<Tax> tax = taxService.findById(id);
        if(tax.isPresent())
        {
            model.addAttribute("tax", tax.get());
            return "tax/tax-details";
        }
        return "error";
    }

    @GetMapping("/new")
    public String showNewTaxForm(Model model) {
        model.addAttribute("tax", new Tax());
        return "tax/tax-form";
    }

    @PostMapping("/save")
    public String saveTax(Tax tax) {
        taxService.save(tax);
        return "redirect:/tax/";
    }

    @GetMapping("/edit/{id}")
    public String showEditTaxForm(@PathVariable("id") Long id, Model model) {
        Optional<Tax> tax = taxService.findById(id);
        if(tax.isPresent()) {
            model.addAttribute("tax", tax.get());
            return "tax/tax-form";
        }
        return "error";
    }

    @GetMapping("/delete/{id}")
    public String deleteTax(@PathVariable("id") Long id) {
        taxService.deleteById(id);
        return "redirect:/tax/";
    }

}
