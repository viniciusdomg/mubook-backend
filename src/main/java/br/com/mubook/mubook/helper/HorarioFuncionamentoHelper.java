package br.com.mubook.mubook.helper;

import br.com.mubook.mubook.dto.CriarHorarioFuncionamentoRequest;
import br.com.mubook.mubook.model.HorarioFuncionamento;
import br.com.mubook.mubook.model.TipoQuadra;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class HorarioFuncionamentoHelper {

    public HorarioFuncionamento RequestHorarioFuncinamentoToModel(CriarHorarioFuncionamentoRequest request, TipoQuadra tipoQuadra) {
        HorarioFuncionamento horarioFuncionamento = new HorarioFuncionamento();
        horarioFuncionamento.setDiaSemana(horarioFuncionamento.parseListByString(request.diasDaSemana()));
        horarioFuncionamento.setTipoQuadra(tipoQuadra);
        horarioFuncionamento.setHorarioAbertura(request.abertura() != null && !request.abertura().isEmpty() ?
                LocalTime.parse(request.abertura()) : null);
        horarioFuncionamento.setHorarioFechamento(request.fechamento() != null && !request.fechamento().isEmpty() ?
                LocalTime.parse(request.fechamento()) : null);

        return horarioFuncionamento;
    }
}
