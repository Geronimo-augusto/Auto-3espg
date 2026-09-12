package br.com.fiap3espg.autoescola3espg.service;

import br.com.fiap3espg.autoescola3espg.domain.aluno.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository repository;

    @Transactional
    public Aluno cadastrar(DadosCadastroAluno dados) {

        var aluno = new Aluno(dados);
        return repository.save(aluno);
    }

    public Page<DadosDetalhamentoAluno> listarAtivos(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosDetalhamentoAluno::new);
    }

    @Transactional
    public Aluno atualizar(DadosAtualizacaoAluno dados) {
        var aluno = repository.getReferenceById(dados.id());
        aluno.atualizarInformacoes(dados);
        return aluno;
    }

    @Transactional
    public void inativar(Long id) {
        var aluno = repository.getReferenceById(id);
        aluno.inativar();
    }
}