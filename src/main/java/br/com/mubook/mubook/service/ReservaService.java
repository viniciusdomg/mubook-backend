package br.com.mubook.mubook.service;

import br.com.mubook.mubook.model.Reserva;
import java.time.LocalDateTime;

/**
 * Interface de serviço para a entidade Reserva.
 * Estende a GenericService para obter os métodos CRUD padrão.
 * Adiciona métodos específicos para as regras de negócio de Reserva.
 */
public interface ReservaService extends GenericService<Reserva, Long> {

    /**
     * Cancela uma reserva existente.
     * 
     * @param id O ID da reserva a ser cancelada.
     * @return A reserva com o status atualizado para CANCELADA.
     */
    Reserva cancelarReserva(Long id);

    /**
     * Remarca uma reserva para uma nova data e hora.
     * 
     * @param id          O ID da reserva a ser remarcada.
     * @param novoHorario A nova data e hora da reserva.
     * @return A reserva com a data e o status atualizados.
     */
    Reserva remarcarReserva(Long id, LocalDateTime novoHorario);
}