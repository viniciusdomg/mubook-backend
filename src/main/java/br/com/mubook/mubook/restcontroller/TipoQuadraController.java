package br.com.mubook.mubook.restcontroller;

import br.com.mubook.mubook.model.TipoQuadra;
import br.com.mubook.mubook.service.TipoQuadraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipoQuadra")
@RequiredArgsConstructor
public class TipoQuadraController {

    private final TipoQuadraService tipoQuadraService;

    @GetMapping
    public ResponseEntity<List<TipoQuadra>> findAll() {
        List<TipoQuadra> lista = tipoQuadraService.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoQuadra> findById(@PathVariable Integer id) {
        TipoQuadra tipoQuadra = tipoQuadraService.findById(id);
        if (tipoQuadra == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tipoQuadra);
    }

    @PostMapping
    public ResponseEntity<TipoQuadra> create(@RequestBody TipoQuadra tipoQuadra) {
        TipoQuadra created = tipoQuadraService.save(tipoQuadra);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoQuadra> update(@PathVariable Integer id, @RequestBody TipoQuadra tipoQuadra) {
        tipoQuadra.setId(id);
        TipoQuadra updated = tipoQuadraService.save(tipoQuadra);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        tipoQuadraService.hardDeleteById(id);
        return ResponseEntity.noContent().build();
    }
}
