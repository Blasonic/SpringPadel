package com.example.padel.demo.repositories;


import com.example.padel.demo.models.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JugadorRepository extends JpaRepository<Jugador, Long> {}