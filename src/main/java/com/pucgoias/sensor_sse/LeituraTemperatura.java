package com.pucgoias.sensor_sse;

public class LeituraTemperatura {

    private String sensor;
    private double valor;
    private long timestamp;

    public LeituraTemperatura(String sensor, double valor, long timestamp) {
        this.sensor = sensor;
        this.valor = valor;
        this.timestamp = timestamp;
    }

    public String getSensor() { return sensor; }
    public double getValor() { return valor; }
    public long getTimestamp() { return timestamp; }
}