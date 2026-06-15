package com.pucgoias.sensor_sse;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class EventoService {

    private final List<SseEmitter> emitters =
            new CopyOnWriteArrayList<>();

    public void registrar(SseEmitter emitter) {
        emitters.add(emitter);
    }

    public void remover(SseEmitter emitter) {
        emitters.remove(emitter);
    }

    @Async
    public void publicar(String tipo, Object payload) throws IOException {
        SseEmitter.SseEventBuilder evento =
            SseEmitter.event()
                .id(String.valueOf(System.currentTimeMillis()))
                .name(tipo)
                .data(payload)
                .reconnectTime(3000);

        List<SseEmitter> mortos = new ArrayList<>();
        for (SseEmitter em : emitters) {
            try {
                em.send(evento);
            } catch (Exception e) {
                mortos.add(em);
            }
        }
        emitters.removeAll(mortos);
    }
}