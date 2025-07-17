package br.com.mubook.mubook.service;

import br.com.mubook.mubook.dto.ConvidadoCreateDto;
import br.com.mubook.mubook.model.Convidado;

/**
 * Interface de serviço para a entidade Convidado.
 * Estende a GenericService para obter os métodos CRUD padrão (findAll,
 * findById, save, delete).
 * Não há necessidade de métodos adicionais por enquanto.
 */
public interface ConvidadoService extends GenericService<Convidado, Long> {

    /**
     * Cria uma nova Pessoa e a associa a um novo Convidado.
     * @param dto Os dados para a criação da pessoa/convidado.
     * @return O modelo do Convidado criado.
     */
    Convidado criarConvidadoComPessoa(ConvidadoCreateDto dto);
}