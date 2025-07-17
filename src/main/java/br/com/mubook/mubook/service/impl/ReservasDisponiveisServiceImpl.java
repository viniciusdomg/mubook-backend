package br.com.mubook.mubook.service.impl;

import br.com.mubook.mubook.entity.ReservasDisponiveisEntity;
import br.com.mubook.mubook.jparepository.ReservasDisponiveisJpaRepository;
import br.com.mubook.mubook.mapper.QuadraEntityMapper;
import br.com.mubook.mubook.mapper.ReservasDisponiveisEntityMapper;
import br.com.mubook.mubook.model.Quadra;
import br.com.mubook.mubook.model.Reserva;
import br.com.mubook.mubook.service.ReservasDisponiveisService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservasDisponiveisServiceImpl extends GenericServiceImpl<Reserva, Long, ReservasDisponiveisEntity> implements ReservasDisponiveisService {

    private final ReservasDisponiveisJpaRepository repository;

    private final ReservasDisponiveisEntityMapper mapper;

    private final QuadraEntityMapper quadraMapper;

    public ReservasDisponiveisServiceImpl (ReservasDisponiveisJpaRepository repository, ReservasDisponiveisEntityMapper mapper,
                                           QuadraEntityMapper quadraMapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
        this.quadraMapper = quadraMapper;
    }

    @Override
    public void deleteAllByReservasDataBefore(LocalDateTime data) {
        repository.deleteByDataHoraBefore(data);
    }

    @Override
    public Boolean existsByQuadraAndDataHora(Quadra quadra, LocalDateTime data) {
        return repository.existsByQuadraAndDataHora(quadraMapper.fromModel(quadra), data);
    }

    @Override
    public List<Reserva> saveAll(List<Reserva> reservas) {
        return mapper.toModel(repository.saveAll(mapper.fromModel(reservas)));
    }
}
