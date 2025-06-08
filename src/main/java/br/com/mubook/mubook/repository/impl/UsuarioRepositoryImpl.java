package br.com.mubook.mubook.repository.impl;

import br.com.mubook.mubook.jpa.UsuarioJpaRepository;
import br.com.mubook.mubook.model.Usuario;
import br.com.mubook.mubook.repository.UsuarioRepository;

import java.util.List;

public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final UsuarioJpaRepository repository;

    public UsuarioRepositoryImpl(UsuarioJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Usuario save(Usuario model) {
        return null;
    }

    @Override
    public List<Usuario> findAll() {
        return List.of();
    }

    @Override
    public Usuario findById(Long id) {
        return null;
    }

    @Override
    public void hardDeleteById(Long id) {

    }

    @Override
    public void hardDeleteAll(Iterable<Long> id) {

    }

    @Override
    public void softDeleteById(Long id) {

    }
}
