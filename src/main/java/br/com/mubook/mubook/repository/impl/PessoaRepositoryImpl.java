package br.com.mubook.mubook.repository.impl;

import br.com.mubook.mubook.jpa.PessoaJpaRepository;
import br.com.mubook.mubook.mapper.PessoaEntityMapper;
import br.com.mubook.mubook.model.Pessoa;
import br.com.mubook.mubook.repository.PessoaRepository;

import java.util.List;

public class PessoaRepositoryImpl implements PessoaRepository {

    private final PessoaJpaRepository repository;

    private final PessoaEntityMapper entityMapper;

    public PessoaRepositoryImpl(PessoaJpaRepository repository, PessoaEntityMapper entityMapper) {
        this.repository = repository;
        this.entityMapper = entityMapper;
    }

    @Override
    public Pessoa save(Pessoa model) {
        return entityMapper.toModel(repository.save(entityMapper.fromModel(model)));
    }

    @Override
    public List<Pessoa> findAll() {
        return entityMapper.toModel(repository.findAll());
    }

    @Override
    public Pessoa findById(Long id) {
        return entityMapper.toModel(repository.findById(id).orElse(null));
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
