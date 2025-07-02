package br.com.mubook.mubook.mapper;

import br.com.mubook.mubook.entity.TipoQuadraEntity;
import br.com.mubook.mubook.model.TipoQuadra;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TipoQuadraEntityMapper extends GenericEntityMapper<TipoQuadra, TipoQuadraEntity>
{

}