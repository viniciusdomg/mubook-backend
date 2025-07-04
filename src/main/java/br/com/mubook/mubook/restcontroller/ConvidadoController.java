package br.com.mubook.mubook.restcontroller;

import br.com.mubook.mubook.model.Convidado;
import br.com.mubook.mubook.service.ConvidadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gerenciar Convidados.
 * 
 * @RestController define esta classe como um controlador que lida com
 *                 requisições REST.
 * @RequestMapping define o caminho base para todos os endpoints
 *                 ("/api/convidado").
 * @RequiredArgsConstructor cria um construtor com os campos 'final' para
 *                          injeção de dependência.
 */
@RestController
@RequestMapping("/api/convidado")
@RequiredArgsConstructor
public class ConvidadoController {

    private final ConvidadoService convidadoService;

    @GetMapping
    public ResponseEntity<List<Convidado>> findAll() {
        List<Convidado> lista = convidadoService.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Convidado> findById(@PathVariable Long id) {
        Convidado convidado = convidadoService.findById(id);
        if (convidado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convidado);
    }

    @PostMapping
    public ResponseEntity<Convidado> create(@RequestBody Convidado convidado) {
        Convidado created = convidadoService.save(convidado);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Convidado> update(@PathVariable Long id, @RequestBody Convidado convidado) {
        convidado.setId(id);
        Convidado updated = convidadoService.save(convidado);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        convidadoService.hardDeleteById(id);
        return ResponseEntity.noContent().build();
    }
}