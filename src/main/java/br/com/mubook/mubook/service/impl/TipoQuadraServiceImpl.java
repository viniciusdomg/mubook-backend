package br.com.mubook.mubook.service.impl;

import br.com.mubook.mubook.entity.TipoQuadraEntity;
import br.com.mubook.mubook.jparepository.TipoQuadraJpaRepository;
import br.com.mubook.mubook.mapper.TipoQuadraEntityMapper;
import br.com.mubook.mubook.model.TipoQuadra;
import br.com.mubook.mubook.service.TipoQuadraService;
import org.springframework.stereotype.Service;


@Service
public class TipoQuadraServiceImpl extends GenericServiceImpl<TipoQuadra, Integer, TipoQuadraEntity> implements TipoQuadraService {

    private final TipoQuadraJpaRepository repository;

    private final TipoQuadraEntityMapper mapper;

    public TipoQuadraServiceImpl(TipoQuadraJpaRepository repository, TipoQuadraEntityMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

//    @Override
//    public Page<TipoQuadra> findAllPageable(int offset, int limit) {
//        Pageable pageable = PageRequest.of(offset, limit);
//        Page<TipoQuadraEntity> entities = repository.findAll(pageable);
//        List<TipoQuadra> tipos = mapper.toModel(entities.getContent());
//        return PageUtils.mapPage(entities, tipos);
//    }
}
