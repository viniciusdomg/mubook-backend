package br.com.mubook.mubook.restcontroller;

import br.com.mubook.mubook.dto.PageResponse;
import br.com.mubook.mubook.dto.ReservaConvidadoDto;
import br.com.mubook.mubook.dto.ReservaCreateDto;
import br.com.mubook.mubook.dto.ReservaUpdateDto;
import br.com.mubook.mubook.exception.BussinesException;
import br.com.mubook.mubook.helper.HistoricoReservaHelper;
import br.com.mubook.mubook.model.Quadra;
import br.com.mubook.mubook.model.Reserva;
import br.com.mubook.mubook.model.Usuario;
import br.com.mubook.mubook.service.HistoricoReservasService;
import br.com.mubook.mubook.service.QuadraService;
import br.com.mubook.mubook.service.ReservasDisponiveisService;
import br.com.mubook.mubook.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/reserva/")
@RequiredArgsConstructor
public class ReservaController {

    private final HistoricoReservasService historicoReservasService;

    private final ReservasDisponiveisService reservasDisponiveisService;

    private final QuadraService quadraService;

    private final UsuarioService usuarioService;

    private final HistoricoReservaHelper historicoHelper;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'ASSOCIADO')")
    public ResponseEntity<List<Reserva>> reservasDisponiveis(
            @RequestParam(required = false) Long idTipoQuadra, @RequestParam(required = false) LocalDate data,
            @RequestParam(required = false) LocalTime hora) {
        return ResponseEntity.ok(reservasDisponiveisService.findReservasWithFilter(idTipoQuadra, data, hora));
    }

    @GetMapping("historico/admin")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<PageResponse<Reserva>> findAll(
            @RequestParam(required = false, defaultValue = "0") int offset,
            @RequestParam(required = false, defaultValue = "20") int limit,
            @RequestParam(required = false) Long idTipoQuadra, @RequestParam(required = false) LocalDate data,
            @RequestParam(required = false) LocalTime hora) {
        try{
            Page<Reserva> reservaPage = historicoReservasService.findReservasWithFilterPageable(idTipoQuadra, data, hora, offset, limit);
            PageResponse<Reserva> pageResponse = historicoHelper.PageToPageResponse(reservaPage);
            return ResponseEntity.ok(pageResponse);
        }catch (Exception e){
            throw new RuntimeException("Erro ao buscar reservas: "+ e.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Reserva> findById(@PathVariable Long id) {
        return ResponseEntity.ok(historicoReservasService.findById(id));
    }

    /**
     * Endpoint para criar uma nova reserva.
     * Recebe um DTO com os IDs de usuário, quadra e convidados.
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'ASSOCIADO')")
    public ResponseEntity<Reserva> agendar(@Valid @RequestBody ReservaCreateDto reservaDto) {
        try {
            Quadra quadra = quadraService.findById(reservaDto.quadraId());

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            Optional<Usuario> usuarioOpt = usuarioService.findByEmailOrCpf(username);
            if (usuarioOpt.isEmpty()) {
                throw new BussinesException("Usuário não encontrado");
            }

            Reserva historico = historicoHelper.RequestToModel(reservaDto, quadra, usuarioOpt.get());

            Reserva novaReserva = historicoReservasService.agendarReserva(historico);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(novaReserva.getId()).toUri();

            return ResponseEntity.created(uri).body(novaReserva);
        }catch (Exception e){
            throw new RuntimeException("Erro ao agendar reserva: "+ e.getMessage());
        }
    }

    @PatchMapping("{id}/cancelar")
    public ResponseEntity<Reserva> cancelar(@PathVariable Long id) {
        Reserva reservaCancelada = historicoReservasService.cancelarReserva(id);
        return ResponseEntity.ok(reservaCancelada);
    }

    @PutMapping("{id}/finalizar")
    public ResponseEntity<Reserva> finalizar(@PathVariable Long id) {
        Reserva reservaFinalizada = historicoReservasService.finalizarReserva(id);
        return ResponseEntity.ok(reservaFinalizada);
    }

    @PutMapping("{id}")
    public ResponseEntity<Reserva> editar(@PathVariable Long id, @Valid @RequestBody ReservaUpdateDto reservaDto) {
        Reserva reservaEditada = historicoReservasService.editarReserva(id, reservaDto);
        return ResponseEntity.ok(reservaEditada);
    }

    @PostMapping("{id}/convidados")
    public ResponseEntity<Reserva> adicionarConvidados(@PathVariable Long id, @Valid @RequestBody ReservaConvidadoDto dto) {
        Reserva reservaAtualizada = historicoReservasService.adicionarConvidados(id, dto.getConvidadosIds());
        return ResponseEntity.ok(reservaAtualizada);
    }

    @DeleteMapping("{id}/convidados")
    public ResponseEntity<Reserva> removerConvidados(@PathVariable Long id, @Valid @RequestBody ReservaConvidadoDto dto) {
        Reserva reservaAtualizada = historicoReservasService.removerConvidados(id, dto.getConvidadosIds());
        return ResponseEntity.ok(reservaAtualizada);
    }

}