package com.unifor.estuda_facil.service;

import com.unifor.estuda_facil.aspect.Loggable;
import com.unifor.estuda_facil.models.entity.Usuario;
import com.unifor.estuda_facil.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public <T extends Usuario> T prepararUsuario(T usuario) {
        if (usuario.getSenha() != null && !usuario.getSenha().startsWith("$2a$")) {
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        }
        return usuario;
    }

    @Loggable
    public List<Usuario> listarTodas() {
        return usuarioRepository.findAll();
    }

    @Loggable
    public Optional<Usuario> buscarPorId(UUID id) {
        return usuarioRepository.findById(id);
    }

    @Loggable
    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Loggable
    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Loggable
    public Usuario atualizar(UUID id, Usuario usuarioAtualizado) {
        return usuarioRepository.findById(id).map(usuario -> {
            if (usuarioAtualizado.getEmail() != null) {
                usuario.setEmail(usuarioAtualizado.getEmail());
            }
            if (usuarioAtualizado.getSenha() != null && !usuarioAtualizado.getSenha().isBlank()) {
                usuario.setSenha(usuarioAtualizado.getSenha());
            }
            if (usuarioAtualizado.getRole() != null) {
                usuario.setRole(usuarioAtualizado.getRole());
            }
            return usuarioRepository.save(usuario);
        }).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }


    @Loggable
    public void deletar(UUID id) {
        usuarioRepository.deleteById(id);
    }
}
