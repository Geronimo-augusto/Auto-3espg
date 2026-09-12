package br.com.fiap3espg.autoescola3espg.domain.instrucao.validacao;

import br.com.fiap3espg.autoescola3espg.domain.instrucao.DadosAgendamentoInstrucao;
import br.com.fiap3espg.autoescola3espg.domain.instrucao.InstrucaoRepository;
import br.com.fiap3espg.autoescola3espg.domain.instrucao.ValidacaoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ValidadorLimiteDiarioAluno implements ValidadorAgendamento {
    private InstrucaoRepository repository;
    @Override
    public void validar(DadosAgendamentoInstrucao dados) {
        int Inicio = 6;
        int fim = 21;
        int aula = 1;
        LocalDateTime inicioExpediente = dados.dataHora().withHour(Inicio);
        LocalDateTime fimExpediene = dados.dataHora().withHour(fim-aula);

//        boolean reincidencia = repository.existsById();
//        if(reincidencia){
//            throw new ValidacaoException("Permitido o agendamento diario de apenas uma instruçao por aluno!");
//        }
    }
}
