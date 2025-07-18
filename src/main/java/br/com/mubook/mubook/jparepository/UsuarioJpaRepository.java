package br.com.mubook.mubook.jparepository;

import br.com.mubook.mubook.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioJpaRepository extends GenericRepository<UsuarioEntity, Long>, JpaSpecificationExecutor<UsuarioEntity> {

    @Query("select u " +
            "from UsuarioEntity u where u.pessoa.email = :search OR u.pessoa.cpf = :search")
    Optional<UsuarioEntity> findByEmailOrCpf(String search);

    @Query("select case when count(p) > 0 then true else false end " +
            "from PessoaEntity p where p.email = :email OR p.cpf = :cpf")
    Boolean existsByEmailOrCpf(String email, String cpf);

    @Query("select u " +
            "from UsuarioEntity u where u.pessoa.id = :idPessoa")
    UsuarioEntity findByPessoa(Long idPessoa);

    @Query("select count(u) from UsuarioEntity u where u.roleUser = 'ROLE_ADMINISTRADOR'")
    Long countByRoleAdministrador();

    @Query("select count(u) from UsuarioEntity u where u.roleUser = 'ROLE_SOCIO'")
    Long countByRoleSocio();
}
