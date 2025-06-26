package br.com.mubook.mubook.mapper;

import br.com.mubook.mubook.entity.QuadraEntity;
import br.com.mubook.mubook.model.Quadra;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuadraEntityMapper extends GenericEntityMapper<Quadra, QuadraEntity> {

}
