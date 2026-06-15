package com.pucgoias.sensor_sse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/sensores")
@CrossOrigin(origins = "*")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping(value = "/stream",
                produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter stream(
            @RequestHeader(value = "Last-Event-ID",
                           required = false) String lastId) {

        SseEmitter emitter = new SseEmitter(-1L);
        eventoService.registrar(emitter);

        emitter.onCompletion(() -> eventoService.remover(emitter));
        emitter.onTimeout(()  -> eventoService.remover(emitter));
        emitter.onError((e)   -> eventoService.remover(emitter));

        return emitter;
    }
}