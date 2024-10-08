package com.example.demo.service;

import com.example.demo.model.Adopcion;

import java.util.List;

public interface AdopcionService {
    List<Adopcion> listarAdopciones();
    Adopcion guardarAdopcion(Adopcion adopcion);
    Adopcion obtenerAdopcionPorId(Long id);
    void eliminarAdopcion(Long id);
}
