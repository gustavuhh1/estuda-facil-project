package com.unifor.estuda_facil.service;

import com.unifor.estuda_facil.models.dto.AdminDTO;
import com.unifor.estuda_facil.models.entity.Admin;
import com.unifor.estuda_facil.models.entity.Usuario;
import com.unifor.estuda_facil.models.entity.enums.Role;
import com.unifor.estuda_facil.repository.AdminRepository;
import com.unifor.estuda_facil.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Admin criarAdmin(AdminDTO adminDTO) {

        // validar se e-mail já existe

        Admin admin = new Admin();
        admin.setNome(adminDTO.getNome());
        admin.setDepartamento(admin.getDepartamento());
        admin.setTelefoneContato(adminDTO.getTelefoneContato());


        Usuario usuario = new Usuario();
        usuario.setEmail(adminDTO.getEmail());
        usuario.setSenha(passwordEncoder.encode(adminDTO.getSenha()));
        usuario.setRole(Role.COORDENACAO);
        usuario = usuarioRepository.save(usuario);

        admin.setUsuario(usuario);

        return adminRepository.save(admin);
    }

    public List<Admin> listarAdmins() {
        return adminRepository.findAll();
    }

    public Admin buscarPorId(UUID id) {
        return adminRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Admin não encontrado!"));
    }

    public Admin atualizarAdmin(UUID id, AdminDTO adminAtualizado) {
        Admin adminExistente = buscarPorId(id);
        adminExistente.setNome(adminAtualizado.getNome());
        adminExistente.setDepartamento(adminAtualizado.getDepartamento());
        adminExistente.setTelefoneContato(adminAtualizado.getTelefoneContato());

        return adminRepository.save(adminExistente);
    }

    // Deletar
    public void deletarAdmin(UUID id) {
        adminRepository.deleteById(id);
    }
}