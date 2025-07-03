package br.com.mubook.mubook.service.impl;

import br.com.mubook.mubook.entity.HorarioFuncionamentoEntity;
import br.com.mubook.mubook.jparepository.HorarioFuncionamentoJpaRepository;
import br.com.mubook.mubook.mapper.HorarioFuncionamentoEntityMapper;
import br.com.mubook.mubook.model.HorarioFuncionamento;
import br.com.mubook.mubook.service.HorarioFuncionamentoService;
import org.springframework.stereotype.Service;

@Service
public class HorarioFuncionamentoServiceImpl
        extends GenericServiceImpl<HorarioFuncionamento, Integer, HorarioFuncionamentoEntity>
        implements HorarioFuncionamentoService {

    private final HorarioFuncionamentoJpaRepository repository;
    private final HorarioFuncionamentoEntityMapper mapper;

    public HorarioFuncionamentoServiceImpl(HorarioFuncionamentoJpaRepository repository,
            HorarioFuncionamentoEntityMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }
}
