package br.com.mubook.mubook.service;

import br.com.mubook.mubook.model.Convidado;

/**
 * Interface de serviço para a entidade Convidado.
 * Estende a GenericService para obter os métodos CRUD padrão (findAll,
 * findById, save, delete).
 * Não há necessidade de métodos adicionais por enquanto.
 */
public interface ConvidadoService extends GenericService<Convidado, Long> {
}