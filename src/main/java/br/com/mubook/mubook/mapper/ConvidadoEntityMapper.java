package br.com.mubook.mubook.mapper;

import br.com.mubook.mubook.entity.ConvidadoEntity;
import br.com.mubook.mubook.model.Convidado;
import org.mapstruct.Mapper;

/**
 * Mapper para converter entre o modelo Convidado e a entidade ConvidadoEntity.
 * 'componentModel = "spring"' permite que este mapper seja injetado como um
 * bean do Spring.
 * 'uses = PessoaEntityMapper.class' informa ao MapStruct para usar o mapper de
 * Pessoa
 * para converter o campo aninhado 'pessoa'.
 */
@Mapper(componentModel = "spring", uses = { PessoaEntityMapper.class })
public interface ConvidadoEntityMapper extends GenericEntityMapper<Convidado, ConvidadoEntity> {
}