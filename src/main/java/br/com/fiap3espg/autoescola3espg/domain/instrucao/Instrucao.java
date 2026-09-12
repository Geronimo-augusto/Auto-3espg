package br.com.fiap3espg.autoescola3espg.domain.instrucao;

import br.com.fiap3espg.autoescola3espg.domain.aluno.Aluno;
import br.com.fiap3espg.autoescola3espg.domain.endereco.Endereco;
import br.com.fiap3espg.autoescola3espg.domain.instrutor.DadosCadastroInstrutor;
import br.com.fiap3espg.autoescola3espg.domain.instrutor.Instrutor;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.temporal.Temporal;

@Entity(name = "Instrucao")
@Table(name = "instrucoes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Instrucao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instrutor_id")
    private Instrutor instrutor;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    @Column(name = "motivo_cancelamento")
    @Enumerated(EnumType.STRING)
    private MotivoCancelamento motivoCancelamento;

    public Instrucao(Long id, Aluno aluno, Instrutor instrutor, LocalDateTime dataHora) {
            this.id = id;
            this.aluno = aluno;
            this.instrutor = instrutor;
            this.dataHora = dataHora;
    }


    public void cancelar(MotivoCancelamento motivo) {
        this.motivoCancelamento = motivo;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }
}