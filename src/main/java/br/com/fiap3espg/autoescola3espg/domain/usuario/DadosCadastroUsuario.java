package br.com.fiap3espg.autoescola3espg.domain.usuario;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(
        @NotBlank String login,
        @NotBlank String senha,
        Role perfil

) {
}