package br.com.mubook.mubook.service.impl;

import br.com.mubook.mubook.entity.PessoaEntity;
import br.com.mubook.mubook.jparepository.PessoaJpaRepository;
import br.com.mubook.mubook.mapper.PessoaEntityMapper;
import br.com.mubook.mubook.model.Pessoa;
import br.com.mubook.mubook.service.PessoaService;

public class PessoaServiceImpl extends GenericServiceImpl<Pessoa, Long, PessoaEntity> implements PessoaService {

    private final PessoaJpaRepository repository;

    private final PessoaEntityMapper mapper;

    public PessoaServiceImpl(PessoaJpaRepository repository, PessoaEntityMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }
}
