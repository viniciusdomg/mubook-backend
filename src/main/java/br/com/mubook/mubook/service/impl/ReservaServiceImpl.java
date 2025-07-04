package br.com.mubook.mubook.service.impl;

import br.com.mubook.mubook.entity.ReservaEntity;
import br.com.mubook.mubook.jparepository.ReservaJpaRepository;
import br.com.mubook.mubook.mapper.ReservaEntityMapper;
import br.com.mubook.mubook.model.Reserva;
import br.com.mubook.mubook.service.ReservaService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

/**
 * Implementação da camada de serviço para Reserva.
 * A anotação @Service marca esta classe como um componente de serviço do
 * Spring.
 */
@Service
public class ReservaServiceImpl extends GenericServiceImpl<Reserva, Long, ReservaEntity> implements ReservaService {

    private final ReservaJpaRepository repository;
    private final ReservaEntityMapper mapper;

    public ReservaServiceImpl(ReservaJpaRepository repository, ReservaEntityMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Implementação da lógica para cancelar uma reserva.
     */
    @Override
    public Reserva cancelarReserva(Long id) {
        // Busca a reserva pelo ID. O método findById do GenericServiceImpl já lança
        // exceção se não encontrar.
        Reserva reserva = this.findById(id);

        // Usa o método do próprio modelo para alterar o status
        reserva.cancelar();

        // Salva a alteração no banco de dados
        return this.save(reserva);
    }

    /**
     * Implementação da lógica para remarcar uma reserva.
     */
    @Override
    public Reserva remarcarReserva(Long id, LocalDateTime novoHorario) {
        // Busca a reserva pelo ID.
        Reserva reserva = this.findById(id);

        // Usa o método do modelo para alterar a data e o status
        reserva.remarcar(novoHorario);

        // Salva a alteração no banco de dados
        return this.save(reserva);
    }
}