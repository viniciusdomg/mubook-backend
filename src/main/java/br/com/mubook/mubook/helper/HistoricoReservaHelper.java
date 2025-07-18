package br.com.mubook.mubook.helper;

import br.com.mubook.mubook.dto.PageResponse;
import br.com.mubook.mubook.dto.ReservaCreateDto;
import br.com.mubook.mubook.enums.StatusReserva;
import br.com.mubook.mubook.exception.BussinesException;
import br.com.mubook.mubook.model.*;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HistoricoReservaHelper {

    public Reserva RequestToModel(ReservaCreateDto dto, Quadra quadra, Usuario usuario){
        validate(quadra, usuario);

        List<Convidado> convidados = dto.convidados().stream().map( convidado -> {
            Pessoa pessoa = new Pessoa();
            pessoa.setNome(convidado.nome());
            pessoa.setCpf(convidado.cpf());
            pessoa.setEmail(convidado.email());

            Convidado model = new Convidado();
            model.setPessoa(pessoa);
            return model;
        }).toList();

        Reserva historicoReserva = new Reserva();
        historicoReserva.setDataHora(dto.dataHora());
        historicoReserva.setQuadra(quadra);
        historicoReserva.setUsuario(usuario);
        historicoReserva.setStatus(StatusReserva.CONFIRMADA);
        historicoReserva.setConvidados(convidados);

        return historicoReserva;
    }

    public PageResponse<Reserva> PageToPageResponse(Page<Reserva> page){
        return new PageResponse<>(page.getContent(), page.getTotalElements(), page.getTotalPages(), page.getSize(),
                page.getNumber(), page.isFirst(), page.isLast(), page.isEmpty());
    }

    private void validate(Quadra quadra, Usuario usuario){
        if(quadra == null) throw new BussinesException("Quadra não pode ser nulo");
        if(usuario == null) throw new BussinesException("Usuário não poder ser nulo");
    }
}
