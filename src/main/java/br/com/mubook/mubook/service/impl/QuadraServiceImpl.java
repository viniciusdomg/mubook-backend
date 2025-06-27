package br.com.mubook.mubook.service.impl;

import br.com.mubook.mubook.entity.QuadraEntity;
import br.com.mubook.mubook.jparepository.QuadraJpaRepository;
import br.com.mubook.mubook.mapper.QuadraEntityMapper;
import br.com.mubook.mubook.model.Quadra;
import br.com.mubook.mubook.service.QuadraService;
import org.springframework.stereotype.Service;

@Service
public class QuadraServiceImpl extends GenericServiceImpl<Quadra, Integer, QuadraEntity> implements QuadraService {

    private final QuadraJpaRepository repository;

    private final QuadraEntityMapper mapper;

    public QuadraServiceImpl(QuadraJpaRepository repository, QuadraEntityMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }
}
