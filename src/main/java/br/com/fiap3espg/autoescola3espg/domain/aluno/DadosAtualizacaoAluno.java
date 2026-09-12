package br.com.fiap3espg.autoescola3espg.domain.aluno;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoAluno(
        @NotNull Long id,
        String nome,
        String telefone
) {
}