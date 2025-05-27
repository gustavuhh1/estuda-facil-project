package com.unifor.estuda_facil.service;

import com.unifor.estuda_facil.aspect.Loggable;
import com.unifor.estuda_facil.models.dto.AdminDTO;
import com.unifor.estuda_facil.models.entity.Admin;
import com.unifor.estuda_facil.models.entity.Usuario;
import com.unifor.estuda_facil.models.entity.enums.Role;
import com.unifor.estuda_facil.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    @Loggable
    public Admin criarAdmin(AdminDTO dto) {

        // Cria o admin
        Admin admin = new Admin();
        admin.setNome(dto.getNome());
        admin.setDepartamento(dto.getDepartamento());
        admin.setTelefoneContato(dto.getTelefoneContato());
        admin.setEmail(dto.getEmail());
        admin.setSenha(dto.getSenha());
        admin.setRole(Role.COORDENACAO);

        return adminRepository.save(admin);
    }

    @Loggable
    public List<Admin> listarAdmins() {
        return adminRepository.findAll();
    }

    public Admin buscarPorId(UUID id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin não encontrado!"));
    }

    @Loggable
    public Admin atualizarAdmin(UUID id, AdminDTO dto) {
        Admin adminExistente = buscarPorId(id);
        adminExistente.setNome(dto.getNome());
        adminExistente.setDepartamento(dto.getDepartamento());
        adminExistente.setTelefoneContato(dto.getTelefoneContato());
        adminExistente.setEmail(dto.getEmail());
        adminExistente.setSenha(dto.getSenha());
        return adminRepository.save(adminExistente);
    }

    @Loggable
    public void deletarAdmin(UUID id) {
        adminRepository.deleteById(id);
    }
}
