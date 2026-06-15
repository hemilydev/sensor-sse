# sensor-sse — Monitor de Sensores de Temperatura via SSE

> Atividade Prática — Server-Sent Events com Spring Boot  
> Disciplina: Mensageria e Streams em Aplicações  
> Prof. Rafael Leal Martins

---

## Sobre o Projeto

Aplicação Spring Boot que simula leituras de sensores de temperatura e as transmite em tempo real via **SSE (Server-Sent Events)** para uma página HTML, utilizando uma única conexão HTTP persistente.

O servidor gera leituras aleatórias a cada 2 segundos para três sensores:

- **Sala** — sensor interno da sala
- **Server** — sensor do servidor
- **Externo** — sensor do ambiente externo

---

## Funcionalidades

| Funcionalidade | Descrição |
|----------------|-----------|
| Stream SSE | Endpoint que mantém conexão aberta e empurra eventos ao cliente |
| Simulação assíncrona | Leituras geradas a cada 2s com `@Scheduled` e enviadas com `@Async` |
| Cards dinâmicos | Front-end atualiza nome, temperatura e timestamp de cada sensor em tempo real |
| Indicador de estado | Exibe `Conectando`, `Conectado` ou `Reconectando` conforme o estado da conexão |
| Reconexão automática | Browser reconecta automaticamente via `EventSource` em caso de queda |
| Thread-safe | Lista de emitters gerenciada com `CopyOnWriteArrayList` |

---

## Endpoints

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/sensores/stream` | Stream SSE com leituras de temperatura em tempo real |

### Exemplo de evento recebido

```
id: 1749071234567
event: temperatura
data: {"sensor":"sala","valor":24.5,"timestamp":1749071234567}
retry: 3000
```

---

## Estrutura do Projeto

```
src/main/java/com/pucgoias/sensor_sse/
├── SensorSseApplication.java    # Classe principal
├── AsyncConfig.java             # Configuração do ThreadPoolTaskExecutor
├── LeituraTemperatura.java      # Modelo de dados
├── EventoService.java           # Gerenciamento dos emitters e publicação
├── EventoController.java        # Endpoint GET /sensores/stream
└── SensorSimulador.java         # Geração periódica de leituras

src/main/resources/static/
└── index.html                   # Front-end com EventSource e cards dinâmicos
```

---

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

---

## Identificação

**Hemily Ramos**  
Análise e Desenvolvimento de Sistemas — Escola Politécnica e de Artes da PUC Goiás  
Mensageria e Streams em Aplicações — Prof. Rafael Leal Martins — Junho de 2026
