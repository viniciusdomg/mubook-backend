package br.com.mubook.mubook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HorarioFuncionamento {
    private Integer id;
    private String diaSemana;
    private LocalTime horarioAbertura;
    private LocalTime horarioFechamento;
    private TipoQuadra tipoQuadra;

    public String parseListByString(List<String> string){
        return String.join(".", string);
    }

    public boolean contemDia(DayOfWeek dia) {
        return List.of(diaSemana.split("\\.")).contains(dia.name());
    }

//    public boolean validarHorario(LocalDateTime horario) {
//        DayOfWeek dia = horario.getDayOfWeek();
//        LocalTime hora = horario.toLocalTime();
//        return dia.equals(diaSemana)
//                && (hora.equals(horarioAbertura) || hora.equals(horarioFechamento)
//                        || (hora.isAfter(horarioAbertura) && hora.isBefore(horarioFechamento)));
//    }
}