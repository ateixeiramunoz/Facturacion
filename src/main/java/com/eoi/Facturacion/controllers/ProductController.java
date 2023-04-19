package com.eoi.Facturacion.controllers;

import com.eoi.Facturacion.entities.Contract;
import com.eoi.Facturacion.entities.Product;
import com.eoi.Facturacion.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = {"/",""})
    public String showProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        return "product/product-list";
    }


    @GetMapping("/{id}")
    public String getContractById(@PathVariable Long id, Model model) {
        Optional<Product> product = productService.findById(id);
        if(product.isPresent())
        {
            model.addAttribute("product", product.get());
            return "product";
        }
        return "error";

    }


    @GetMapping("/new")
    public String showNewProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "product/product-form";
    }

    @PostMapping("/save")
    public String saveProduct(Product product) {
        productService.save(product);
        return "redirect:/products/";
    }

    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable("id") Long id, Model model) {
        Optional<Product> product = productService.findById(id);
        if(product.isPresent()) {
            model.addAttribute("product", product.get());
            return "product/product-form";
        }
        return "error";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteById(id);
        return "redirect:/products/";
    }


}