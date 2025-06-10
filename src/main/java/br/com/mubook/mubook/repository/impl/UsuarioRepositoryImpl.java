package br.com.mubook.mubook.repository.impl;

import br.com.mubook.mubook.jpa.UsuarioJpaRepository;
import br.com.mubook.mubook.mapper.UsuarioEntityMapper;
import br.com.mubook.mubook.model.Usuario;
import br.com.mubook.mubook.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final UsuarioJpaRepository repository;

    private final UsuarioEntityMapper entityMapper;

    public UsuarioRepositoryImpl(UsuarioJpaRepository repository, UsuarioEntityMapper entityMapper) {
        this.repository = repository;
        this.entityMapper = entityMapper;
    }

    @Override
    public Usuario save(Usuario model) {
        return entityMapper.toModel(repository.save(entityMapper.fromModel(model)));
    }

    @Override
    public List<Usuario> findAll() {
        return List.of();
    }

    @Override
    public Usuario findById(Long id) {
        return entityMapper.toModel(repository.findById(id).orElse(null));
    }

    @Override
    public Optional<Usuario> findByEmailOrCpf(String search) {
        return Optional.ofNullable(entityMapper.toModel(repository.findByEmailOrCpf(search)));
    }

    @Override
    public void hardDeleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void hardDeleteAll(Iterable<Long> id) {
        repository.deleteAllById(id);
    }

    @Override
    public void softDeleteById(Long id) {
    }
}
