package br.com.mubook.mubook.enums;

import java.util.List;

public enum TipoUsuario {

    ADMINISTRADOR("Administrador"), ASSOCIADO("Associado");

    private final String nome;

    TipoUsuario(String nome) {
        this.nome = nome;
    }

    public String getNome() { return nome; }

    public static List<TipoUsuario> getTiposUsuarios() {
        return List.of(TipoUsuario.ADMINISTRADOR, TipoUsuario.ASSOCIADO);
    }
}
