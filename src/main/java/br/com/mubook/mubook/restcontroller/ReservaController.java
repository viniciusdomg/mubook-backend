package br.com.mubook.mubook.restcontroller;

import br.com.mubook.mubook.dto.ReservaConvidadoDto;
import br.com.mubook.mubook.dto.ReservaCreateDto;
import br.com.mubook.mubook.dto.ReservaUpdateDto;
import br.com.mubook.mubook.model.Reserva;
import br.com.mubook.mubook.service.HistoricoReservasService;
import br.com.mubook.mubook.service.ReservasDisponiveisService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Controlador REST para gerenciar Reservas.
 * 
 * @RestController combina @Controller e @ResponseBody.
 * @RequestMapping define o caminho base para todos os endpoints deste
 *                 controlador.
 */
@RestController
@RequestMapping("/api/reserva/")
@RequiredArgsConstructor
public class ReservaController {

    private final HistoricoReservasService historicoReservasService;

    private final ReservasDisponiveisService reservasDisponiveisService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'ASSOCIADO')")
    public ResponseEntity<List<Reserva>> reservasDisponiveis(
            @RequestParam(required = false) Long idTipoQuadra, @RequestParam(required = false) LocalDate data,
            @RequestParam(required = false) LocalTime hora) {
        return ResponseEntity.ok(reservasDisponiveisService.findReservasWithFilter(idTipoQuadra, data, hora));
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
    public ResponseEntity<Reserva> create(@Valid @RequestBody ReservaCreateDto reservaDto) {
        Reserva novaReserva = historicoReservasService.criarReserva(reservaDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(novaReserva.getId()).toUri();
        return ResponseEntity.created(uri).body(novaReserva);
    }

    /**
     * Endpoint para cancelar uma reserva.
     */
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

    @PostMapping("/{id}/convidados")
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