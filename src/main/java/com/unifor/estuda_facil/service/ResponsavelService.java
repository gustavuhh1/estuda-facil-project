package com.unifor.estuda_facil.service;

import com.unifor.estuda_facil.aspect.Loggable;
import com.unifor.estuda_facil.models.dto.ResponsavelDTO;
import com.unifor.estuda_facil.models.entity.Responsavel;
import com.unifor.estuda_facil.repository.ResponsavelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ResponsavelService {

    private final ResponsavelRepository responsavelRepository;
    private final UsuarioService usuarioService;

    @Loggable
    public Responsavel criarResponsavel(ResponsavelDTO dto) {
        Responsavel responsavel = new Responsavel();
        responsavel.setNome(dto.getNome());
        responsavel.setTelefone(dto.getTelefone());
        responsavel.setCpf(dto.getCpf());

        usuarioService.prepararUsuario(responsavel);

        return responsavelRepository.save(responsavel);
    }
    @Loggable
    public List<Responsavel> listarTodos() {
        return responsavelRepository.findAll();
    }

    public Responsavel buscarPorId(UUID id) {
        return responsavelRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Responsável não encontrado"));
    }
    @Loggable
    public Responsavel atualizar(UUID id, ResponsavelDTO dto) {
        Responsavel r = buscarPorId(id);
        r.setNome(dto.getNome());
        r.setTelefone(dto.getTelefone());
        r.setCpf(dto.getCpf());
        return responsavelRepository.save(r);
    }
    @Loggable
    public void deletar(UUID id) {
        responsavelRepository.deleteById(id);
    }
}
