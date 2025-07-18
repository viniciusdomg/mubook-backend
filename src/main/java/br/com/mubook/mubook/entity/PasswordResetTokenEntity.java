package br.com.mubook.mubook.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "mubook", name = "password_reset_token")
public class PasswordResetTokenEntity {

    private static final int EXPIRATION_HOURS = 1; // Token expira em 1 hora

    @Id
    @GeneratedValue(generator = "gen_password_reset_token_id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "gen_password_reset_token_id", schema = "mubook", sequenceName = "seq_password_reset_token_id", allocationSize = 1)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    @OneToOne(targetEntity = UsuarioEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "usuario_id")
    private UsuarioEntity usuario;

    @Column(nullable = false, name = "expiry_date")
    private LocalDateTime expiryDate;

    /**
     * Construtor para facilitar a criação de um token para um usuário.
     */
    public PasswordResetTokenEntity(String token, UsuarioEntity usuario) {
        this.token = token;
        this.usuario = usuario;
        this.expiryDate = LocalDateTime.now().plusHours(EXPIRATION_HOURS);
    }

    /**
     * Verifica se o token expirou.
     * @return true se o token estiver expirado, false caso contrário.
     */
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(this.expiryDate);
    }
}