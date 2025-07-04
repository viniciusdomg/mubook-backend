package br.com.mubook.mubook.restcontroller;

import br.com.mubook.mubook.dto.QuadraCreateDTO;
import br.com.mubook.mubook.model.Quadra;
import br.com.mubook.mubook.service.QuadraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quadra")
@RequiredArgsConstructor
public class QuadraController {

    private final QuadraService quadraService;

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping
    public ResponseEntity<List<Quadra>> findAll() {
        List<Quadra> lista = quadraService.findAll();
        return ResponseEntity.ok(lista);
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/{id}")
    public ResponseEntity<Quadra> findById(@PathVariable Integer id) {
        Quadra quadra = quadraService.findById(id);
        if (quadra == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(quadra);
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping
    public ResponseEntity<Quadra> create(@RequestBody QuadraCreateDTO quadraDTO) {
        Quadra created = quadraService.createFromDTO(quadraDTO);
        return ResponseEntity.ok(created);
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PutMapping("/{id}")
    public ResponseEntity<Quadra> update(@PathVariable Integer id, @RequestBody QuadraCreateDTO quadraDTO) {
        Quadra updated = quadraService.updateFromDTO(id, quadraDTO);
        return ResponseEntity.ok(updated);
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        quadraService.hardDeleteById(id);
        return ResponseEntity.noContent().build();
    }
}
