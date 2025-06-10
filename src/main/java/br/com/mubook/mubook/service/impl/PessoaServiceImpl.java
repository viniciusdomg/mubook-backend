package br.com.mubook.mubook.service.impl;

import br.com.mubook.mubook.model.Pessoa;
import br.com.mubook.mubook.repository.PessoaRepository;
import br.com.mubook.mubook.service.PessoaService;

public class PessoaServiceImpl extends GenericServiceImpl<Pessoa, Long, PessoaRepository> implements PessoaService {

    public PessoaServiceImpl(PessoaRepository repository) {
        super(repository);
    }
}
