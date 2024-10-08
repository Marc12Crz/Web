package com.example.demo.aspect;

import com.example.demo.model.Auditoria;
import com.example.demo.repository.AuditoriaRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class LoggingAspecto {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspecto.class);

    @Autowired
    private AuditoriaRepository auditoriaRepository;

    @After("execution(* com.example.demo.controller.*Controller.guardar*(..)) ||" +
            "execution(* com.example.demo.controller.*Controller.eliminar*(..)) ||" +
            "execution(* com.example.demo.controller.*Controller.editar*(..))")
    public void auditoria(JoinPoint joinPoint) {
        String metodo = joinPoint.getSignature().getName();
        String entidad = joinPoint.getTarget().getClass().getSimpleName().replace("Controller", "");
        Object[] args = joinPoint.getArgs();
        Long id = null;


        if (metodo.startsWith("guardar")) {
            if (args.length > 0) {
                try {
                    id = (Long) args[0].getClass().getMethod("getIdUsuario").invoke(args[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (metodo.startsWith("eliminar") || metodo.startsWith("editar")) {
            if (args.length > 0) {
                id = (Long) args[0];
            }
        }


        if (id != null) {
            String accion = metodo.startsWith("guardar") ? "CREAR" : metodo.startsWith("eliminar") ? "ELIMINAR" : "EDITAR";
            Auditoria auditoria = new Auditoria(entidad, id, accion, LocalDateTime.now(), "admin"); // Reemplaza "admin" por el usuario actual si es necesario
            auditoriaRepository.save(auditoria);
            logger.info("Registrando auditoría: Entidad={}, ID={}, Acción={}, Usuario={}", entidad, id, accion, "admin");
        }
    }
}
