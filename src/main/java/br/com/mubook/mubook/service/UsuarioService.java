package br.com.mubook.mubook.service;

import br.com.mubook.mubook.dto.FiltrosUsuarioRequest;
import br.com.mubook.mubook.model.Usuario;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UsuarioService extends GenericService<Usuario, Long> {

    Optional<Usuario> findByEmailOrCpf(String search);

    Page<Usuario> findAllWithFilters(FiltrosUsuarioRequest filtros, int offset, int limit);
}
