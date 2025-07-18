package br.com.mubook.mubook.service.impl;

import br.com.mubook.mubook.entity.QuadraEntity;
import br.com.mubook.mubook.exception.BussinesException;
import br.com.mubook.mubook.exception.ObjetoNullException;
import br.com.mubook.mubook.jparepository.QuadraJpaRepository;
import br.com.mubook.mubook.jparepository.specifications.QuadraSpecifications;
import br.com.mubook.mubook.mapper.QuadraEntityMapper;
import br.com.mubook.mubook.model.Quadra;
import br.com.mubook.mubook.service.QuadraService;
import br.com.mubook.mubook.utils.PageUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuadraServiceImpl extends GenericServiceImpl<Quadra, Integer, QuadraEntity> implements QuadraService {

    private final QuadraJpaRepository repository;
    private final QuadraEntityMapper mapper;

    public QuadraServiceImpl(QuadraJpaRepository repository, QuadraEntityMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Quadra save(Quadra quadra){
        validate(quadra);
        return mapper.toModel(repository.save(mapper.fromModel(quadra)));
    }

    @Override
    public void update(Integer id, Quadra quadra) {
        QuadraEntity quadraEntity = mapper.fromModel(quadra);
        quadraEntity.setId(id);
        repository.save(quadraEntity);
    }

    public long contarQuadras() {
        return repository.countTotalQuadras();
    }

    @Override
    public Page<Quadra> findAllByTipoQuadra(Long tipoQuadra, int offset, int limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        Page<QuadraEntity> entities = repository.findAll(QuadraSpecifications.comFiltros(tipoQuadra), pageable);
        List<Quadra> quadras = mapper.toModel(entities.getContent());
        return PageUtils.mapPage(entities, quadras);
    }

    private void validate(Quadra quadra){
        if (quadra == null){
            throw new ObjetoNullException();
        }
        if (quadra.getQuantidadeMaxima() <= 0){
            throw new BussinesException("Essa quantidade não é valida, não é permitido valores menores ou iguais a zero");
        }
    }
}
