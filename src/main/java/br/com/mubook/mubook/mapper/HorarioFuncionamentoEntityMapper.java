package br.com.mubook.mubook.mapper;

import br.com.mubook.mubook.entity.HorarioFuncionamentoEntity;
import br.com.mubook.mubook.model.HorarioFuncionamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { TipoQuadraEntityMapper.class })
public interface HorarioFuncionamentoEntityMapper
        extends GenericEntityMapper<HorarioFuncionamento, HorarioFuncionamentoEntity> {

    @Mapping(source = "tipoQuadra", target = "tipoQuadraEntity")
    @Override
    HorarioFuncionamentoEntity fromModel(HorarioFuncionamento model);

    @Mapping(source = "tipoQuadraEntity", target = "tipoQuadra")
    @Override
    HorarioFuncionamento toModel(HorarioFuncionamentoEntity entity);
}
