package br.com.mubook.mubook.restcontroller;

import br.com.mubook.mubook.model.TipoQuadra;
import br.com.mubook.mubook.service.TipoQuadraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipoQuadra/")
@RequiredArgsConstructor
public class TipoQuadraController {

    private final TipoQuadraService tipoQuadraService;

    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'ASSOCIADO')")
    @GetMapping
    public ResponseEntity<List<TipoQuadra>> findAll() {
        List<TipoQuadra> lista = tipoQuadraService.findAll();
        return ResponseEntity.ok(lista);
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("{id}")
    public ResponseEntity<TipoQuadra> findById(@PathVariable Integer id) {
        TipoQuadra tipoQuadra = tipoQuadraService.findById(id);
        if (tipoQuadra == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tipoQuadra);
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping
    public ResponseEntity<String> create(@RequestBody TipoQuadra tipoQuadra) {
        tipoQuadraService.save(tipoQuadra);
        return ResponseEntity.ok("Tipo de Quadra criado com sucesso!");
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PutMapping("{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody TipoQuadra tipoQuadra) {
        tipoQuadra.setId(id);
        tipoQuadraService.save(tipoQuadra);
        return ResponseEntity.ok("Tipo de Quadra atualizado com sucesso!");
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        tipoQuadraService.hardDeleteById(id);
        return ResponseEntity.noContent().build();
    }
}
