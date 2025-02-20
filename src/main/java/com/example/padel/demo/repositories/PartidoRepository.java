package com.example.padel.demo.repositories;


import com.example.padel.demo.models.Partido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartidoRepository extends JpaRepository<Partido, Long> {}
