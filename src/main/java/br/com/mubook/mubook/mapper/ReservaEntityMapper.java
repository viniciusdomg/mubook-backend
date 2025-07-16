package br.com.mubook.mubook.mapper;

import br.com.mubook.mubook.entity.HistoricoReservasEntity;
import br.com.mubook.mubook.model.Reserva;
import org.mapstruct.Mapper;

/**
 * Mapper para converter entre o modelo Reserva e a entidade ReservaEntity.
 * componentModel = "spring" permite que este mapper seja injetado em outros
 * componentes do Spring.
 * A cláusula 'uses' informa ao MapStruct para usar outros mappers para os
 * campos aninhados.
 */
@Mapper(componentModel = "spring", uses = { UsuarioEntityMapper.class, QuadraEntityMapper.class,
        PessoaEntityMapper.class })
public interface ReservaEntityMapper extends GenericEntityMapper<Reserva, HistoricoReservasEntity> {
}