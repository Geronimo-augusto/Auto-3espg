package br.com.fiap3espg.autoescola3espg.domain.instrucao.validacao;

import br.com.fiap3espg.autoescola3espg.domain.instrucao.DadosAgendamentoInstrucao;

import java.time.LocalDateTime;

public class ValidadorHoraInteira implements ValidadorAgendamento{
    public void validar(DadosAgendamentoInstrucao dados){
        LocalDateTime dataEscolhida = dados.dataHora();
        if(dataEscolhida.getMinute() != 0/*&& dataEscolhida.getSecond() != 0 && dataEscolhida.getNano() != 0*/){

        }
    }
}
