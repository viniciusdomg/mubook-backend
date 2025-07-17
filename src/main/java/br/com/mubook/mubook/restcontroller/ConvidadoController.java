package br.com.mubook.mubook.restcontroller;

import br.com.mubook.mubook.dto.ConvidadoCreateDto;
import br.com.mubook.mubook.model.Convidado;
import br.com.mubook.mubook.service.ConvidadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Controlador REST para gerenciar Convidados.
 * Fornece endpoints para listar, buscar, criar e deletar convidados.
 * A criação de um convidado implica também na criação de uma nova Pessoa.
 *
 * @RestController Define esta classe como um controlador que lida com requisições REST.
 * @RequestMapping Define o caminho base para todos os endpoints ("/api/convidado").
 * @RequiredArgsConstructor Cria um construtor com os campos 'final' para injeção de dependência do Spring.
 */
@RestController
@RequestMapping("/api/convidado")
@RequiredArgsConstructor
public class ConvidadoController {

    private final ConvidadoService convidadoService;

    /**
     * Endpoint para listar todos os convidados cadastrados.
     * @return Uma resposta HTTP 200 (OK) com a lista de convidados no corpo.
     */
    @GetMapping
    public ResponseEntity<List<Convidado>> findAll() {
        List<Convidado> lista = convidadoService.findAll();
        return ResponseEntity.ok(lista);
    }

    /**
     * Endpoint para buscar um convidado específico pelo seu ID.
     * @param id O ID do convidado a ser buscado.
     * @return Uma resposta HTTP 200 (OK) com o convidado encontrado ou 404 (Not Found) se não existir.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Convidado> findById(@PathVariable Long id) {
        Convidado convidado = convidadoService.findById(id);
        if (convidado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convidado);
    }

    /**
     * Endpoint para criar um novo convidado.
     * Este método recebe os dados de uma pessoa, cria a entidade Pessoa e, em seguida,
     * cria a entidade Convidado associada a ela.
     *
     * @param convidadoDto O DTO com os dados para criar a nova pessoa/convidado.
     * @return Uma resposta HTTP 201 (Created) com o convidado criado no corpo e a URL
     * para o novo recurso no cabeçalho 'Location'.
     */
    @PostMapping
    public ResponseEntity<Convidado> create(@Valid @RequestBody ConvidadoCreateDto convidadoDto) {
        // Chama o método de serviço especializado que criamos
        Convidado novoConvidado = convidadoService.criarConvidadoComPessoa(convidadoDto);

        // Cria a URI para o recurso recém-criado (ex: /api/convidado/123)
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(novoConvidado.getId()).toUri();

        // Retorna a resposta 201 Created com a URI e o objeto criado
        return ResponseEntity.created(uri).body(novoConvidado);
    }

    /**
     * Endpoint para deletar um convidado pelo seu ID.
     * Esta operação realiza um 'hard delete', removendo o registro do banco de dados.
     *
     * @param id O ID do convidado a ser deletado.
     * @return Uma resposta HTTP 204 (No Content) indicando sucesso na remoção.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        convidadoService.hardDeleteById(id);
        return ResponseEntity.noContent().build();
    }
}