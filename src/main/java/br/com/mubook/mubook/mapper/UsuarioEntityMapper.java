package br.com.mubook.mubook.mapper;

import br.com.mubook.mubook.entity.UsuarioEntity;
import br.com.mubook.mubook.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioEntityMapper extends GenericEntityMapper<Usuario, UsuarioEntity> {

}
