package com.eoi.Facturacion.controllers;

import com.eoi.Facturacion.entities.Contract;
import com.eoi.Facturacion.entities.Customer;
import com.eoi.Facturacion.entities.Invoice;
import com.eoi.Facturacion.services.ContractService;
import com.eoi.Facturacion.services.CustomerService;
import com.eoi.Facturacion.services.InvoiceService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/customers")
@Log4j2
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private ContractService contractService;


    @GetMapping(value = {"/",""})
    public String showCustomers(Model model) {
        model.addAttribute("dataObject", customerService.findAll());
        model.addAttribute("fragmentName", "fragment-customer-list");

        return "index";
    }

    @GetMapping("/new")
    public String showNewCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("fragmentName", "fragment-customer-form");
        return "index";
    }

    @PostMapping("/save")
    public String saveCustomer(Customer customer) {
        customerService.save(customer);
        return "redirect:/customers/";
    }

    @GetMapping("/edit/{id}")
    public String showEditCustomerForm(@PathVariable("id") Long id, Model model) {

        Optional<Customer> customer = customerService.findById(id);
        if(customer.isPresent()){
            model.addAttribute("customer", customer.get());
        }
        else{
            // Si el cliente no existe, redirigir a una página de error o mostrar un mensaje de error
            return "error-page";
        }

        return "customer/customer-form";

    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteById(id);
        return "redirect:/customers/";
    }


    @GetMapping("/{id}/invoices/new")
    public String newInvoiceForm(@PathVariable("id") Long id, Model model) {
        Optional<Customer> customer = customerService.findById(id);
        if(customer.isPresent()){
            Invoice invoice = new Invoice();
            invoice.setCustomer(customer.get());
            model.addAttribute("invoice", invoice);
            return "customer/customer-invoice-form";
        }else {
            return "error";
        }
    }

    @PostMapping("/{id}/invoices/new")
    public String createInvoice(@PathVariable("id") Long id, Invoice invoice) {
        Optional<Customer> customer = customerService.findById(id);
        if(customer.isPresent()){
            invoice.setCustomer(customer.get());
            invoiceService.save(invoice);
            return "redirect:/customers/edit/" + id;
        }
        else
        {
            return "error";
        }

    }


    @GetMapping("/{id}/contracts/new")
    public String newContractForm(@PathVariable("id") Long id, Model model) {
        Optional<Customer> customer = customerService.findById(id);
        if(customer.isPresent()){
            Contract contract = new Contract();
            contract.setCustomer(customer.get());
            model.addAttribute("contract", contract);
            return "customer/customer-contract-form";
        }else {
            return "error";
        }
    }


    @PostMapping("/{id}/contracts/new")
    public String createContract(@PathVariable("id") Long id, Contract contract) {
        Optional<Customer> customer = customerService.findById(id);
        if(customer.isPresent()){
            contract.setCustomer(customer.get());
            contractService.save(contract);
            return "redirect:/customers/edit/" + id;
        }
        else
        {
            return "error";
        }
    }







}