package br.com.fiap3espg.autoescola3espg.domain.instrucao.validacao;

import br.com.fiap3espg.autoescola3espg.domain.instrucao.DadosAgendamentoInstrucao;
import br.com.fiap3espg.autoescola3espg.domain.instrucao.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioFuncionamento implements ValidadorAgendamento{
    @Override
    public void validar(DadosAgendamentoInstrucao dados) {
        LocalDateTime dataEscolhida = dados.dataHora();
        int abertura = 6;
        int fechamento = 21;
        int TempoAula = 1;

        boolean domingo =dataEscolhida.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        boolean preAbertura = dataEscolhida.getHour() < abertura;
        boolean posFechamento = dataEscolhida.getHour() > (fechamento - TempoAula);

        if(domingo|| preAbertura || posFechamento){
            throw new ValidacaoException("Horario não disponivel");
        }
    }
}
