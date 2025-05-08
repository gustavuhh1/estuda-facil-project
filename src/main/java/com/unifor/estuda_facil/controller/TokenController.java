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
public class TokenController {

    private final TokenService service;

    public TokenController(TokenService service) {
        this.service = service;
    }

    @PostMapping("/gerar")
    public ResponseEntity<TokenResponseDTO> gerarToken(
            @RequestBody @Valid TokenRequestDTO req
    ) {
        TokenResponseDTO resp = service.generate(req);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/validar")
    public ResponseEntity<Void> validarToken(
            @RequestBody @Valid TokenValidationDTO req
    ) {
        boolean ok = service.validate(req);
        if (ok) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(401).build();
    }
}
