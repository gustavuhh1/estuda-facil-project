package com.unifor.estuda_facil.controller;

import com.unifor.estuda_facil.models.dto.*;
import com.unifor.estuda_facil.models.entity.Admin;
import com.unifor.estuda_facil.models.entity.Aluno;
import com.unifor.estuda_facil.models.entity.Professor;
import com.unifor.estuda_facil.models.entity.Usuario;
import com.unifor.estuda_facil.service.CustomUserDetailsService;
import com.unifor.estuda_facil.service.JwtService;
import com.unifor.estuda_facil.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;
    private final UsuarioService usuarioService;

    public AuthController(AuthenticationManager authManager, JwtService jwtService, CustomUserDetailsService userDetailsService, UsuarioService usuarioService) {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO request) {
        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
            String token = jwtService.generateToken(userDetails);
            Usuario user = usuarioService.buscarPorEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
            System.out.println(user.getRole());
            UsuarioResponseDTO userDTO;

            if (user instanceof Aluno aluno) {
                AlunoDTO dto = new AlunoDTO();
                dto.setId(aluno.getId());
                dto.setEmail(aluno.getEmail());
                dto.setRole(String.valueOf(aluno.getRole()));
                dto.setNome(aluno.getNome());
                dto.setMatricula(aluno.getMatricula());
                dto.setTurmaId(aluno.getTurma() != null ? aluno.getTurma().getId() : null);
                userDTO = dto;

            } else if (user instanceof Professor prof) {
                ProfessorDTO dto = new ProfessorDTO();
                dto.setId(prof.getId());
                dto.setEmail(prof.getEmail());
                dto.setRole(String.valueOf(prof.getRole()));
                dto.setNome(prof.getNome());
                dto.setDisciplina(prof.getDisciplina());
                dto.setTelefoneContato(prof.getTelefone());
                userDTO = dto;

            } else if (user instanceof Admin admin) {
                AdminDTO dto = new AdminDTO();
                dto.setId(admin.getId());
                dto.setEmail(admin.getEmail());
                dto.setRole(String.valueOf(admin.getRole()));
                dto.setNome(admin.getNome());
                dto.setDepartamento(admin.getDepartamento());
                dto.setTelefoneContato(admin.getTelefoneContato());
                userDTO = dto;

            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Tipo de usuário desconhecido");
            }

            return ResponseEntity.ok(new AuthResponseDTO(token, userDTO));
        } catch (BadCredentialsException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid email or password"));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "User not found"));
        }
    }
}

