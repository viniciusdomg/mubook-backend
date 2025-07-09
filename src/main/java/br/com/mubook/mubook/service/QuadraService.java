package br.com.mubook.mubook.service;

import br.com.mubook.mubook.model.Quadra;

import java.util.List;

public interface QuadraService extends GenericService<Quadra, Integer> {

    void update(Integer id, Quadra dto);

    List<Quadra> findAllByTipoQuadra(String tipoQuadra);
}
