package br.com.mubook.mubook.service.impl;

import br.com.mubook.mubook.entity.HorarioFuncionamentoEntity;
import br.com.mubook.mubook.jparepository.HorarioFuncionamentoJpaRepository;
import br.com.mubook.mubook.mapper.HorarioFuncionamentoEntityMapper;
import br.com.mubook.mubook.mapper.TipoQuadraEntityMapper;
import br.com.mubook.mubook.model.HorarioFuncionamento;
import br.com.mubook.mubook.model.TipoQuadra;
import br.com.mubook.mubook.service.HorarioFuncionamentoService;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
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
    public Optional<HorarioFuncionamento> findByTipoQuadraEntityAndDiaSemana(TipoQuadra tipo, DayOfWeek diaSemana) {
        return repository.findByTipoQuadraEntityAndDiaSemana(tipoQuadraMapper.fromModel(tipo),diaSemana)
                .map(mapper::toModel);
    }
}
