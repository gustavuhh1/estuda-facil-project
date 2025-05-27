package com.unifor.estuda_facil.config;

import com.unifor.estuda_facil.models.entity.Admin;
import com.unifor.estuda_facil.models.entity.enums.Role;
import com.unifor.estuda_facil.repository.AdminRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class TestDataLoader implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final AdminRepository adminRepository;

    public TestDataLoader(PasswordEncoder passwordEncoder, AdminRepository adminRepository) {
        this.passwordEncoder = passwordEncoder;
        this.adminRepository = adminRepository;
    }

    @Override
    public void run(String... args) {
        addUserIfNotExists("coordenador@teste.com", Role.COORDENACAO);
        addUserIfNotExists("professor@teste.com", Role.PROFESSOR);
        addUserIfNotExists("aluno@teste.com", Role.ALUNO);
        addUserIfNotExists("responsavel@teste.com", Role.RESPONSAVEL);
    }

    private void addUserIfNotExists(String email, Role role) {
        if (adminRepository.findByEmail(email).isEmpty()) {
            Admin user = new Admin();
            user.setEmail(email);
            user.setSenha(passwordEncoder.encode("123456"));
            user.setRole(role);
            user.setNome("Gustavo Martins");
            adminRepository.save(user);
        }
    }
}
