package br.com.fiap.bean;

import java.time.LocalDate;

/**
 * Representa a previsão meteorológica para um incêndio.
 */
public class PrevisaoMeteorologica {
    private LocalDate data;
    private double temperatura;
    private double umidade;
    private double vento;
    private double probabilidadeChuva;

    public PrevisaoMeteorologica(LocalDate data, double temperatura, double umidade, double vento, double probabilidadeChuva) {
        this.data = data;
        this.temperatura = temperatura;
        this.umidade = umidade;
        this.vento = vento;
        this.probabilidadeChuva = probabilidadeChuva;
    }

    public LocalDate getData() {
        return data;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public double getUmidade() {
        return umidade;
    }

    public double getVento() {
        return vento;
    }

    public double getProbabilidadeChuva() {
        return probabilidadeChuva;
    }

    /**
     * Verifica se as condições são favoráveis à propagação de incêndios.
     *
     * @return true se favoráveis, false caso contrário.
     */
    public boolean isCondicaoCritica() {
        return temperatura > 35 && umidade < 30 && probabilidadeChuva < 20;
    }

    @Override
    public String toString() {
        return "Data: " + data + ", Temp: " + temperatura + "°C, Umidade: " + umidade + "%, Vento: " + vento + "km/h, Prob. Chuva: " + probabilidadeChuva + "%";
    }
}