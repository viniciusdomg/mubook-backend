package br.com.mubook.mubook.service;

import br.com.mubook.mubook.model.TipoQuadra;
import org.springframework.data.domain.Page;

public interface TipoQuadraService extends GenericService<TipoQuadra, Integer> {

    Page<TipoQuadra> findAllPageable(int offset, int limit);
}
