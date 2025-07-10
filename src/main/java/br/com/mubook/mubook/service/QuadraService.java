package br.com.mubook.mubook.service;

import br.com.mubook.mubook.model.Quadra;
import org.springframework.data.domain.Page;

public interface QuadraService extends GenericService<Quadra, Integer> {

    Quadra save(Quadra quadra);

    void update(Integer id, Quadra dto);

    Page<Quadra> findAllByTipoQuadra(Long tipoQuadra, int page, int limit);
}
