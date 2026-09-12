package br.com.fiap3espg.autoescola3espg.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosAlteracaoSenha(
        @NotBlank String senhaAtual,
        @NotBlank String novaSenha
) {
}