package br.com.mubook.mubook.restcontroller;

import br.com.mubook.mubook.dto.CriarHorarioFuncionamentoRequest;
import br.com.mubook.mubook.dto.PageResponse;
import br.com.mubook.mubook.helper.HorarioFuncionamentoHelper;
import br.com.mubook.mubook.model.HorarioFuncionamento;
import br.com.mubook.mubook.service.HorarioFuncionamentoService;
import br.com.mubook.mubook.service.TipoQuadraService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api/horario-funcionamento/")
@RequiredArgsConstructor
public class HorarioFuncionamentoController {

    private final HorarioFuncionamentoService horarioFuncionamentoService;

    private final TipoQuadraService tipoQuadraService;

    private final HorarioFuncionamentoHelper helper;

    @GetMapping
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<PageResponse<HorarioFuncionamento>> findAll(
            @RequestParam(required = false, defaultValue = "0") int offset,
            @RequestParam(required = false, defaultValue = "20") int limit) {
        try{
            Page<HorarioFuncionamento> page = horarioFuncionamentoService.findAllPageable(offset, limit);
            PageResponse<HorarioFuncionamento> response = helper.PageToPageResponse(page);
            return ResponseEntity.ok(response);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<HorarioFuncionamento> findById(@PathVariable Integer id) {
        HorarioFuncionamento horario = horarioFuncionamentoService.findById(id);
        if (horario == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
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
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        horarioFuncionamentoService.hardDeleteById(id);
        return ResponseEntity.ok("Horário de Funcionamento Deletado");
    }
}
