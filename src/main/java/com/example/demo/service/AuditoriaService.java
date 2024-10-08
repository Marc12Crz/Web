package com.example.demo.service;

import com.example.demo.model.Auditoria;
import java.util.List;

public interface AuditoriaService {
    void registrarAccion(String entidad, Long idEntidad, String accion, String usuario);
    List<Auditoria> listarAuditorias();
}
