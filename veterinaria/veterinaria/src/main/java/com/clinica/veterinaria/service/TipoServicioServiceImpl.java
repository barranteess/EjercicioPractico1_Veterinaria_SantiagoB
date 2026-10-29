/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinica.veterinaria.service;

import com.clinica.veterinaria.domain.TipoServicio;
import com.clinica.veterinaria.repository.TipoServicioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TipoServicioServiceImpl implements TipoServicioService {

    private final TipoServicioRepository tipoServicioRepository;

    public TipoServicioServiceImpl(TipoServicioRepository tipoServicioRepository) {
        this.tipoServicioRepository = tipoServicioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TipoServicio> obtenerTodos() {
        return tipoServicioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TipoServicio> obtenerPorId(Integer id) {
        return tipoServicioRepository.findById(id);
    }

    @Override
    @Transactional
    public void registrar(TipoServicio tipoServicio) {
        tipoServicioRepository.save(tipoServicio);
    }

    @Override
    @Transactional
    public void borrar(Integer id) {
        tipoServicioRepository.deleteById(id);
    }
}