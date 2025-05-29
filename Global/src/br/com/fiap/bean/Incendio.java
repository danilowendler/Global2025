package br.com.fiap.bean;

import java.time.LocalDate;

/**
 * Representa um incêndio florestal.
 */
public class Incendio {
    private int id;
    private Localizacao localizacao;
    private Causa causa;
    private PrevisaoMeteorologica previsao;
    private TipoVegetacao tipoVegetacao;
    private LocalDate dataInicio;
    private double areaAtingida;

    public Incendio(int id, Localizacao localizacao, Causa causa, PrevisaoMeteorologica previsao, TipoVegetacao tipoVegetacao, LocalDate dataInicio, double areaAtingida) {
        this.id = id;
        this.localizacao = localizacao;
        this.causa = causa;
        this.previsao = previsao;
        this.tipoVegetacao = tipoVegetacao;
        this.dataInicio = dataInicio;
        this.areaAtingida = areaAtingida;
    }

    public int getId() {
        return id;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public Causa getCausa() {
        return causa;
    }

    public PrevisaoMeteorologica getPrevisao() {
        return previsao;
    }

    public TipoVegetacao getTipoVegetacao() {
        return tipoVegetacao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public double getAreaAtingida() {
        return areaAtingida;
    }

    /**
     * Verifica se o incêndio é de grande proporção.
     *
     * @return true se área atingida for superior a 1000 hectares, false caso contrário.
     */
    public boolean isGrandeProporcao() {
        return areaAtingida > 1000;
    }

    @Override
    public String toString() {
        return "Incêndio ID: " + id + ", Local: " + localizacao + ", Causa: " + causa + ", Data Início: " + dataInicio + ", Área Atingida: " + areaAtingida + " ha";
    }
}
