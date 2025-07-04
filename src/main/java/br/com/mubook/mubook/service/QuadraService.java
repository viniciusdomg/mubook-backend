package br.com.mubook.mubook.service;

import br.com.mubook.mubook.dto.QuadraCreateDTO;
import br.com.mubook.mubook.model.Quadra;

public interface QuadraService extends GenericService<Quadra, Integer> {
    Quadra createFromDTO(QuadraCreateDTO dto);
    Quadra updateFromDTO(Integer id, QuadraCreateDTO dto);
}
