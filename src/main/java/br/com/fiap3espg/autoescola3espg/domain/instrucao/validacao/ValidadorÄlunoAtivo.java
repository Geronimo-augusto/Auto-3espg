package br.com.fiap3espg.autoescola3espg.domain.instrucao.validacao;


import br.com.fiap3espg.autoescola3espg.domain.aluno.AlunoRepository;
import br.com.fiap3espg.autoescola3espg.domain.instrucao.DadosAgendamentoInstrucao;
import br.com.fiap3espg.autoescola3espg.domain.instrucao.ValidacaoException;
import br.com.fiap3espg.autoescola3espg.domain.instrutor.InstrutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidadorÄlunoAtivo implements ValidadorAgendamento {
    private final AlunoRepository alunoRepository;


    public void validar(DadosAgendamentoInstrucao dados){
        if(!alunoRepository.existsByIdAndAtivoTrue(dados.idAluno())){
            throw new ValidacaoException("Não é possivel agendar instrucão pra aluno inativo");
        }
    }


}
