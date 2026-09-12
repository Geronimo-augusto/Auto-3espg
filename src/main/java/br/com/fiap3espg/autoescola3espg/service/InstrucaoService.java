package br.com.fiap3espg.autoescola3espg.service;

import br.com.fiap3espg.autoescola3espg.domain.aluno.Aluno;
import br.com.fiap3espg.autoescola3espg.domain.aluno.AlunoNotFoundException;
import br.com.fiap3espg.autoescola3espg.domain.aluno.AlunoRepository;
import br.com.fiap3espg.autoescola3espg.domain.instrucao.*;
import br.com.fiap3espg.autoescola3espg.domain.instrucao.validacao.ValidadorAgendamento;
import br.com.fiap3espg.autoescola3espg.domain.instrucao.validacao.ValidadorAntecedenciaCancelamento;
import br.com.fiap3espg.autoescola3espg.domain.instrutor.Instrutor;
import br.com.fiap3espg.autoescola3espg.domain.instrutor.InstrutorNotFoundException;
import br.com.fiap3espg.autoescola3espg.domain.instrutor.InstrutorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstrucaoService {
    private final InstrucaoRepository repository;
    private final AlunoRepository alunoRepository;
    private final InstrutorRepository instrutorRepository;
    private final List<ValidadorAgendamento> validadorAgendamentos;
    private final ValidadorAntecedenciaCancelamento validadorCancelamento;

    public DadosDetalhamentoAgendamento agendarInstrucao(DadosAgendamentoInstrucao dados) {
        if (!alunoRepository.existsById(dados.idAluno())) {
            throw new AlunoNotFoundException("ID do aluno informado não existe!");
        }
        if (dados.idInstrutor() != null && !instrutorRepository.existsById(dados.idInstrutor())) {
            throw new InstrutorNotFoundException("ID do instrutor informado não existe!");
        }
        // validacao
        // Validar estao ativo
        validadorAgendamentos.forEach(validador -> validador.validar(dados));


        Aluno aluno = alunoRepository.getReferenceById(dados.idAluno());
        Instrutor instrutor = escolherInstrutor(dados);

        if (instrutor == null) {
            throw new ValidacaoException("Nenhum instrutor disponível para a data/hora informada!");
        }

        //completar a query no InstrutorRepository

        Instrucao instrucao = new Instrucao(null, aluno, instrutor, dados.dataHora());
        Instrucao salvo = repository.save(instrucao);
        return new DadosDetalhamentoAgendamento(salvo);
    }

    private Instrutor escolherInstrutor(DadosAgendamentoInstrucao dados) {
        if (dados.idInstrutor() != null) {
            return instrutorRepository.getReferenceById(dados.idInstrutor());
        }
        if (dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória se o instrutor não for informado!");
        }
        return instrutorRepository.escolherInstrutorAleatorioDisponivel(
                dados.especialidade(),
                dados.dataHora()
        );
    }

    @Transactional
    public void cancelarInstrucao(long id,DadosCancelamentoInstrucao dados) {
        if (!repository.existsById(id)) {
            throw new ValidacaoException("Id da instrução informado não existe!");
        }

        validadorCancelamento.validar(dados,id);

        var instrucao = repository.getReferenceById(id);
        instrucao.cancelar(dados.motivo());
    }
}