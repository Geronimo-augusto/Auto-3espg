package br.com.fiap3espg.autoescola3espg.controller;

import br.com.fiap3espg.autoescola3espg.domain.aluno.*;
import br.com.fiap3espg.autoescola3espg.service.AlunoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/alunos")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService service;

    @PostMapping("/cadastrar")
    @Transactional
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroAluno dados, UriComponentsBuilder uriBuilder) {
        var aluno = service.cadastrar(dados);
        var uri = uriBuilder.path("/alunos/{id}").buildAndExpand(aluno.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoAluno(aluno));
    }

    @GetMapping
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<Page<DadosDetalhamentoAluno>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = service.listarAtivos(paginacao);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoAluno dados) {
        var aluno = service.atualizar(dados);
        return ResponseEntity.ok(new DadosDetalhamentoAluno(aluno));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity inativar(@PathVariable Long id) {
        service.inativar(id);
        return ResponseEntity.noContent().build();
    }
}