package br.com.mubook.mubook.service.impl;

import br.com.mubook.mubook.dto.QuadraCreateDTO;
import br.com.mubook.mubook.entity.QuadraEntity;
import br.com.mubook.mubook.entity.TipoQuadraEntity;
import br.com.mubook.mubook.jparepository.QuadraJpaRepository;
import br.com.mubook.mubook.jparepository.TipoQuadraJpaRepository;
import br.com.mubook.mubook.mapper.QuadraEntityMapper;
import br.com.mubook.mubook.model.Quadra;
import br.com.mubook.mubook.service.QuadraService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class QuadraServiceImpl extends GenericServiceImpl<Quadra, Integer, QuadraEntity> implements QuadraService {

    private final QuadraJpaRepository repository;
    private final QuadraEntityMapper mapper;
    private final TipoQuadraJpaRepository tipoQuadraRepository;

    public QuadraServiceImpl(QuadraJpaRepository repository, QuadraEntityMapper mapper, TipoQuadraJpaRepository tipoQuadraRepository) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
        this.tipoQuadraRepository = tipoQuadraRepository;
    }

    @Override
    public Quadra updateFromDTO(Integer id, QuadraCreateDTO dto) {
        // 1. Busca a entidade Quadra existente pelo ID. Se não encontrar, lança uma exceção.
        QuadraEntity quadraEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Quadra com ID " + id + " não encontrada."));

        // 2. Atualiza os dados da entidade com os valores do DTO.
        quadraEntity.setNome(dto.getNome());
        quadraEntity.setQuantidadeMaxima(dto.getQuantidadeMaxima());
        quadraEntity.setFoto_url(dto.getFoto_url());

        // 3. Busca a entidade TipoQuadra completa, assim como fizemos no create.
        TipoQuadraEntity tipoQuadraEntity = tipoQuadraRepository.findById(dto.getTipoQuadraId())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de Quadra com ID " + dto.getTipoQuadraId() + " não encontrado."));

        // 4. Associa o novo tipo de quadra à quadra que está a ser atualizada.
        quadraEntity.setTipoQuadra(tipoQuadraEntity);

        // 5. Salva a entidade atualizada no banco de dados.
        QuadraEntity updatedEntity = repository.save(quadraEntity);

        // 6. Converte a entidade atualizada de volta para o modelo e retorna.
        return mapper.toModel(updatedEntity);
    }


    @Override
    public Quadra createFromDTO(QuadraCreateDTO dto) {
        // 1. Converte os dados básicos do DTO para a entidade Quadra
        QuadraEntity quadraEntity = new QuadraEntity();
        quadraEntity.setNome(dto.getNome());
        quadraEntity.setQuantidadeMaxima(dto.getQuantidadeMaxima());
        quadraEntity.setFoto_url(dto.getFoto_url());

        // 2. Busca a entidade TipoQuadra completa usando o ID do DTO
        TipoQuadraEntity tipoQuadraEntity = tipoQuadraRepository.findById(dto.getTipoQuadraId())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de Quadra com ID " + dto.getTipoQuadraId() + " não encontrado."));

        // 3. Associa o tipo de quadra encontrado à quadra que será salva
        quadraEntity.setTipoQuadra(tipoQuadraEntity);

        // 4. Salva a entidade Quadra completa no banco de dados
        QuadraEntity savedEntity = repository.save(quadraEntity);

        // 5. Converte a entidade salva de volta para o modelo e retorna
        return mapper.toModel(savedEntity);
    }
}
