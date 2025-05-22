package com.unifor.estuda_facil.controller;

import com.unifor.estuda_facil.models.entity.Anexo;
import com.unifor.estuda_facil.service.AnexoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/anexos")
@RequiredArgsConstructor
public class AnexoController {

    private final AnexoService anexoService;

    @PostMapping("/tarefa/{tarefaId}")
    @PreAuthorize("hasAnyRole('COORDENACAO', 'PROFESSOR')")
    public ResponseEntity<Anexo> uploadAnexoParaTarefa(@PathVariable Long tarefaId, @RequestParam("file") MultipartFile file) throws IOException {
        Anexo anexo = anexoService.salvarAnexoParaTarefa(tarefaId, file);
        return ResponseEntity.status(HttpStatus.CREATED).body(anexo);
    }

    @PostMapping("/aviso/{avisoId}")
    @PreAuthorize("hasAnyRole('COORDENACAO', 'PROFESSOR')")
    public ResponseEntity<Anexo> uploadAnexoParaAviso(@PathVariable Long avisoId, @RequestParam("file") MultipartFile file) throws IOException {
        Anexo anexo = anexoService.salvarAnexoParaAviso(avisoId, file);
        return ResponseEntity.status(HttpStatus.CREATED).body(anexo);
    }

    @GetMapping("/tarefa/{tarefaId}")
    @PreAuthorize("hasAnyRole('COORDENACAO', 'PROFESSOR', 'ALUNO', 'RESPONSAVEL')")
    public ResponseEntity<List<Anexo>> listarAnexosTarefa(@PathVariable Long tarefaId) {
        List<Anexo> anexos = anexoService.listarAnexosPorTarefa(tarefaId);
        return ResponseEntity.ok(anexos);
    }

    @GetMapping("/aviso/{avisoId}")
    @PreAuthorize("hasAnyRole('COORDENACAO', 'PROFESSOR', 'ALUNO', 'RESPONSAVEL')")
    public ResponseEntity<List<Anexo>> listarAnexosAviso(@PathVariable Long avisoId) {
        List<Anexo> anexos = anexoService.listarAnexosPorAviso(avisoId);
        return ResponseEntity.ok(anexos);
    }

    @GetMapping("/download/{anexoId}")
    @PreAuthorize("hasAnyRole('COORDENACAO', 'PROFESSOR', 'ALUNO', 'RESPONSAVEL')")
    public ResponseEntity<byte[]> downloadAnexo(@PathVariable Long anexoId) throws IOException {
        byte[] arquivo = anexoService.downloadArquivo(anexoId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "arquivo");

        return new ResponseEntity<>(arquivo, headers, HttpStatus.OK);
    }
}
