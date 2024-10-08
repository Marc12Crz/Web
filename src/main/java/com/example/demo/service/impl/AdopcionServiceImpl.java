package com.example.demo.service.impl;

import com.example.demo.model.Adopcion;
import com.example.demo.repository.AdopcionRepository;
import com.example.demo.service.AdopcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdopcionServiceImpl implements AdopcionService {
    @Autowired
    private AdopcionRepository adopcionRepository;

    @Override
    public List<Adopcion> listarAdopciones() {
        return adopcionRepository.findAll();
    }

    @Override
    public Adopcion guardarAdopcion(Adopcion adopcion) {
        return adopcionRepository.save(adopcion);
    }

    @Override
    public Adopcion obtenerAdopcionPorId(Long id) {
        return adopcionRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarAdopcion(Long id) {
        adopcionRepository.deleteById(id);
    }
}
