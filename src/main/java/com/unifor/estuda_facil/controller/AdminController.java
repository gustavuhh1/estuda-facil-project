package com.unifor.estuda_facil.controller;

import com.unifor.estuda_facil.exception.EmailAlreadyExists;
import com.unifor.estuda_facil.models.dto.AdminDTO;
import com.unifor.estuda_facil.models.dto.AvisoDTO;
import com.unifor.estuda_facil.models.dto.AlunoDTO;
import com.unifor.estuda_facil.models.dto.ProfessorDTO;
import com.unifor.estuda_facil.models.dto.TurmaDTO;
import com.unifor.estuda_facil.models.dto.TarefaDTO;
import com.unifor.estuda_facil.models.entity.Admin;
import com.unifor.estuda_facil.models.entity.Aviso;
import com.unifor.estuda_facil.models.entity.Aluno;
import com.unifor.estuda_facil.models.entity.Professor;
import com.unifor.estuda_facil.models.entity.Turma;
import com.unifor.estuda_facil.models.entity.Tarefa;
import com.unifor.estuda_facil.repository.UsuarioRepository;
import com.unifor.estuda_facil.service.AdminService;
import com.unifor.estuda_facil.service.AvisoService;
import com.unifor.estuda_facil.service.AlunoService;
import com.unifor.estuda_facil.service.ProfessorService;
import com.unifor.estuda_facil.service.TurmaService;
import com.unifor.estuda_facil.service.TarefaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TarefaService tarefaService;
    @Autowired
    private ProfessorService professorService;
    @Autowired
    private AlunoService alunoService;
    @Autowired
    private TurmaService turmaService;
    @Autowired
    private AvisoService avisoService;

    @PostMapping
    public ResponseEntity<Admin> criarAdmin(@RequestBody @Valid AdminDTO dto) {
        try {
            Admin adm = adminService.criarAdmin(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(adm);
        } catch (EmailAlreadyExists e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Admin>> listarAdmins() {
        return ResponseEntity.ok(adminService.listarAdmins());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> buscarAdmin(@PathVariable UUID id) {
        Admin adm = adminService.buscarPorId(id);
        if (adm != null) {
            return ResponseEntity.ok(adm);
        }
        return ResponseEntity.notFound().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Admin> atualizarAdmin(@PathVariable UUID id, @RequestBody @Valid AdminDTO dto) {
        return ResponseEntity.ok(adminService.atualizarAdmin(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAdmin(@PathVariable UUID id) {
        Admin adm = adminService.buscarPorId(id);
        if (adm != null) {
            adminService.deletarAdmin(id);
            usuarioRepository.deleteById(adm.getUsuario().getId());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping("/tarefa")
    public ResponseEntity<Tarefa> criarTarefa(@RequestBody @Valid TarefaDTO dto) {
        Turma turma = dto.getTurmaId() != null
                ? turmaService.buscarPorId(dto.getTurmaId()).orElse(null)
                : null;
        Tarefa t = new Tarefa();
        t.setTitulo(dto.getTitulo());
        t.setDescricao(dto.getDescricao());
        t.setDataEntrega(dto.getDataEntrega());
        t.setStatus(dto.getStatus());
        t.setNota(dto.getNota());
        t.setTurma(turma);
        Tarefa saved = tarefaService.salvar(t);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/tarefa")
    public ResponseEntity<List<Tarefa>> listarTarefas() {
        return ResponseEntity.ok(tarefaService.listarTodas());
    }

    @GetMapping("/tarefa/{id}")
    public ResponseEntity<Tarefa> buscarTarefa(@PathVariable Long id) {
        return tarefaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/tarefa/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id, @RequestBody @Valid TarefaDTO dto) {
        return tarefaService.buscarPorId(id)
                .map(existing -> {
                    existing.setTitulo(dto.getTitulo());
                    existing.setDescricao(dto.getDescricao());
                    existing.setDataEntrega(dto.getDataEntrega());
                    existing.setStatus(dto.getStatus());
                    existing.setNota(dto.getNota());
                    if (dto.getTurmaId() != null) {
                        Turma turmaU = turmaService.buscarPorId(dto.getTurmaId()).orElse(null);
                        existing.setTurma(turmaU);
                    }
                    return tarefaService.salvar(existing);
                })
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/tarefa/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable Long id) {
        tarefaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/professor")
    public ResponseEntity<Professor> criarProfessor(@RequestBody @Valid ProfessorDTO dto) {
        Professor p = professorService.criarProfessor(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(p);
    }

    @GetMapping("/professor")
    public ResponseEntity<List<Professor>> listarProfessores() {
        return ResponseEntity.ok(professorService.listarProfessores());
    }

    @GetMapping("/professor/{id}")
    public ResponseEntity<Professor> buscarProfessor(@PathVariable Long id) {
        return ResponseEntity.ok(professorService.buscarPorId(id));
    }

    @PutMapping("/professor/{id}")
    public ResponseEntity<Professor> atualizarProfessor(@PathVariable Long id, @RequestBody @Valid ProfessorDTO dto) {
        return ResponseEntity.ok(professorService.atualizarProfessor(id, dto));
    }

    @DeleteMapping("/professor/{id}")
    public ResponseEntity<Void> deletarProfessor(@PathVariable Long id) {
        professorService.deletarProfessor(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/aluno")
    public ResponseEntity<Aluno> criarAluno(@RequestBody @Valid AlunoDTO dto) {
        Aluno a = alunoService.criarAluno(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(a);
    }

    @GetMapping("/aluno")
    public ResponseEntity<List<Aluno>> listarAlunos() {
        return ResponseEntity.ok(alunoService.listarAlunos());
    }

    @GetMapping("/aluno/{id}")
    public ResponseEntity<Aluno> buscarAluno(@PathVariable Long id) {
        return ResponseEntity.ok(alunoService.buscarPorId(id));
    }

    @PutMapping("/aluno/{id}")
    public ResponseEntity<Aluno> atualizarAluno(@PathVariable Long id, @RequestBody @Valid AlunoDTO dto) {
        return ResponseEntity.ok(alunoService.atualizarAluno(id, dto));
    }

    @DeleteMapping("/aluno/{id}")
    public ResponseEntity<Void> deletarAluno(@PathVariable Long id) {
        alunoService.deletarAluno(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/turma")
    public ResponseEntity<Turma> criarTurma(@RequestBody @Valid TurmaDTO dto) {
        Turma t = new Turma();
        t.setCodigo(dto.getCodigo());
        t.setNome(dto.getNome());
        t.setAnoLetivo(dto.getAnoLetivo());
        Turma saved = turmaService.salvar(t);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }


    @GetMapping("/turma")
    public ResponseEntity<List<Turma>> listarTurmas() {
        return ResponseEntity.ok(turmaService.listarTodas());
    }

    @GetMapping("/turma/{id}")
    public ResponseEntity<Turma> buscarTurma(@PathVariable Long id) {
        return turmaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/turma/{id}")
    public ResponseEntity<Turma> atualizarTurma(
            @PathVariable Long id,
            @RequestBody @Valid TurmaDTO dto
    ) {
        Optional<Turma> turmaOpt = turmaService.buscarPorId(id);
        if (turmaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Turma turma = turmaOpt.get();
        turma.setCodigo(dto.getCodigo());
        turma.setNome(dto.getNome());
        turma.setAnoLetivo(dto.getAnoLetivo());

        Turma updated = turmaService.salvar(turma);
        return ResponseEntity.ok(updated);
    }



    @DeleteMapping("/turma/{id}")
    public ResponseEntity<Void> deletarTurma(@PathVariable Long id) {
        turmaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/aviso")
    public ResponseEntity<Aviso> criarAviso(@RequestBody @Valid AvisoDTO dto) {
        Turma turmaA = dto.getTurmaId() != null
                ? turmaService.buscarPorId(dto.getTurmaId()).orElse(null)
                : null;
        Aviso a = new Aviso();
        a.setTitulo(dto.getTitulo());
        a.setDescricao(dto.getDescricao());
        a.setDataCriacao(dto.getDataCriacao());
        a.setTurma(turmaA);
        Aviso saved = avisoService.salvar(a);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/aviso")
    public ResponseEntity<List<Aviso>> listarAvisos() {
        return ResponseEntity.ok(avisoService.listarTodos());
    }

    @GetMapping("/aviso/{id}")
    public ResponseEntity<Aviso> buscarAviso(@PathVariable Long id) {
        return avisoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/aviso/{id}")
    public ResponseEntity<Void> deletarAviso(@PathVariable Long id) {
        avisoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
