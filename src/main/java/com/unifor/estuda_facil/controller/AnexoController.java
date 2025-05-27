package com.unifor.estuda_facil.controller;

import com.unifor.estuda_facil.models.entity.Anexo;
import com.unifor.estuda_facil.service.AnexoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/anexos")
@RequiredArgsConstructor
class AnexoController {
    private final AnexoService service;

    @PostMapping("/tarefa/{tarefaId}")
    public ResponseEntity<Anexo> uploadTarefa(@PathVariable UUID tarefaId, @RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvarAnexoParaTarefa(tarefaId, file));
    }

    @PostMapping("/aviso/{avisoId}")
    public ResponseEntity<Anexo> uploadAviso(@PathVariable UUID avisoId, @RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvarAnexoParaAviso(avisoId, file));
    }

    @GetMapping("/tarefa/{tarefaId}")
    public ResponseEntity<List<Anexo>> listarTarefa(@PathVariable UUID tarefaId) {
        return ResponseEntity.ok(service.listarAnexosPorTarefa(tarefaId));
    }

    @GetMapping("/aviso/{avisoId}")
    public ResponseEntity<List<Anexo>> listarAviso(@PathVariable UUID avisoId) {
        return ResponseEntity.ok(service.listarAnexosPorAviso(avisoId));
    }

    @GetMapping("/download/{anexoId}")
    public ResponseEntity<byte[]> download(@PathVariable UUID anexoId) throws IOException {
        byte[] data = service.downloadArquivo(anexoId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "arquivo");
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }
}
