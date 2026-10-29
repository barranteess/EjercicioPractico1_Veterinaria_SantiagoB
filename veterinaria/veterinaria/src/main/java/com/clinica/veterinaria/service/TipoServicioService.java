/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.clinica.veterinaria.service;

import com.clinica.veterinaria.domain.TipoServicio;
import java.util.List;
import java.util.Optional;

public interface TipoServicioService {

    List<TipoServicio> obtenerTodos();

    Optional<TipoServicio> obtenerPorId(Integer id);

    void registrar(TipoServicio tipoServicio);

    void borrar(Integer id);
}