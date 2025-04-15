package com.unifor.estuda_facil.controller;

import com.unifor.estuda_facil.models.entity.Aviso;
import com.unifor.estuda_facil.service.AvisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/avisos")
public class AvisoController {

    @Autowired
    private AvisoService avisoService;

    @GetMapping
    public List<Aviso> listarTodos() {
        return avisoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Aviso> buscarPorId(@PathVariable Long id) {
        return avisoService.buscarPorId(id);
    }

    @PostMapping
    public Aviso salvar(@RequestBody Aviso aviso) {
        return avisoService.salvar(aviso);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        avisoService.deletar(id);
    }
}
