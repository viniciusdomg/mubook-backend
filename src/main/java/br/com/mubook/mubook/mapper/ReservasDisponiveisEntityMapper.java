package br.com.mubook.mubook.mapper;


import br.com.mubook.mubook.entity.ReservasDisponiveisEntity;
import br.com.mubook.mubook.model.Reserva;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { UsuarioEntityMapper.class, QuadraEntityMapper.class,
        PessoaEntityMapper.class })
public interface ReservasDisponiveisEntityMapper extends GenericEntityMapper<Reserva, ReservasDisponiveisEntity> {
}
