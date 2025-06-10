package br.com.mubook.mubook.enums;

import java.util.List;

public enum RoleUser {

    ADMINISTRADOR("Administrador"), ASSOCIADO("Associado");

    private final String nome;

    RoleUser(String nome) {
        this.nome = nome;
    }

    public String getNome() { return nome; }

    public static List<RoleUser> getTiposUsuarios() {
        return List.of(RoleUser.ADMINISTRADOR, RoleUser.ASSOCIADO);
    }
}
