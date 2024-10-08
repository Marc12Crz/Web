package com.example.demo.service.impl;

import com.example.demo.model.Auditoria;
import com.example.demo.repository.AuditoriaRepository;
import com.example.demo.service.AuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditoriaServiceImpl implements AuditoriaService {

    @Autowired
    private AuditoriaRepository auditoriaRepository;

    @Override
    public void registrarAccion(String entidad, Long idEntidad, String accion, String usuario) {
        Auditoria auditoria = new Auditoria(entidad, idEntidad, accion, LocalDateTime.now(), usuario);

        auditoriaRepository.save(auditoria);
    }

    @Override
    public List<Auditoria> listarAuditorias() {
        return auditoriaRepository.findAll();
    }
}
