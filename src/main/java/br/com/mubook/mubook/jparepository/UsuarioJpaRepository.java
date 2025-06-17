package br.com.mubook.mubook.jparepository;

import br.com.mubook.mubook.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioJpaRepository extends GenericRepository<UsuarioEntity, Long> {

    @Query("from UsuarioEntity u where u.pessoa.email = :search OR u.pessoa.cpf = :search")
    Optional<UsuarioEntity> findByEmailOrCpf(String search);

}
