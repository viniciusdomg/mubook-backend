package br.com.mubook.mubook.jparepository;

import br.com.mubook.mubook.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioJpaRepository extends GenericRepository<UsuarioEntity, Long> {

    @Query("select u " +
            "from UsuarioEntity u where u.pessoa.email = :search OR u.pessoa.cpf = :search")
    Optional<UsuarioEntity> findByEmailOrCpf(String search);

    @Query("select case when count(p) > 0 then true else false end " +
            "from PessoaEntity p where p.email = :email OR p.cpf = :cpf")
    Boolean existsByEmailOrCpf(String email, String cpf);

}
