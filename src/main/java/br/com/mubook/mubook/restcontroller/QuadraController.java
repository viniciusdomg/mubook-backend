package br.com.mubook.mubook.restcontroller;

import br.com.mubook.mubook.model.Quadra;
import br.com.mubook.mubook.service.QuadraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quadra")
@RequiredArgsConstructor
public class QuadraController {

    private final QuadraService quadraService;

    @GetMapping
    public ResponseEntity<List<Quadra>> findAll() {
        List<Quadra> lista = quadraService.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quadra> findById(@PathVariable Integer id) {
        Quadra quadra = quadraService.findById(id);
        if (quadra == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(quadra);
    }

    @PostMapping
    public ResponseEntity<Quadra> create(@RequestBody Quadra quadra) {
        Quadra created = quadraService.save(quadra);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quadra> update(@PathVariable Integer id, @RequestBody Quadra quadra) {
        quadra.setId(id);
        Quadra updated = quadraService.save(quadra);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        quadraService.hardDeleteById(id);
        return ResponseEntity.noContent().build();
    }
}
