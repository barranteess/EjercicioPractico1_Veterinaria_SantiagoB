/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.clinica.veterinaria.service;

import com.clinica.veterinaria.domain.Servicio;
import java.util.List;
import java.util.Optional;

public interface ServicioService {

    List<Servicio> obtenerTodos();

    Optional<Servicio> obtenerPorId(Integer id);

    void registrar(Servicio servicio);

    void borrar(Integer id);
}