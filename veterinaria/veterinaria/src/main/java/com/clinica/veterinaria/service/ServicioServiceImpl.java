/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinica.veterinaria.service;

import com.clinica.veterinaria.domain.Servicio;
import com.clinica.veterinaria.repository.ServicioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioServiceImpl implements ServicioService {

    private final ServicioRepository servicioRepository;

    public ServicioServiceImpl(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Servicio> obtenerTodos() {
        return servicioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Servicio> obtenerPorId(Integer id) {
        return servicioRepository.findById(id);
    }

    @Override
    @Transactional
    public void registrar(Servicio servicio) {
        servicioRepository.save(servicio);
    }

    @Override
    @Transactional
    public void borrar(Integer id) {
        servicioRepository.deleteById(id);
    }
}