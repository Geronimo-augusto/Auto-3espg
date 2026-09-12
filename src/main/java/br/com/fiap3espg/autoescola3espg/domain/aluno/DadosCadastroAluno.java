package br.com.fiap3espg.autoescola3espg.domain.aluno;

import br.com.fiap3espg.autoescola3espg.domain.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroAluno(
        @NotBlank String nome,
        @NotBlank @Email String email,
        @NotBlank @Pattern(regexp = "\\d{11}") String cpf,
        @NotBlank String telefone,
        @Valid
        DadosEndereco endereco
) {
}