package br.com.fiap3espg.autoescola3espg.domain.instrucao;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface InstrucaoRepository extends JpaRepository<Instrucao, Long> {

    boolean existsByInstrutorIdAndDataHora(Long aLong, @NotNull @Future LocalDateTime localDateTime);
}