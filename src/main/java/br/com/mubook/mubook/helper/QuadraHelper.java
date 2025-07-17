package br.com.mubook.mubook.helper;

import br.com.mubook.mubook.dto.QuadraCreateDTO;
import br.com.mubook.mubook.model.Quadra;
import br.com.mubook.mubook.model.TipoQuadra;
import org.springframework.stereotype.Component;


@Component
public class QuadraHelper {

    public Quadra RequestToQuadra(QuadraCreateDTO dto, TipoQuadra tipo){
        Quadra quadra = new Quadra();
        quadra.setNome(dto.nome());
        quadra.setQuantidadeMaxima(dto.quantidadeMaxima());
        quadra.setTipoQuadra(tipo);
        quadra.setFoto_url(dto.foto_url());

        return quadra;
    }
}
