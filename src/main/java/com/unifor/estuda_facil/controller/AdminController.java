package com.unifor.estuda_facil.controller;

import com.unifor.estuda_facil.exception.EmailAlreadyExists;
import com.unifor.estuda_facil.models.dto.AdminDTO;
import com.unifor.estuda_facil.models.entity.Admin;
import com.unifor.estuda_facil.repository.UsuarioRepository;
import com.unifor.estuda_facil.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping
    public ResponseEntity<Admin> criarAdmin(@RequestBody @Valid AdminDTO dto) {
        try {
            Admin adm = adminService.criarAdmin(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(adm);
        } catch (EmailAlreadyExists e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Admin>> listarAdmins() {
        return ResponseEntity.ok(adminService.listarAdmins());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> buscarAdmin(@PathVariable UUID id) {
        Admin adm = adminService.buscarPorId(id);
        if (adm == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(adm);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Admin> atualizarAdmin(
            @PathVariable UUID id,
            @RequestBody @Valid AdminDTO dto
    ) {
        Admin updated = adminService.atualizarAdmin(id, dto);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAdmin(@PathVariable UUID id) {
        Admin adm = adminService.buscarPorId(id);
        if (adm == null) {
            return ResponseEntity.notFound().build();
        }
        adminService.deletarAdmin(id);
        return ResponseEntity.noContent().build();
    }
}
