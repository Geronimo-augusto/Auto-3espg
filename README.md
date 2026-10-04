
# Auto-3espg

API da autoescola 3ESPG: aluno, instrutor, usuário e agendamento de instrução. Spring Boot, pacote `br.com.fiap3espg.autoescola3espg`.

Grupo: Ana Laura Torres Loureiro (RM 554375), Murilo Cordeiro Ferreira (RM 556727), Geronimo Augusto (RM 557170), Ianny Raquel Ferreira de Souza (RM 559096).

## Domínio

| Pacote | O que guarda |
|---|---|
| `domain/aluno` | aluno, endereço, cadastro e inativação |
| `domain/instrutor` | instrutor e `Especialidade` |
| `domain/usuario` | login, `Role`, troca de senha |
| `domain/instrucao` | aula marcada, cancelamento, `MotivoCancelamento` |
| `domain/instrucao/validacao` | regras do agendamento, uma classe por regra |
| `infra/security` | JWT, `SecurityFilter`, `SecurityConfig` |
| `infra/exception` | `TratadorGlobalErros` |

## Rotas vistas nos controllers

| Método | Rota | Efeito |
|---|---|---|
| POST | `/login` | autentica e devolve `DadosTokenJWT`. Falha responde 400 com a mensagem da exceção |
| POST | `/alunos/cadastrar` | cadastra aluno |
| GET | `/alunos` | lista ativos, paginado |
| PUT | `/alunos` | atualiza |
| DELETE | `/alunos/{id}` | inativa |
| POST | `/instrucoes` | agenda. Corpo `DadosAgendamentoInstrucao` |
| DELETE | `/instrucoes/{id}` | cancela. Corpo `DadosCancelamentoInstrucao` |

Há também `InstrutorController`, `UsuarioController` e `HealthCheckController` no mesmo pacote.

## Regras do agendamento

O controller não contém a regra. `Instrucao` percorre `ValidadorAgendamento`. As classes em `validacao/`:

- `ValidadorHorarioFuncionamento` — recusa domingo e horário fora de 06:00–20:00, considerando aula de 1 hora. Mensagem: `Horario não disponivel`.
- `ValidadorHorarioAntecedencia` e `ValidadorAntecedenciaCancelamento` — antecedência para marcar e para cancelar.
- `ValidadorHoraInteira` — hora cheia.
- `ValidadorConflitoHora` — choque de horário.
- `ValidadorLimiteDiarioAluno` — teto de aulas do aluno no dia.
- `ValidadorAlunoAtivo` / `ValidadorInstrutorAtivo` — cadastro ativo. Os arquivos no repo estão como `ValidadorÄlunoAtivo.java` e `ValidadrInstrutorAtivo.java`.

Falha de regra sobe `ValidacaoException`.

## Como rodar

```bash
git clone https://github.com/Geronimo-augusto/Auto-3espg.git
cd Auto-3espg
./mvnw spring-boot:run
```

Banco, usuário e segredo do JWT estão em `src/main/resources`. Sem o serviço que o properties aponta, o contexto não sobe.

## Limites conhecidos

- Dois arquivos de validador com nome quebrado. O comportamento depende de a classe dentro do arquivo ainda ser carregada pelo Spring.
- Login em falha devolve o tipo da exceção no corpo.

## Integrantes:
Ana Laura Torres Loureiro - rm554375 <br>
Murilo Cordeiro Ferreira - rm556727 <br>
Geronimo Augusto - rm557170 <br>
Ianny Raquel Ferreira de Souza - rm559096 <br>
