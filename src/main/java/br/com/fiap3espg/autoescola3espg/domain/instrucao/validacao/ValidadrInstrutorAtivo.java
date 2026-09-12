package br.com.fiap3espg.autoescola3espg.domain.instrucao.validacao;

import br.com.fiap3espg.autoescola3espg.domain.instrucao.DadosAgendamentoInstrucao;
import br.com.fiap3espg.autoescola3espg.domain.instrucao.ValidacaoException;
import br.com.fiap3espg.autoescola3espg.domain.instrutor.InstrutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidadrInstrutorAtivo implements ValidadorAgendamento{

    private final InstrutorRepository instrutorRepository;

    public  void validar(DadosAgendamentoInstrucao dados){
        if(instrutorRepository.existsByIdAndAtivoFalse(dados.idInstrutor())){
            throw new ValidacaoException("Não é possivel agendar instrucão pra instrutor inativo");
        }
    }
}
