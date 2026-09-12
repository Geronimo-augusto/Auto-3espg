package br.com.fiap3espg.autoescola3espg.domain.aluno;

import br.com.fiap3espg.autoescola3espg.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Aluno")
@Table(name = "alunos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;

    @Embedded
    private Endereco endereco;
    private Boolean ativo;

    public Aluno(DadosCadastroAluno dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.cpf = dados.cpf();
        this.telefone = dados.telefone();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoes(DadosAtualizacaoAluno dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
    }

    public void inativar() {
        this.ativo = false;
    }
}