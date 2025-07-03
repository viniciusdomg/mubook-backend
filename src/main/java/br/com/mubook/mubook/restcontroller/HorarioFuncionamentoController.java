package br.com.mubook.mubook.restcontroller;

import br.com.mubook.mubook.model.HorarioFuncionamento;
import br.com.mubook.mubook.service.HorarioFuncionamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/horario-funcionamento")
@RequiredArgsConstructor
public class HorarioFuncionamentoController {

    private final HorarioFuncionamentoService horarioFuncionamentoService;

    @GetMapping
    public ResponseEntity<List<HorarioFuncionamento>> findAll() {
        List<HorarioFuncionamento> lista = horarioFuncionamentoService.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HorarioFuncionamento> findById(@PathVariable Integer id) {
        HorarioFuncionamento horario = horarioFuncionamentoService.findById(id);
        if (horario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(horario);
    }

    @PostMapping
    public ResponseEntity<HorarioFuncionamento> create(@RequestBody HorarioFuncionamento horario) {
        HorarioFuncionamento created = horarioFuncionamentoService.save(horario);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HorarioFuncionamento> update(@PathVariable Integer id,
            @RequestBody HorarioFuncionamento horario) {
        horario.setId(id);
        HorarioFuncionamento updated = horarioFuncionamentoService.save(horario);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        horarioFuncionamentoService.hardDeleteById(id);
        return ResponseEntity.noContent().build();
    }
}
