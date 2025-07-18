package br.com.mubook.mubook.service.impl;

import br.com.mubook.mubook.entity.HorarioFuncionamentoEntity;
import br.com.mubook.mubook.jparepository.HorarioFuncionamentoJpaRepository;
import br.com.mubook.mubook.mapper.HorarioFuncionamentoEntityMapper;
import br.com.mubook.mubook.mapper.TipoQuadraEntityMapper;
import br.com.mubook.mubook.model.HorarioFuncionamento;
import br.com.mubook.mubook.model.TipoQuadra;
import br.com.mubook.mubook.service.HorarioFuncionamentoService;
import br.com.mubook.mubook.utils.PageUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HorarioFuncionamentoServiceImpl
        extends GenericServiceImpl<HorarioFuncionamento, Integer, HorarioFuncionamentoEntity>
        implements HorarioFuncionamentoService {

    private final HorarioFuncionamentoJpaRepository repository;

    private final TipoQuadraEntityMapper tipoQuadraMapper;

    private final HorarioFuncionamentoEntityMapper mapper;

    public HorarioFuncionamentoServiceImpl(HorarioFuncionamentoJpaRepository repository,
            HorarioFuncionamentoEntityMapper mapper, TipoQuadraEntityMapper tipoQuadraMapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
        this.tipoQuadraMapper = tipoQuadraMapper;
    }

    @Override
    public Optional<HorarioFuncionamento> findByTipoQuadraEntityAndDiaSemana(TipoQuadra tipo, String diaSemana) {
        return repository.findbyTipoQuadraEntityAndDiaSemana(tipoQuadraMapper.fromModel(tipo), diaSemana)
                .map(mapper::toModel);
    }

    @Override
    public Page<HorarioFuncionamento> findAllPageable(int offset, int limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        Page<HorarioFuncionamentoEntity> entities = repository.findAll(pageable);
        List<HorarioFuncionamento> horarios = mapper.toModel(entities.getContent());
        return PageUtils.mapPage(entities, horarios);
    }
}
