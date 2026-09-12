package br.com.fiap3espg.autoescola3espg.domain.instrucao.validacao;

import br.com.fiap3espg.autoescola3espg.domain.instrucao.DadosCancelamentoInstrucao;
import br.com.fiap3espg.autoescola3espg.domain.instrucao.InstrucaoRepository;
import br.com.fiap3espg.autoescola3espg.domain.instrucao.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorAntecedenciaCancelamento {

    @Autowired
    private InstrucaoRepository repository;

    public void validar(DadosCancelamentoInstrucao dados, long id) {
        var instrucao = repository.getReferenceById(id);
        var agora = LocalDateTime.now();
        var diferencaEmHoras = Duration.between(agora, instrucao.getDataHora()).toHours();

        if (diferencaEmHoras < 24) {
            throw new ValidacaoException("Instrução somente pode ser cancelada com antecedência mínima de 24 horas!");
        }
    }
}