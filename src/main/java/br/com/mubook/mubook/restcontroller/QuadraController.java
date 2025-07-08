package br.com.mubook.mubook.restcontroller;

import br.com.mubook.mubook.dto.QuadraCreateDTO;
import br.com.mubook.mubook.model.Quadra;
import br.com.mubook.mubook.service.QuadraService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/quadra/")
@RequiredArgsConstructor
public class QuadraController {

    private final QuadraService quadraService;

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping
    public ResponseEntity<List<Quadra>> findAll() {
        try {
            List<Quadra> lista = quadraService.findAll();
            return ResponseEntity.ok(lista);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("{id}")
    public ResponseEntity<Quadra> findById(@PathVariable Integer id) {
        try {
            Quadra quadra = quadraService.findById(id);
            if (quadra == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(quadra);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping
    public ResponseEntity<String> create(@RequestBody QuadraCreateDTO quadraDTO) {
        try {
            quadraService.createFromDTO(quadraDTO);
            return ResponseEntity.ok("Quadra criada com sucesso!");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PutMapping("{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody QuadraCreateDTO quadraDTO) {
        try {
          quadraService.updateFromDTO(id, quadraDTO);
          return ResponseEntity.ok("Dados da quadra atualizado com sucesso!");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            quadraService.hardDeleteById(id);
            return ResponseEntity.ok("Quadra deletada com sucesso!");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @DeleteMapping
    public ResponseEntity<String> deleteAll(@Valid @RequestBody Iterable<Integer> ids) {
        try {
            quadraService.hardDeleteAll(ids);
            return ResponseEntity.ok("Quadras deletadas com sucesso!");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
