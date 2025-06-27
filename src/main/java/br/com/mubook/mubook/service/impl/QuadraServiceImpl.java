package br.com.mubook.mubook.service.impl;

import br.com.mubook.mubook.entity.QuadraEntity;
import br.com.mubook.mubook.entity.TipoQuadraEntity;
import br.com.mubook.mubook.jparepository.QuadraJpaRepository;
import br.com.mubook.mubook.jparepository.TipoQuadraJpaRepository;
import br.com.mubook.mubook.mapper.QuadraEntityMapper;
import br.com.mubook.mubook.mapper.TipoQuadraEntityMapper;
import br.com.mubook.mubook.model.Quadra;
import br.com.mubook.mubook.model.TipoQuadra;
import br.com.mubook.mubook.service.QuadraService;
import br.com.mubook.mubook.service.TipoQuadraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
