package br.com.mubook.mubook.repository;

import br.com.mubook.mubook.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends GenericRepository<Usuario, Long>{

    Optional<Usuario> findByEmailOrCpf(String search);
}
