package br.com.mubook.mubook.timers;

import br.com.mubook.mubook.enums.StatusReserva;
import br.com.mubook.mubook.model.HorarioFuncionamento;
import br.com.mubook.mubook.model.Quadra;
import br.com.mubook.mubook.model.Reserva;
import br.com.mubook.mubook.model.TipoQuadra;
import br.com.mubook.mubook.service.HorarioFuncionamentoService;
import br.com.mubook.mubook.service.QuadraService;
import br.com.mubook.mubook.service.ReservasDisponiveisService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JobCriaAndDeletaReservasDisponiveis {

    private final ReservasDisponiveisService reservaService;

    private final QuadraService quadraService;

    private final HorarioFuncionamentoService horarioFuncionamentoService;

 //   @Scheduled(cron = "0 0 0 * * *")
    public void removerReservasAntigas() {
        LocalDateTime agora = LocalDateTime.now();
        reservaService.deleteAllByReservasDataBefore(agora.toLocalDate().atStartOfDay());
    }

//    @Scheduled(cron = "0 5 0 * * *") // 00:05
    public void criarReservasDisponiveis() {
        LocalDate hoje = LocalDate.now();
        LocalDate dataLimite = hoje.plusDays(30);
        List<Quadra> quadras = quadraService.findAll();

        for (LocalDate dia = hoje; !dia.isAfter(dataLimite); dia = dia.plusDays(1)) {
            DayOfWeek diaSemana = dia.getDayOfWeek();
            ArrayList<Reserva> reservas = new ArrayList<>();

            for (Quadra quadra : quadras) {
                TipoQuadra tipo = quadra.getTipoQuadra();

                Optional<HorarioFuncionamento> horarioOpt =
                        horarioFuncionamentoService.findByTipoQuadraEntityAndDiaSemana(tipo, diaSemana);

                if (horarioOpt.isEmpty()) {
                    continue;
                }

                HorarioFuncionamento horario = horarioOpt.get();
                LocalTime abertura = horario.getHorarioAbertura();
                LocalTime fechamento = horario.getHorarioFechamento();

                for (LocalTime hora = abertura; hora.isBefore(fechamento); hora = hora.plusHours(1)) {
                    LocalDateTime dataHora = LocalDateTime.of(dia, hora);
                    boolean jaExiste = reservaService.existsByQuadraAndDataHora(quadra, dataHora);

                    if (!jaExiste) {
                        Reserva nova = new Reserva();
                        nova.setQuadra(quadra);
                        nova.setDataHora(dataHora);
                        nova.setStatus(StatusReserva.DISPONIVEL);
                        reservas.add(nova);
                    }
                }
            }
            if (!reservas.isEmpty()){
                reservaService.saveAll(reservas);
            }
        }
    }
}
