package br.com.mubook.mubook.helper;

import br.com.mubook.mubook.dto.QuadraCreateDTO;
import br.com.mubook.mubook.model.Quadra;
import org.springframework.stereotype.Component;


@Component
public class QuadraHelper {

    public Quadra RequestToQuadra(QuadraCreateDTO dto){
        Quadra quadra = new Quadra();
        quadra.setNome(dto.nome());
        quadra.setQuantidadeMaxima(dto.quantidadeMaxima());
        quadra.setFoto_url(dto.foto_url());

        return quadra;
    }
}
