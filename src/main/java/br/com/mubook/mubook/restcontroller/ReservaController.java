package br.com.mubook.mubook.restcontroller;

import br.com.mubook.mubook.model.Reserva;
import br.com.mubook.mubook.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
@RequestMapping("/api/reserva")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;

    @GetMapping
    public ResponseEntity<List<Reserva>> findAll() {
        List<Reserva> lista = reservaService.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> findById(@PathVariable Long id) {
        Reserva reserva = reservaService.findById(id);
        // O findById do serviço já deve tratar o caso de não encontrar,
        // mas uma verificação dupla não é ruim.
        if (reserva == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reserva);
    }

    @PostMapping
    public ResponseEntity<Reserva> create(@RequestBody Reserva reserva) {
        Reserva created = reservaService.save(reserva);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> update(@PathVariable Long id, @RequestBody Reserva reserva) {
        reserva.setId(id);
        Reserva updated = reservaService.save(reserva);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reservaService.hardDeleteById(id);
        return ResponseEntity.noContent().build();
    }

    // --- Endpoints Específicos ---

    /**
     * Endpoint para cancelar uma reserva.
     * Usamos o método PATCH pois é uma atualização parcial no recurso.
     */
    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Reserva> cancelar(@PathVariable Long id) {
        Reserva reservaCancelada = reservaService.cancelarReserva(id);
        return ResponseEntity.ok(reservaCancelada);
    }

    /**
     * DTO (Data Transfer Object) simples para receber a nova data da remarcação.
     */
    record RemarcarRequest(LocalDateTime novaDataHora) {
    }

    /**
     * Endpoint para remarcar uma reserva.
     * Usamos PATCH também para uma atualização parcial.
     * 
     * @param request O corpo da requisição contendo a nova data e hora.
     */
    @PatchMapping("/{id}/remarcar")
    public ResponseEntity<Reserva> remarcar(@PathVariable Long id, @RequestBody RemarcarRequest request) {
        Reserva reservaRemarcada = reservaService.remarcarReserva(id, request.novaDataHora());
        return ResponseEntity.ok(reservaRemarcada);
    }
}