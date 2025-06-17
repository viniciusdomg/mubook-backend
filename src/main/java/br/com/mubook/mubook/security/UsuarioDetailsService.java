package br.com.mubook.mubook.security;

import br.com.mubook.mubook.service.UsuarioService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioService service;

    public UsuarioDetailsService(UsuarioService service) {
        this.service = service;
    }

    @Override
    public UsuarioDetails loadUserByUsername(String search) throws BadCredentialsException {
        return service.findByEmailOrCpf(search)
                .map(UsuarioDetails::new)
                .orElseThrow(() -> new BadCredentialsException("Usuário não encontrado"));
    }
}
