package br.com.fiap3espg.autoescola3espg.domain.instrucao.validacao;

import br.com.fiap3espg.autoescola3espg.domain.instrucao.DadosAgendamentoInstrucao;
import br.com.fiap3espg.autoescola3espg.domain.instrucao.InstrucaoRepository;
import br.com.fiap3espg.autoescola3espg.domain.instrucao.ValidacaoException;
import br.com.fiap3espg.autoescola3espg.domain.instrutor.InstrutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidadorConflitoHora implements ValidadorAgendamento{
    private final InstrucaoRepository repository;


    @Override
    public void validar(DadosAgendamentoInstrucao dados) {
        boolean instrutorOcupado = repository.existsByInstrutorIdAndDataHora(
                dados.idInstrutor(),
                dados.dataHora()
        );

        if(instrutorOcupado){
            throw new ValidacaoException("Instrutor ja ocupado para tal data" + instrutorOcupado);
        }
    }
}
