package br.com.mubook.mubook.service.impl;

import br.com.mubook.mubook.dto.ReservaUpdateDto;
import br.com.mubook.mubook.entity.*;
import br.com.mubook.mubook.enums.RoleUser;
import br.com.mubook.mubook.enums.StatusReserva;
import br.com.mubook.mubook.exception.BussinesException;
import br.com.mubook.mubook.jparepository.*;
import br.com.mubook.mubook.jparepository.specifications.ReservaSpecifications;
import br.com.mubook.mubook.mapper.ConvidadoEntityMapper;
import br.com.mubook.mubook.mapper.HistoricoReservaEntityMapper;
import br.com.mubook.mubook.model.Convidado;
import br.com.mubook.mubook.model.Reserva;
import br.com.mubook.mubook.service.HistoricoReservasService;
import br.com.mubook.mubook.utils.PageUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementação da camada de serviço para Reserva.
 * A anotação @Service marca esta classe como um componente de serviço do
 * Spring.
 */
@Service
public class HistoricoReservasServiceImpl extends GenericServiceImpl<Reserva, Long, HistoricoReservasEntity> implements HistoricoReservasService {

    private final HistoricoReservasJpaRepository repository;

    private final QuadraJpaRepository quadraRepository;

    private final UsuarioJpaRepository usuarioRepository;

    private final ConvidadoJpaRepository convidadoRepository;

    private final PessoaJpaRepository pessoaRepository;

    private final ReservasDisponiveisJpaRepository disponiveisRepository;

    private final HistoricoReservaEntityMapper mapper;

    private final ConvidadoEntityMapper convidadoMapper;

    private final HistoricoReservasJpaRepository historicoReservasJpaRepository;

    public HistoricoReservasServiceImpl(HistoricoReservasJpaRepository repository, HistoricoReservaEntityMapper mapper,
                                     QuadraJpaRepository quadraRepository, ConvidadoJpaRepository convidadoRepository,
                                     UsuarioJpaRepository usuarioRepository, PessoaJpaRepository pessoaRepository,
                                     ReservasDisponiveisJpaRepository disponiveisRepository, ConvidadoEntityMapper convidadoMapper, HistoricoReservasJpaRepository historicoReservasJpaRepository) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
        this.quadraRepository = quadraRepository;
        this.convidadoRepository = convidadoRepository;
        this.usuarioRepository = usuarioRepository;
        this.pessoaRepository = pessoaRepository;
        this.disponiveisRepository = disponiveisRepository;
        this.convidadoMapper = convidadoMapper;
        this.historicoReservasJpaRepository = historicoReservasJpaRepository;
    }


    @Override
    public Page<Reserva> findReservasWithFilterPageable(Long idTipoQuadra, LocalDate data, LocalTime hora, int offset, int limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        Page<HistoricoReservasEntity> entities = repository.findAll(ReservaSpecifications.
                comFiltrosHistorico(idTipoQuadra, data, hora, StatusReserva.CONFIRMADA), pageable);

        List<Reserva> reservas = mapper.toModel(entities.getContent());
        return PageUtils.mapPage(entities, reservas);
    }

    @Override
    public List<Reserva> findReservasWithFilter(Long idTipoQuadra, LocalDate data, LocalTime hora, Long idUsuario) {
        List<HistoricoReservasEntity> entities = repository.findAll(ReservaSpecifications.comFiltrosHistorico(idTipoQuadra,
                data, hora, StatusReserva.CONFIRMADA));
        return mapper.toModel(entities);
    }

    @Override
    @Transactional
    public Reserva agendarReserva(Reserva reserva) {
        validate(reserva);
        HistoricoReservasEntity entity = mapper.fromModel(reserva);
        for(ConvidadoEntity convidado : entity.getConvidados()){
            PessoaEntity pessoa = pessoaRepository.findByCpf(convidado.getPessoa().getCpf());
            if(pessoa != null){
                convidado.setPessoa(pessoa);
            }
            convidado.setReserva(entity);
        }

        Reserva reservaSalva = mapper.toModel(repository.save(entity));

        disponiveisRepository.alteraStatusReserva(reservaSalva.getDataHora(),
                reservaSalva.getQuadra().getId(), StatusReserva.AGENDADO);

        return reservaSalva;
    }


    @Override
    public Reserva cancelarReserva(Long id) {
        Reserva reserva = this.findById(id);
        validateCancelar(reserva);
        HistoricoReservasEntity entity = mapper.fromModel(reserva);
        disponiveisRepository.alteraStatusReserva(entity.getDataHora(), entity.getQuadra().getId(),
                StatusReserva.DISPONIVEL);
        reserva.cancelar();
        return this.save(reserva);
    }


    @Override
    @Transactional
    public Reserva finalizarReserva(Long id) {
        HistoricoReservasEntity reserva = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva não encontrada com o ID: " + id));

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

        if (dto.getDataHora() != null) {
            reserva.setDataHora(dto.getDataHora());
        }

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
    public Reserva adicionarConvidados(Long reservaId, List<Convidado> convidados) {
        validatePessoa(convidados);

        HistoricoReservasEntity reserva = repository.findById(reservaId)
                .orElseThrow(() -> new EntityNotFoundException("Reserva não encontrada com o ID: " + reservaId));

        if (reserva.getConvidados() == null) {
            reserva.setConvidados(new ArrayList<>());
        }

        List<String> cpfsExistentes = reserva.getConvidados().stream()
                .map(c -> c.getPessoa().getCpf())
                .toList();

        List<ConvidadoEntity> novosConvidados = convidadoMapper.fromModel(convidados);

        for (ConvidadoEntity convidado : novosConvidados) {
            PessoaEntity pessoa = pessoaRepository.findByCpf(convidado.getPessoa().getCpf());
            if (pessoa != null) {
                convidado.setPessoa(pessoa);
            }

            if (!cpfsExistentes.contains(convidado.getPessoa().getCpf())) {
                convidado.setReserva(reserva);
                reserva.getConvidados().add(convidado);
            }
        }

        repository.save(reserva);
        return mapper.toModel(reserva);
    }

    @Override
    @Transactional
    public void removerConvidados(List<Long> convidadosIds) {
        convidadoRepository.deleteAllById(convidadosIds);
    }

    @Override
    @Transactional
    public void removerConvidado(Long id) {
        convidadoRepository.deleteById(id);
    }

    private void validate(Reserva reserva){
        if(repository.existsByUsuarioIdAndStatus(reserva.getUsuario().getId(), StatusReserva.CONFIRMADA)){
            Optional<UsuarioEntity> entity = usuarioRepository.findById(reserva.getUsuario().getId());
            if(entity.isPresent()){
                if (entity.get().getRoleUser() == RoleUser.ROLE_ASSOCIADO){
                    throw new BussinesException("Desculpe, não é possível fazer sua reserva pois ainda há uma vigente");
                }
            }
        }
    }

    public long contarReservasUltimos30Dias() {
        LocalDateTime trintaDiasAtras = LocalDateTime.now().minusDays(30);
        return historicoReservasJpaRepository.countReservasUltimos30Dias(trintaDiasAtras);
    }

    private void validateCancelar(Reserva reserva){
        if(reserva == null){
            throw new BussinesException("Erro ao encontrar reserva");
        }
        if (reserva.getDataHora().isBefore(LocalDateTime.now())) {
            throw new BussinesException("A reserva não pode mais ser cancelada. O horário já passou.");
        }
        if (reserva.getStatus() != StatusReserva.CONFIRMADA) {
            throw new BussinesException("Apenas reservas confirmadas podem ser canceladas.");
        }
    }

    private void validatePessoa(List<Convidado> convidados){
        for (Convidado convidado : convidados) {
            String cpf = convidado.getPessoa().getCpf();
            String email = convidado.getPessoa().getEmail();
            String nome = convidado.getPessoa().getNome();

            if (cpf == null || !cpf.matches("\\d{11}")) {
                throw new BussinesException("CPF "+ cpf +" inválido: deve conter exatamente 11 dígitos");
            }

            if (email == null || !email.matches("^[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,}$")) {
                throw new IllegalArgumentException("E-mail inválido: " + email);
            }

            if (nome == null || nome.trim().isEmpty()) {
                throw new IllegalArgumentException("Nome não pode estar vazio.");
            }
        }
    }
}