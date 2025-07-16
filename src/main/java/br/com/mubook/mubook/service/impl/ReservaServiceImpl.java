package br.com.mubook.mubook.service.impl;

import br.com.mubook.mubook.dto.ReservaCreateDto;
import br.com.mubook.mubook.dto.ReservaUpdateDto;
import br.com.mubook.mubook.entity.ConvidadoEntity;
import br.com.mubook.mubook.entity.QuadraEntity;
import br.com.mubook.mubook.entity.HistoricoReservasEntity;
import br.com.mubook.mubook.entity.UsuarioEntity;
import br.com.mubook.mubook.enums.StatusReserva;
import br.com.mubook.mubook.jparepository.*; // Importe todos os repositórios necessários
import br.com.mubook.mubook.mapper.ReservaEntityMapper;
import br.com.mubook.mubook.model.Reserva;
import br.com.mubook.mubook.service.ReservaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação da camada de serviço para Reserva.
 * A anotação @Service marca esta classe como um componente de serviço do
 * Spring.
 */
@Service
public class ReservaServiceImpl extends GenericServiceImpl<Reserva, Long, HistoricoReservasEntity> implements ReservaService {

    private final ReservaJpaRepository repository;
    private final ReservaEntityMapper mapper;
    private final UsuarioJpaRepository usuarioRepository; // Nova dependência
    private final QuadraJpaRepository quadraRepository;   // Nova dependência
    private final ConvidadoJpaRepository convidadoRepository; // Nova dependência

    public ReservaServiceImpl(ReservaJpaRepository repository, ReservaEntityMapper mapper,
                              UsuarioJpaRepository usuarioRepository, QuadraJpaRepository quadraRepository,
                              ConvidadoJpaRepository convidadoRepository) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
        this.usuarioRepository = usuarioRepository;
        this.quadraRepository = quadraRepository;
        this.convidadoRepository = convidadoRepository;
    }

    @Override
    @Transactional
    public Reserva criarReserva(ReservaCreateDto dto) {
        // 1. Buscar as entidades relacionadas pelos IDs
        UsuarioEntity usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o ID: " + dto.getUsuarioId()));

        QuadraEntity quadra = quadraRepository.findById(dto.getQuadraId())
                .orElseThrow(() -> new EntityNotFoundException("Quadra não encontrada com o ID: " + dto.getQuadraId()));

        List<ConvidadoEntity> convidados = new ArrayList<>();
        if (dto.getConvidadosIds() != null && !dto.getConvidadosIds().isEmpty()) {
            convidados = convidadoRepository.findAllById(dto.getConvidadosIds());
        }

        // 2. Criar e preencher a nova ReservaEntity
        HistoricoReservasEntity novaReserva = new HistoricoReservasEntity();
        novaReserva.setDataHora(dto.getDataHora());
        novaReserva.setUsuario(usuario);
        novaReserva.setQuadra(quadra);
        novaReserva.setConvidados(convidados);
        novaReserva.setStatus(StatusReserva.CONFIRMADA); // Status inicial padrão

        // 3. Salvar no banco e mapear para o modelo de retorno
        HistoricoReservasEntity reservaSalva = repository.save(novaReserva);
        return mapper.toModel(reservaSalva);
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

    @Override
    @Transactional
    public Reserva finalizarReserva(Long id) {
        HistoricoReservasEntity reserva = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva não encontrada com o ID: " + id));

        // Regra de negócio: só podemos finalizar uma reserva que está confirmada.
        if (reserva.getStatus() != StatusReserva.CONFIRMADA) {
            throw new IllegalStateException("Apenas reservas confirmadas podem ser finalizadas.");
        }

        reserva.setStatus(StatusReserva.FINALIZADA);
        HistoricoReservasEntity reservaSalva = repository.save(reserva);
        return mapper.toModel(reservaSalva);
    }

    @Override
    @Transactional
    public Reserva editarReserva(Long id, ReservaUpdateDto dto) {
        HistoricoReservasEntity reserva = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva não encontrada com o ID: " + id));

        // Atualiza a data/hora se ela for fornecida no DTO
        if (dto.getDataHora() != null) {
            reserva.setDataHora(dto.getDataHora());
        }

        // Atualiza a quadra se o ID for fornecido no DTO
        if (dto.getQuadraId() != null) {
            QuadraEntity novaQuadra = quadraRepository.findById(dto.getQuadraId())
                    .orElseThrow(() -> new EntityNotFoundException("Quadra não encontrada com o ID: " + dto.getQuadraId()));
            reserva.setQuadra(novaQuadra);
        }

        HistoricoReservasEntity reservaSalva = repository.save(reserva);
        return mapper.toModel(reservaSalva);
    }

    @Override
    @Transactional
    public Reserva adicionarConvidados(Long reservaId, List<Long> convidadosIds) {
        HistoricoReservasEntity reserva = repository.findById(reservaId)
                .orElseThrow(() -> new EntityNotFoundException("Reserva não encontrada com o ID: " + reservaId));

        List<ConvidadoEntity> novosConvidados = convidadoRepository.findAllById(convidadosIds);

        // Evita adicionar convidados duplicados
        novosConvidados.forEach(convidado -> {
            if (!reserva.getConvidados().contains(convidado)) {
                reserva.getConvidados().add(convidado);
            }
        });

        repository.save(reserva);
        return mapper.toModel(reserva);
    }

    @Override
    @Transactional
    public Reserva removerConvidados(Long reservaId, List<Long> convidadosIds) {
        HistoricoReservasEntity reserva = repository.findById(reservaId)
                .orElseThrow(() -> new EntityNotFoundException("Reserva não encontrada com o ID: " + reservaId));

        List<ConvidadoEntity> convidadosParaRemover = convidadoRepository.findAllById(convidadosIds);
        reserva.getConvidados().removeAll(convidadosParaRemover);

        repository.save(reserva);
        return mapper.toModel(reserva);
    }
}