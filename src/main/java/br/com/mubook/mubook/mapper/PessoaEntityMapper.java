package br.com.mubook.mubook.mapper;

import br.com.mubook.mubook.entity.PessoaEntity;
import br.com.mubook.mubook.model.Pessoa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PessoaEntityMapper extends GenericEntityMapper<Pessoa, PessoaEntity>{

}
