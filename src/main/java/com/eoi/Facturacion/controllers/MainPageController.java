package com.eoi.Facturacion.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
class MainPageController {

    @GetMapping(value = {"/",""})
    public String showHome() {
        return "home";
    }

}
