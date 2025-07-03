package br.com.mubook.mubook.mapper;

import br.com.mubook.mubook.entity.HorarioFuncionamentoEntity;
import br.com.mubook.mubook.model.HorarioFuncionamento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HorarioFuncionamentoEntityMapper
        extends GenericEntityMapper<HorarioFuncionamento, HorarioFuncionamentoEntity> {
}
