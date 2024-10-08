package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FormularioAdopcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFormulario;
    private Long idAlbergue;
    private String pregunta;

    // Getters y Setters
    public Long getIdFormulario() {
        return idFormulario;
    }

    public void setIdFormulario(Long idFormulario) {
        this.idFormulario = idFormulario;
    }

    public Long getIdAlbergue() {
        return idAlbergue;
    }

    public void setIdAlbergue(Long idAlbergue) {
        this.idAlbergue = idAlbergue;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }
}
