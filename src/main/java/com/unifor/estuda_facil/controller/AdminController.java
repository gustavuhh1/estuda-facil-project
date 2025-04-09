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
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<?> criarAdmin(@RequestBody @Valid AdminDTO adminDTO) {
        Admin novoAdmin;
        try {
            novoAdmin = adminService.criarAdmin(adminDTO);
        } catch (EmailAlreadyExists e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

        return ResponseEntity.ok(novoAdmin);
    }

    @GetMapping
    public ResponseEntity<List<Admin>> listarAdmins() {
        List<Admin> admins = adminService.listarAdmins();
        return ResponseEntity.ok(admins);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> buscarPorId(@PathVariable UUID id) {
        Admin admin = adminService.buscarPorId(id);
        return ResponseEntity.ok(admin);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Admin> atualizarAdmin(
            @PathVariable UUID id,
            @RequestBody AdminDTO adminAtualizado) {
        Admin admin = adminService.atualizarAdmin(id, adminAtualizado);
        return ResponseEntity.ok(admin);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAdmin(@PathVariable UUID id) {
        Admin admin = adminService.buscarPorId(id);
        if(admin != null) {
            adminService.deletarAdmin(admin.getId());
            UUID user_id = admin.getUsuario().getId();
            usuarioRepository.deleteById(user_id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}