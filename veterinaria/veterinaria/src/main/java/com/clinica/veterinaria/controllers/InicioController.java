/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinica.veterinaria.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class InicioController {

    @GetMapping("/")
    public String mostrarInicio() {
        return "inicio";
    }

    @PostMapping("/consultas/enviar")
    public String recibirConsulta(@RequestParam String nombreContacto,
                                  @RequestParam String correoContacto,
                                  @RequestParam String nombreMascota,
                                  @RequestParam String mensajeContacto,
                                  RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("mensajeConsulta",
                "¡Gracias, " + nombreContacto + "! Recibimos tu consulta sobre "
                + nombreMascota + " y te responderemos pronto.");
        return "redirect:/#consultas";
    }
}