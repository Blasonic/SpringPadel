package com.example.padel.demo.controllers;

import com.example.padel.demo.models.Jugador;
import com.example.padel.demo.repositories.JugadorRepository;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/jugador")
public class JugadorController {
    private final JugadorRepository repository;
    public JugadorController(JugadorRepository repository) { this.repository = repository; }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("jugador", repository.findAll());
        return "jugador";
    }

    @GetMapping("/crear")
    public String formulario(Model model) {
        model.addAttribute("jugador", new Jugador());
        return "jugador_form";
    }

    @PostMapping
    public String agregar(@ModelAttribute Jugador jugador) {
        repository.save(jugador);
        return "redirect:/jugador";
    }


    @GetMapping("/editar/{id}")
    public String editarJugador(@PathVariable Long id, Model model) {
        Optional<Jugador> jugador = repository.findById(id);
        if (jugador.isPresent()) {
            model.addAttribute("jugador", jugador.get());
            return "jugador_form";
        }
        return "redirect:/jugadores";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarJugador(@PathVariable Long id, @ModelAttribute Jugador jugador) {
        jugador.setId(id);
        repository.save(jugador);
        return "redirect:/jugador";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/jugador";
    }
}
