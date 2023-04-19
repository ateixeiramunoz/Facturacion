package com.eoi.Facturacion.controllers;

import com.eoi.Facturacion.entities.Subscription;
import com.eoi.Facturacion.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService; // Inyección de dependencia del servicio Subscription

    @GetMapping(value = {"/",""})
    public String getAllSubscriptions(Model model) {
        List<Subscription> subscriptions = subscriptionService.findAll(); // Llamada al servicio para obtener todas las suscripciones
        model.addAttribute("subscriptions", subscriptions); // Agregar la lista de suscripciones al modelo
        return "subscription/subscription-list"; // Devolver la vista que muestra la lista de suscripciones
    }

    @GetMapping("/{id}")
    public String getSubscriptionById(@PathVariable Long id, Model model) {
        Optional<Subscription> subscription = subscriptionService.findById(id); // Llamada al servicio para obtener la suscripción con el id especificado
        if(subscription.isPresent())
        {
            model.addAttribute("subscription", subscription.get()); // Agregar la suscripción al modelo
            return "subscription/subscription-form"; // Devolver la vista para editar la suscripción
        }
        return "error"; // Si no se encuentra la suscripción, devolver la vista de error

    }

    @GetMapping("/new")
    public String newSubscription(Model model) {
        Subscription subscription = new Subscription(); // Crear una nueva suscripción vacía
        model.addAttribute("subscription", subscription); // Agregar la suscripción al modelo
        return "subscription/subscription-form"; // Devolver la vista para crear una nueva suscripción
    }

    @PostMapping("/save")
    public String saveSubscription(Subscription subscription) {
        subscriptionService.save(subscription); // Guardar la suscripción en la base de datos a través del servicio
        return "redirect:/subscriptions/"; // Redirigir al usuario a la lista de suscripciones
    }

    @GetMapping("/edit/{id}")
    public String editSubscription(@PathVariable Long id, Model model) {
        Optional<Subscription> subscription = subscriptionService.findById(id); // Llamada al servicio para obtener la suscripción con el id especificado
        if(subscription.isPresent())
        {
            model.addAttribute("subscription", subscription.get()); // Agregar la suscripción al modelo
            return "subscription/subscription-form"; // Devolver la vista para editar la suscripción
        }
        return "error"; // Si no se encuentra la suscripción, devolver la vista de error

    }

    @PostMapping("/update/{id}")
    public String updateSubscription(@PathVariable Long id, Subscription subscription) {
        subscription.setId(id); // Establecer el id de la suscripción
        subscriptionService.save(subscription); // Guardar la suscripción en la base de datos a través del servicio
        return "redirect:/subscriptions/"; // Redirigir al usuario a la lista de suscripciones
    }

    @GetMapping("/delete/{id}")
    public String deleteSubscription(@PathVariable Long id) {
        subscriptionService.deleteById(id); // Eliminar la suscripción con el id especificado a través del servicio
        return "redirect:/subscriptions/"; // Redirigir al usuario a la lista de suscripciones
    }

}
