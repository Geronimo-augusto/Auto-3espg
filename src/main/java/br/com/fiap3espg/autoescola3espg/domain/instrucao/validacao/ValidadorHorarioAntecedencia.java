package br.com.fiap3espg.autoescola3espg.domain.instrucao.validacao;

import br.com.fiap3espg.autoescola3espg.domain.instrucao.DadosAgendamentoInstrucao;
import br.com.fiap3espg.autoescola3espg.domain.instrucao.ValidacaoException;

import java.time.Duration;
import java.time.LocalDateTime;

public class ValidadorHorarioAntecedencia implements ValidadorAgendamento{
    @Override
    public void validar (DadosAgendamentoInstrucao dados){
        LocalDateTime dataEscolhida = dados.dataHora();
        LocalDateTime agora = LocalDateTime.now();

        long antencendencia = Duration.between(agora,dataEscolhida).toMinutes();
        long time = 30;
        if (antencendencia < time){
            throw new ValidacaoException("Não pode agendar com menos de 30 minutos de antecedencia");
        }
    }
}
