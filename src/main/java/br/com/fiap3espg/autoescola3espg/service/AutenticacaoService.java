package br.com.fiap3espg.autoescola3espg.service;

import br.com.fiap3espg.autoescola3espg.domain.usuario.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutenticacaoService implements UserDetailsService {
    private final UsuarioRepository repository;


    private PasswordEncoder passwordEncoder; // Injetando o encriptador

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var usuario = repository.findByLogin(username);

        System.out.println("--- TESTE DE LOGIN ---");

        // 1. A senha bate?

        // 2. Os métodos booleanos estão todos TRUE mesmo?
        System.out.println("CONTA NAO EXPIRADA? " + usuario.isAccountNonExpired());
        System.out.println("CONTA NAO BLOQUEADA? " + usuario.isAccountNonLocked());
        System.out.println("CREDENCIAIS NAO EXPIRADAS? " + usuario.isCredentialsNonExpired());
        System.out.println("CONTA ATIVA? " + usuario.isEnabled());
//        boolean senhaBate = passwordEncoder.matches("123456", usuario.getPassword());
//        System.out.println("SENHA BATE COM 123456? " + senhaBate);

        return usuario;
    }

}