package br.com.mubook.mubook.helper;

import br.com.mubook.mubook.dto.PageResponse;
import br.com.mubook.mubook.model.Reserva;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class ReservasDisponiveisHelper {

    public PageResponse<Reserva> PageToPageResponse(Page<Reserva> page){
        return new PageResponse<>(page.getContent(), page.getTotalElements(), page.getTotalPages(), page.getSize(),
                page.getNumber(), page.isFirst(), page.isLast(), page.isEmpty());
    }
}
