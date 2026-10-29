/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinica.veterinaria.controllers;

import com.clinica.veterinaria.domain.Servicio;
import com.clinica.veterinaria.domain.TipoServicio;
import com.clinica.veterinaria.service.ServicioService;
import com.clinica.veterinaria.service.TipoServicioService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/servicios")
public class ServicioController {

    private final ServicioService servicioService;
    private final TipoServicioService tipoServicioService;

    public ServicioController(ServicioService servicioService, TipoServicioService tipoServicioService) {
        this.servicioService = servicioService;
        this.tipoServicioService = tipoServicioService;
    }

    @GetMapping
    public String mostrarListado(Model model) {
        model.addAttribute("listaServicios", servicioService.obtenerTodos());
        return "servicios/listado";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        Servicio servicioNuevo = new Servicio();
        servicioNuevo.setTipoServicio(new TipoServicio());
        model.addAttribute("servicio", servicioNuevo);
        model.addAttribute("listaTipos", tipoServicioService.obtenerTodos());
        return "servicios/formulario";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Integer id, Model model,
                                           RedirectAttributes redirectAttributes) {
        return servicioService.obtenerPorId(id).map(servicio -> {
            model.addAttribute("servicio", servicio);
            model.addAttribute("listaTipos", tipoServicioService.obtenerTodos());
            return "servicios/formulario";
        }).orElseGet(() -> {
            redirectAttributes.addFlashAttribute("mensaje", "El servicio no existe");
            redirectAttributes.addFlashAttribute("tipoMensaje", "danger");
            return "redirect:/servicios";
        });
    }

    @PostMapping("/guardar")
    public String guardarServicio(@ModelAttribute("servicio") Servicio servicio,
                                  RedirectAttributes redirectAttributes) {
        boolean esNuevo = (servicio.getId() == null);
        servicioService.registrar(servicio);
        redirectAttributes.addFlashAttribute("mensaje", esNuevo
                ? "Servicio agregado correctamente"
                : "Servicio modificado correctamente");
        redirectAttributes.addFlashAttribute("tipoMensaje", "success");
        return "redirect:/servicios";
    }

    @PostMapping("/eliminar")
    public String eliminarServicio(@RequestParam Integer id, RedirectAttributes redirectAttributes) {
        try {
            servicioService.borrar(id);
            redirectAttributes.addFlashAttribute("mensaje", "Servicio eliminado correctamente");
            redirectAttributes.addFlashAttribute("tipoMensaje", "success");
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("mensaje",
                    "No se puede eliminar: el servicio tiene citas programadas");
            redirectAttributes.addFlashAttribute("tipoMensaje", "danger");
        }
        return "redirect:/servicios";
    }
}