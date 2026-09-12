package br.com.fiap3espg.autoescola3espg.service;

import br.com.fiap3espg.autoescola3espg.domain.instrucao.ValidacaoException;
import br.com.fiap3espg.autoescola3espg.domain.usuario.DadosAlteracaoSenha;
import br.com.fiap3espg.autoescola3espg.domain.usuario.DadosCadastroUsuario;
import br.com.fiap3espg.autoescola3espg.domain.usuario.Usuario;
import br.com.fiap3espg.autoescola3espg.domain.usuario.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Usuario cadastrar(DadosCadastroUsuario dados) {
        // Criptografa a senha antes de salvar
        String senhaCriptografada = passwordEncoder.encode(dados.senha());

        // Crie o construtor apropriado na sua entidade Usuario
        Usuario novoUsuario = new Usuario(dados, senhaCriptografada);
        return repository.save(novoUsuario);
    }



    @Transactional
    public void alterarSenhaPropria(Usuario usuarioLogado, DadosAlteracaoSenha dados) {

        if (!passwordEncoder.matches(dados.senhaAtual(), usuarioLogado.getSenha())) {
            throw new ValidacaoException("A senha atual informada está incorreta.");
        }


        String novaSenhaCriptografada = passwordEncoder.encode(dados.novaSenha());
        usuarioLogado.setSenha(novaSenhaCriptografada);


        repository.save(usuarioLogado);
    }
}