package br.com.mubook.mubook.enums;

import lombok.Getter;

import java.util.List;

@Getter
public enum RoleUser {

    ROLE_ADMINISTRADOR("Administrador"),
    ROLE_ASSOCIADO("Associado");

    private final String descricao;

    RoleUser(String descricao) {
        this.descricao = descricao;
    }

    public static List<RoleUser> getTiposUsuarios() {
        return List.of(RoleUser.ROLE_ADMINISTRADOR, RoleUser.ROLE_ASSOCIADO);
    }

    public String getAuthority() {
        return name();
    }

}
