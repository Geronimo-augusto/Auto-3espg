package br.com.fiap3espg.autoescola3espg.domain.usuario;

public record DadosDetalhamentoUsuario(Long id, String login) {
    public DadosDetalhamentoUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getLogin());
    }
}