package com.example.padel.demo.controllers;

import com.example.padel.demo.models.Partido;
import com.example.padel.demo.repositories.JugadorRepository;
import com.example.padel.demo.repositories.PartidoRepository;

import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/partido")

public class PartidoController {
    private final PartidoRepository repository;
    private final JugadorRepository jugadorRepository;

    public PartidoController(PartidoRepository repository, JugadorRepository jugadorRepository) {
        this.repository = repository;
        this.jugadorRepository = jugadorRepository;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("partido", repository.findAll());
        return "partido";
    }

    @GetMapping("/crear")
    public String formulario(Model model) {
        model.addAttribute("partido", new Partido());
        model.addAttribute("jugador", jugadorRepository.findAll());  // Aquí pasamos la lista de jugadores al modelo
        return "partido_form";
    }

    @PostMapping
    public String agregar(@ModelAttribute Partido partido) {
        repository.save(partido);
        return "redirect:/partido";
    }

    @GetMapping("/editar/{id}")
    public String editarPartido(@PathVariable Long id, Model model) {
        Optional<Partido> partido = repository.findById(id);
        if (partido.isPresent()) {
            model.addAttribute("partido", partido.get());
            model.addAttribute("jugador", jugadorRepository.findAll());
            return "partido_form";
        }
        return "redirect:/partido";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarPartido(@PathVariable Long id, @ModelAttribute Partido partido) {
        partido.setId(id);
        repository.save(partido);
        return "redirect:/partido";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/partido";
    }
}

