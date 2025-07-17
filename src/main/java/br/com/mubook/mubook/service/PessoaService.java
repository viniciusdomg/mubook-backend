package br.com.mubook.mubook.service;

import br.com.mubook.mubook.model.Pessoa;

public interface PessoaService extends GenericService<Pessoa, Long>{

    Pessoa findByEmail(String email);
}
