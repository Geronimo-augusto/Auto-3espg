package br.com.fiap3espg.autoescola3espg.controller;

import br.com.fiap3espg.autoescola3espg.domain.usuario.DadosLogin;
import br.com.fiap3espg.autoescola3espg.infra.security.DadosTokenJWT;
import br.com.fiap3espg.autoescola3espg.domain.usuario.Usuario;
import br.com.fiap3espg.autoescola3espg.infra.security.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    private final AuthenticationManager manager;
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosLogin dados) {
        try {
            var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
            Authentication authentication = manager.authenticate(token);
            String tokenJWT = tokenService.generateToken((Usuario) authentication.getPrincipal());

            return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));

        } catch (Exception e) {
            e.printStackTrace(); // Imprime o erro vermelho no console do IntelliJ

            // Devolve o erro MASTIGADO no Postman
            return ResponseEntity.badRequest().body("A autenticação falhou! Motivo: " + e.getMessage() + " | Tipo do Erro: " + e.getClass().getSimpleName());
        }
    }
}