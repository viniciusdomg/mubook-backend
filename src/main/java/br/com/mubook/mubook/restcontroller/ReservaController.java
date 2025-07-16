package br.com.mubook.mubook.restcontroller;

import br.com.mubook.mubook.dto.ReservaConvidadoDto;
import br.com.mubook.mubook.dto.ReservaCreateDto;
import br.com.mubook.mubook.dto.ReservaUpdateDto;
import br.com.mubook.mubook.model.Reserva;
import br.com.mubook.mubook.service.ReservaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
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

    private final ReservaService reservaService;

    @GetMapping
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<List<Reserva>> findAll() {
        return ResponseEntity.ok(reservaService.findAll());
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'ASSOCIADO')")
    public ResponseEntity<Reserva> findById(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.findById(id));
    }

    /**
     * Endpoint para criar uma nova reserva.
     * Recebe um DTO com os IDs de usuário, quadra e convidados.
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'ASSOCIADO')")
    public ResponseEntity<Reserva> create(@Valid @RequestBody ReservaCreateDto reservaDto) {
        Reserva novaReserva = reservaService.criarReserva(reservaDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(novaReserva.getId()).toUri();
        return ResponseEntity.created(uri).body(novaReserva);
    }

    /**
     * Endpoint para cancelar uma reserva.
     */
    @PutMapping("{id}/cancelar")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','ASSOCIADO')")
    public ResponseEntity<Reserva> cancelar(@PathVariable Long id) {
        Reserva reservaCancelada = reservaService.cancelarReserva(id);
        return ResponseEntity.ok(reservaCancelada);
    }

    @PutMapping("/{id}/finalizar")
    public ResponseEntity<Reserva> finalizar(@PathVariable Long id) {
        Reserva reservaFinalizada = reservaService.finalizarReserva(id);
        return ResponseEntity.ok(reservaFinalizada);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'ASSOCIADO')")
    public ResponseEntity<Reserva> editar(@PathVariable Long id, @Valid @RequestBody ReservaUpdateDto reservaDto) {
        Reserva reservaEditada = reservaService.editarReserva(id, reservaDto);
        return ResponseEntity.ok(reservaEditada);
    }

    @PostMapping("{id}/convidados")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'ASSOCIADO')")
    public ResponseEntity<Reserva> adicionarConvidados(@PathVariable Long id, @Valid @RequestBody ReservaConvidadoDto dto) {
        Reserva reservaAtualizada = reservaService.adicionarConvidados(id, dto.getConvidadosIds());
        return ResponseEntity.ok(reservaAtualizada);
    }

    @DeleteMapping("{id}/convidados")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'ASSOCIADO')")
    public ResponseEntity<Reserva> removerConvidados(@PathVariable Long id, @Valid @RequestBody ReservaConvidadoDto dto) {
        Reserva reservaAtualizada = reservaService.removerConvidados(id, dto.getConvidadosIds());
        return ResponseEntity.ok(reservaAtualizada);
    }

}