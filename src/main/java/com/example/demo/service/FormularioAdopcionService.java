package com.example.demo.service;

import com.example.demo.model.FormularioAdopcion;

import java.util.List;

public interface FormularioAdopcionService {
    List<FormularioAdopcion> listarFormularios();
    FormularioAdopcion guardarFormulario(FormularioAdopcion formulario);
    FormularioAdopcion obtenerFormularioPorId(Long id);
    void eliminarFormulario(Long id);
}
