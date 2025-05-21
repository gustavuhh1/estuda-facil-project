package com.unifor.estuda_facil.factory;

import com.unifor.estuda_facil.models.dto.AnexoDTO;
import com.unifor.estuda_facil.models.entity.Anexo;

public interface AnexoFactory {
    public Anexo criar(AnexoDTO dto);
}
