/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinica.veterinaria.controllers;

import com.clinica.veterinaria.domain.TipoServicio;
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
@RequestMapping("/tipos")
public class TipoServicioController {

    private final TipoServicioService tipoServicioService;

    public TipoServicioController(TipoServicioService tipoServicioService) {
        this.tipoServicioService = tipoServicioService;
    }

    @GetMapping
    public String mostrarListado(Model model) {
        model.addAttribute("listaTipos", tipoServicioService.obtenerTodos());
        return "tipos/listado";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("tipoServicio", new TipoServicio());
        return "tipos/formulario";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Integer id, Model model,
                                           RedirectAttributes redirectAttributes) {
        return tipoServicioService.obtenerPorId(id).map(tipo -> {
            model.addAttribute("tipoServicio", tipo);
            return "tipos/formulario";
        }).orElseGet(() -> {
            redirectAttributes.addFlashAttribute("mensaje", "El tipo de servicio no existe");
            redirectAttributes.addFlashAttribute("tipoMensaje", "danger");
            return "redirect:/tipos";
        });
    }

    @PostMapping("/guardar")
    public String guardarTipo(@ModelAttribute("tipoServicio") TipoServicio tipoServicio,
                              RedirectAttributes redirectAttributes) {
        boolean esNuevo = (tipoServicio.getId() == null);
        tipoServicioService.registrar(tipoServicio);
        redirectAttributes.addFlashAttribute("mensaje", esNuevo
                ? "Tipo de servicio agregado correctamente"
                : "Tipo de servicio modificado correctamente");
        redirectAttributes.addFlashAttribute("tipoMensaje", "success");
        return "redirect:/tipos";
    }

    @PostMapping("/eliminar")
    public String eliminarTipo(@RequestParam Integer id, RedirectAttributes redirectAttributes) {
        try {
            tipoServicioService.borrar(id);
            redirectAttributes.addFlashAttribute("mensaje", "Tipo de servicio eliminado correctamente");
            redirectAttributes.addFlashAttribute("tipoMensaje", "success");
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("mensaje",
                    "No se puede eliminar: el tipo tiene servicios asociados");
            redirectAttributes.addFlashAttribute("tipoMensaje", "danger");
        }
        return "redirect:/tipos";
    }
}