package com.unifor.estuda_facil.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/token")
public class TokenController {

    @PostMapping("/gerar")
    public ResponseEntity<JsonNode> gerarToken(@RequestBody JsonNode payload) {
        // TODO: implementar geração de token (registro/recuperação)
        JsonNode response = payload; // placeholder
        return ResponseEntity.ok(response);
    }

    @PostMapping("/validar")
    public ResponseEntity<Void> validarToken(@RequestBody JsonNode payload) {
        // TODO: verificar validade e retornar status apropriado
        return ResponseEntity.ok().build();
    }
}