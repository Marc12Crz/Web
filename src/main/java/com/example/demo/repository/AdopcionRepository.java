package com.example.demo.repository;

import com.example.demo.model.Adopcion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdopcionRepository extends JpaRepository<Adopcion, Long> {
}
