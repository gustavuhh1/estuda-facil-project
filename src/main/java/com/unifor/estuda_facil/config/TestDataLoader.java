package com.unifor.estuda_facil.config;

import com.unifor.estuda_facil.models.entity.Usuario;
import com.unifor.estuda_facil.models.entity.enums.Role;
import com.unifor.estuda_facil.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class TestDataLoader implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public TestDataLoader(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        addUserIfNotExists("coordenador@teste.com", "1234", Role.COORDENACAO);
        addUserIfNotExists("professor@teste.com", "1234", Role.PROFESSOR);
        addUserIfNotExists("aluno@teste.com", "1234", Role.ALUNO);
        addUserIfNotExists("responsavel@teste.com", "1234", Role.RESPONSAVEL);
    }

    private void addUserIfNotExists(String email, String rawPassword, Role role) {
        if (usuarioRepository.findByEmail(email).isEmpty()) {
            Usuario user = new Usuario();
            user.setEmail(email);
            user.setSenha(passwordEncoder.encode(rawPassword));
            user.setRole(role);
            usuarioRepository.save(user);
        }
    }
}
