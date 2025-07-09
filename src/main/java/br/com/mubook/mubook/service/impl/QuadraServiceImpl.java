package br.com.mubook.mubook.service.impl;

import br.com.mubook.mubook.entity.QuadraEntity;
import br.com.mubook.mubook.jparepository.QuadraJpaRepository;
import br.com.mubook.mubook.jparepository.TipoQuadraJpaRepository;
import br.com.mubook.mubook.jparepository.specifications.QuadraSpecifications;
import br.com.mubook.mubook.mapper.QuadraEntityMapper;
import br.com.mubook.mubook.model.Quadra;
import br.com.mubook.mubook.service.QuadraService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuadraServiceImpl extends GenericServiceImpl<Quadra, Integer, QuadraEntity> implements QuadraService {

    private final QuadraJpaRepository repository;
    private final QuadraEntityMapper mapper;
    private final TipoQuadraJpaRepository tipoQuadraRepository;

    public QuadraServiceImpl(QuadraJpaRepository repository, QuadraEntityMapper mapper, TipoQuadraJpaRepository tipoQuadraRepository) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
        this.tipoQuadraRepository = tipoQuadraRepository;
    }

    @Override
    public void update(Integer id, Quadra quadra) {
        QuadraEntity quadraEntity = mapper.fromModel(quadra);
        quadraEntity.setId(id);
        repository.save(quadraEntity);
    }

    @Override
    public List<Quadra> findAllByTipoQuadra(String tipoQuadra) {
        return mapper.toModel(repository.findAll(QuadraSpecifications.comFiltros(tipoQuadra)));
    }

}
