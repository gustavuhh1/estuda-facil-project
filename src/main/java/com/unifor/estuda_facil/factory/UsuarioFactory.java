//package com.unifor.estuda_facil.factory;

//import com.unifor.estuda_facil.exception.EmailAlreadyExists;
//import com.unifor.estuda_facil.models.entity.Usuario;
//import com.unifor.estuda_facil.models.entity.enums.Role;
//import com.unifor.estuda_facil.repository.UsuarioRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class UsuarioFactory {
//
//    private final UsuarioRepository usuarioRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public Usuario criar(String email, String senha, Role role) {
//        if (usuarioRepository.findByEmail(email).isPresent()) {
//            throw new EmailAlreadyExists("E-mail já existente");
//        }
//
//        Usuario usuario = new Usuario();
//        usuario.setEmail(email);
//        usuario.setSenha(passwordEncoder.encode(senha));
//        usuario.setRole(role);
//        return usuarioRepository.save(usuario);
//    }
//}
