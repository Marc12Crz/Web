package com.example.demo.service.impl;

import com.example.demo.model.FormularioAdopcion;
import com.example.demo.repository.FormularioAdopcionRepository;
import com.example.demo.service.FormularioAdopcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormularioAdopcionServiceImpl implements FormularioAdopcionService {

    @Autowired
    private FormularioAdopcionRepository formularioAdopcionRepository;

    @Override
    public List<FormularioAdopcion> listarFormularios() {
        return formularioAdopcionRepository.findAll();
    }

    @Override
    public FormularioAdopcion guardarFormulario(FormularioAdopcion formulario) {
        return formularioAdopcionRepository.save(formulario);
    }

    @Override
    public FormularioAdopcion obtenerFormularioPorId(Long id) {
        return formularioAdopcionRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarFormulario(Long id) {
        formularioAdopcionRepository.deleteById(id);
    }
}
