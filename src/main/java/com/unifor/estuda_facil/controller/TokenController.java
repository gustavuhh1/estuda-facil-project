package com.unifor.estuda_facil.controller;

import com.unifor.estuda_facil.models.dto.TokenRequestDTO;
import com.unifor.estuda_facil.models.dto.TokenResponseDTO;
import com.unifor.estuda_facil.models.dto.TokenValidationDTO;
import com.unifor.estuda_facil.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
class TokenController {
    private final TokenService service;
    public TokenController(TokenService service) {
        this.service = service;
    }

    @PostMapping("/gerar")
    public ResponseEntity<TokenResponseDTO> gerarToken(@RequestBody @Valid TokenRequestDTO req) {
        return ResponseEntity.ok(service.generate(req));
    }

    @PostMapping("/validar")
    public ResponseEntity<Void> validarToken(@RequestBody @Valid TokenValidationDTO req) {
        return service.validate(req) ? ResponseEntity.ok().build() : ResponseEntity.status(401).build();
    }
}