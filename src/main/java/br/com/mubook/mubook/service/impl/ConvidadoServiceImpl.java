package br.com.mubook.mubook.service.impl;

import br.com.mubook.mubook.dto.ConvidadoCreateDto;
import br.com.mubook.mubook.entity.ConvidadoEntity;
import br.com.mubook.mubook.entity.PessoaEntity;
import br.com.mubook.mubook.jparepository.ConvidadoJpaRepository;
import br.com.mubook.mubook.jparepository.PessoaJpaRepository;
import br.com.mubook.mubook.mapper.ConvidadoEntityMapper;
import br.com.mubook.mubook.model.Convidado;
import br.com.mubook.mubook.model.Endereco;
import br.com.mubook.mubook.service.ConvidadoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 * Implementação da camada de serviço para Convidado.
 * A anotação @Service a registra como um componente de serviço do Spring.
 */
@Service
public class ConvidadoServiceImpl extends GenericServiceImpl<Convidado, Long, ConvidadoEntity>
        implements ConvidadoService {

    private final ConvidadoJpaRepository repository;
    private final ConvidadoEntityMapper mapper;
    private final PessoaJpaRepository pessoaRepository; // Nova dependência

    // Atualize o construtor para injetar o novo repositório
    public ConvidadoServiceImpl(ConvidadoJpaRepository repository, ConvidadoEntityMapper mapper, PessoaJpaRepository pessoaRepository) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    @Transactional // Garante que ambas as operações (salvar pessoa e convidado) sejam bem-sucedidas ou nenhuma delas.
    public Convidado criarConvidadoComPessoa(ConvidadoCreateDto dto) {
        // 1. Criar e preencher a entidade Pessoa a partir do DTO
        PessoaEntity novaPessoa = new PessoaEntity();
        novaPessoa.setNome(dto.getNome());
        novaPessoa.setCpf(dto.getCpf());
        novaPessoa.setEmail(dto.getEmail());
        novaPessoa.setTelefone(dto.getTelefone());
        novaPessoa.setDataNascimento(dto.getDataNascimento());
        novaPessoa.setGenero(dto.getGenero());
        novaPessoa.setAtivo(true); // Definimos como ativo por padrão

        // 2. Criar e preencher o Endereço embutido
        Endereco endereco = new Endereco();
        endereco.setRua(dto.getRua());
        endereco.setNumero(dto.getNumero());
        endereco.setBairro(dto.getBairro());
        endereco.setComplemento(dto.getComplemento());
        endereco.setCep(dto.getCep());
        novaPessoa.setEndereco(endereco);

        // 3. Salvar a PessoaEntity no banco de dados. O JPA preencherá o ID.
        PessoaEntity pessoaSalva = pessoaRepository.save(novaPessoa);

        // 4. Criar a entidade Convidado
        ConvidadoEntity novoConvidado = new ConvidadoEntity();

        // 5. Associar a Pessoa recém-criada ao Convidado
        novoConvidado.setPessoa(pessoaSalva);

        // A reserva pode ser associada em outro momento, então 'reserva_id' ficará nulo por enquanto.

        // 6. Salvar a ConvidadoEntity. O JPA preencherá o ID.
        ConvidadoEntity convidadoSalvo = repository.save(novoConvidado);

        // 7. Mapear a entidade salva para o modelo de retorno
        return mapper.toModel(convidadoSalvo);
    }
}
