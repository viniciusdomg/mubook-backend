package br.com.mubook.mubook.service;

import br.com.mubook.mubook.model.Usuario;

import java.util.Optional;

public interface UsuarioService extends GenericService<Usuario, Long> {

    Optional<Usuario> findByEmailOrCpf(String search);
}
