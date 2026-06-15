# sensor-sse

Aplicação Spring Boot que simula leituras de sensores de temperatura e as transmite via SSE (Server-Sent Events) para uma página HTML com atualização em tempo real.

## Tecnologias

- Java 17+
- Spring Boot 3.5
- Spring Web (SseEmitter)
- HTML + JavaScript puro (EventSource API)

## Como executar

### Pré-requisitos

- Java 17 ou superior
- Maven

### Passos

1. Clone o repositório:
   ```bash
   git clone https://github.com/hemilydev/sensor-sse.git
   cd sensor-sse
   ```

2. Execute a aplicação:
   ```bash
   mvn spring-boot:run
   ```

3. Acesse no navegador:
   ```
   http://localhost:8080
   ```

## Endpoints

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/sensores/stream` | Stream SSE com leituras de temperatura em tempo real |

## Funcionamento

- O servidor gera leituras aleatórias de temperatura (entre 20°C e 30°C) para três sensores: `sala`, `server` e `externo`.
- As leituras são emitidas a cada 2 segundos via `@Scheduled`.
- O front-end conecta ao endpoint `/sensores/stream` usando a API `EventSource` e atualiza os cards dinamicamente.
- O indicador de estado exibe: `Conectando`, `Conectado` ou `Reconectando`.
