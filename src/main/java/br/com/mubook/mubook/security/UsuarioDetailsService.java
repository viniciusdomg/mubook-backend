package br.com.mubook.mubook.security;

import br.com.mubook.mubook.exception.AuthenticationInvalidException;
import br.com.mubook.mubook.exception.UsuarioNotFoundException;
import br.com.mubook.mubook.model.Usuario;
import br.com.mubook.mubook.service.UsuarioService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioService usuarioService;

    public UsuarioDetailsService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @Override
    public UsuarioDetails loadUserByUsername(String search) throws UsernameNotFoundException {
        return usuarioService.findByEmailOrCpf(search)
                .map(UsuarioDetails::new)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuário não encontrado"));
    }
}
