package com.pucgoias.sensor_sse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Random;

@Component
@EnableScheduling
public class SensorSimulador {

    @Autowired
    private EventoService eventoService;

    private final Random random = new Random();
    private final String[] sensores = {"sala", "server", "externo"};

    @Scheduled(fixedRate = 2000)
    public void emitirLeituras() throws IOException {
        for (String sensor : sensores) {
            LeituraTemperatura leitura = new LeituraTemperatura(
                sensor,
                20.0 + random.nextDouble() * 10,
                System.currentTimeMillis()
            );
            eventoService.publicar("temperatura", leitura);
        }
    }
}