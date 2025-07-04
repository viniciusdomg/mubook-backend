package br.com.mubook.mubook.enums;

import java.util.List;

public enum RoleUser {

    ROLE_ADMINISTRADOR,
    ROLE_ASSOCIADO;

    /**
     * Retorna todas as roles disponíveis no sistema.
     */
    public static List<RoleUser> getTiposUsuarios() {
        return List.of(RoleUser.ROLE_ADMINISTRADOR, RoleUser.ROLE_ASSOCIADO);
    }

    public String getAuthority() {
        return name();
    }
}
