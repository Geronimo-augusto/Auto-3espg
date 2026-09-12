package br.com.fiap3espg.autoescola3espg.domain.aluno;

public record DadosDetalhamentoAluno(Long id, String nome, String email, String cpf, String telefone, Boolean ativo) {
    public DadosDetalhamentoAluno(Aluno aluno) {
        this(aluno.getId(), aluno.getNome(), aluno.getEmail(), aluno.getCpf(), aluno.getTelefone(), aluno.getAtivo());
    }
}