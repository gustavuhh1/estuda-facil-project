package com.unifor.estuda_facil.service;

import com.unifor.estuda_facil.models.dto.ResponsavelDTO;
import com.unifor.estuda_facil.models.entity.Responsavel;
import com.unifor.estuda_facil.repository.ResponsavelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResponsavelService {

    private final ResponsavelRepository responsavelRepository;

    public Responsavel criarResponsavel(ResponsavelDTO dto) {
        Responsavel responsavel = new Responsavel();
        responsavel.setNome(dto.getNome());
        responsavel.setTelefone(dto.getTelefone());
        responsavel.setCpf(dto.getCpf());
        return responsavelRepository.save(responsavel);
    }

    public List<Responsavel> listarTodos() {
        return responsavelRepository.findAll();
    }

    public Responsavel buscarPorId(Long id) {
        return responsavelRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Responsável não encontrado"));
    }

    public Responsavel atualizar(Long id, ResponsavelDTO dto) {
        Responsavel r = buscarPorId(id);
        r.setNome(dto.getNome());
        r.setTelefone(dto.getTelefone());
        r.setCpf(dto.getCpf());
        return responsavelRepository.save(r);
    }

    public void deletar(Long id) {
        responsavelRepository.deleteById(id);
    }
}
