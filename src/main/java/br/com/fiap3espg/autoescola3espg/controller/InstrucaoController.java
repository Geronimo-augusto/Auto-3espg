package br.com.fiap3espg.autoescola3espg.controller;

import br.com.fiap3espg.autoescola3espg.domain.aluno.Aluno;
import br.com.fiap3espg.autoescola3espg.domain.aluno.AlunoRepository;
import br.com.fiap3espg.autoescola3espg.domain.instrucao.*;
import br.com.fiap3espg.autoescola3espg.domain.instrutor.Instrutor;
import br.com.fiap3espg.autoescola3espg.domain.instrutor.InstrutorRepository;
import br.com.fiap3espg.autoescola3espg.service.InstrucaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/instrucoes")
@RequiredArgsConstructor
public class InstrucaoController {
    private final InstrucaoService service;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoAgendamento> agendarInstrucao(@RequestBody @Valid DadosAgendamentoInstrucao dados) {
        return ResponseEntity.ok(service.agendarInstrucao(dados));
    }
    @DeleteMapping("/{id}")
    @Transactional
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity cancelarInstrucao(@PathVariable Long id, @RequestBody @Valid DadosCancelamentoInstrucao dados) {
        service.cancelarInstrucao(id, dados);
        return ResponseEntity.noContent().build();
    }
}