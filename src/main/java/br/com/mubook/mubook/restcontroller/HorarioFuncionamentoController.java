package br.com.mubook.mubook.restcontroller;

import br.com.mubook.mubook.dto.CriarHorarioFuncionamentoRequest;
import br.com.mubook.mubook.helper.HorarioFuncionamentoHelper;
import br.com.mubook.mubook.model.HorarioFuncionamento;
import br.com.mubook.mubook.service.HorarioFuncionamentoService;
import br.com.mubook.mubook.service.TipoQuadraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/horario-funcionamento/")
@RequiredArgsConstructor
public class HorarioFuncionamentoController {

    private final HorarioFuncionamentoService horarioFuncionamentoService;

    private final TipoQuadraService tipoQuadraService;

    private final HorarioFuncionamentoHelper helper;

    @GetMapping
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<List<HorarioFuncionamento>> findAll() {
        List<HorarioFuncionamento> lista = horarioFuncionamentoService.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<HorarioFuncionamento> findById(@PathVariable Integer id) {
        HorarioFuncionamento horario = horarioFuncionamentoService.findById(id);
        if (horario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(horario);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<String> create(@RequestBody CriarHorarioFuncionamentoRequest horario) {
        try {
            var tipoQuadra = tipoQuadraService.findById(horario.tipoQuadraId());
            var model = helper.RequestHorarioFuncinamentoToModel(horario, tipoQuadra);
            horarioFuncionamentoService.save(model);
            return ResponseEntity.ok("Novo Horario de Funcionamento Criado");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<HorarioFuncionamento> update(@PathVariable Integer id,
            @RequestBody HorarioFuncionamento horario) {
        horario.setId(id);
        HorarioFuncionamento updated = horarioFuncionamentoService.save(horario);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        horarioFuncionamentoService.hardDeleteById(id);
        return ResponseEntity.noContent().build();
    }
}
