package br.com.fiap3espg.autoescola3espg.controller;

import br.com.fiap3espg.autoescola3espg.domain.usuario.*;
import br.com.fiap3espg.autoescola3espg.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;
    private final UsuarioRepository repository;

    // Apenas ADMIN pode cadastrar

    @PostMapping("/cadastrar")
    @Transactional
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder) {
        var usuario = service.cadastrar(dados);

        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuario(usuario));
    }

    // Apenas ADMIN pode listar/deletar (exemplo genérico, você pode adaptar)
    @DeleteMapping("/{id}")
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity excluir(@PathVariable Long id) {
        // service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    // Listar todos os usuários (Paginado)
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<DadosDetalhamentoUsuario>> listar(@PageableDefault(size = 10, sort = {"login"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosDetalhamentoUsuario::new);
        return ResponseEntity.ok(page);
    }

    // Buscar um usuário específico por ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var usuario = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }

    // Qualquer usuário LOGADO pode alterar a própria senha
    // O Spring Security injeta automaticamente o usuário logado via @AuthenticationPrincipal
    @PutMapping("/alterar-senha")
    @Transactional
    public ResponseEntity alterarSenha(@RequestBody @Valid DadosAlteracaoSenha dados, @AuthenticationPrincipal Usuario usuarioLogado) {
        service.alterarSenhaPropria(usuarioLogado, dados);
        return ResponseEntity.noContent().build();
    }
}