package br.com.mubook.mubook.restcontroller;

import br.com.mubook.mubook.dto.QuadraCreateDTO;
import br.com.mubook.mubook.exception.BussinesException;
import br.com.mubook.mubook.helper.QuadraHelper;
import br.com.mubook.mubook.model.Quadra;
import br.com.mubook.mubook.service.QuadraService;
import br.com.mubook.mubook.service.TipoQuadraService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/quadra/")
@RequiredArgsConstructor
public class QuadraController {

    private final QuadraService service;

    private final TipoQuadraService tipoService;

    private final QuadraHelper helper;

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping
    public ResponseEntity<Page<Quadra>> findAll(@RequestParam(required = false) Long tipoQuadra,
                                                @RequestParam(required = false, defaultValue = "0") int offset,
                                                @RequestParam(required = false, defaultValue = "0") int limit) {
        try {
            Page<Quadra> page = service.findAllByTipoQuadra(tipoQuadra, offset, limit);
            return ResponseEntity.ok(page);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("{id}")
    public ResponseEntity<Quadra> findById(@PathVariable Integer id) {
        try {
            Quadra quadra = service.findById(id);
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
            Quadra quadra = helper.RequestToQuadra(quadraDTO);
            service.save(quadra);
            return ResponseEntity.ok("Quadra criada com sucesso!");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PutMapping("{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody QuadraCreateDTO dto) {
        try {
            validate(dto);
            Quadra quadra = helper.RequestToQuadra(dto);
            quadra.setTipoQuadra(tipoService.findById(quadra.getTipoQuadra().getId()));
            service.update(id, quadra);
            return ResponseEntity.ok("Dados da quadra atualizado com sucesso!");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            service.hardDeleteById(id);
            return ResponseEntity.ok("Quadra deletada com sucesso!");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @DeleteMapping("all")
    public ResponseEntity<String> deleteAll(@Valid @RequestBody Iterable<Integer> ids) {
        try {
            service.hardDeleteAll(ids);
            return ResponseEntity.ok("Quadras deletadas com sucesso!");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public void validate(QuadraCreateDTO dto){
        if(dto.tipoQuadraId() == null || dto.tipoQuadraId() == 0){
            throw new BussinesException("preencher campo Tipo de Quadra");
        }
    }
}
