package br.com.fiap.bean;

import java.time.LocalDate;

/**
 * Representa uma ação de combate a um incêndio.
 */
public class AcaoCombate {
    private int id;
    private Incendio incendio;
    private Equipe equipe;
    private LocalDate data;
    private String descricao;
    private double areaControlada;

    public AcaoCombate(int id, Incendio incendio, Equipe equipe, LocalDate data, String descricao, double areaControlada) {
        this.id = id;
        this.incendio = incendio;
        this.equipe = equipe;
        this.data = data;
        this.descricao = descricao;
        this.areaControlada = areaControlada;
    }

    public int getId() {
        return id;
    }

    public Incendio getIncendio() {
        return incendio;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public LocalDate getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getAreaControlada() {
        return areaControlada;
    }

    /**
     * Verifica se a ação foi eficaz, controlando mais de 50% da área atingida.
     *
     * @return true se eficaz, false caso contrário.
     */
    public boolean isEficaz() {
        return areaControlada >= (incendio.getAreaAtingida() * 0.5);
    }

    @Override
    public String toString() {
        return "Ação ID: " + id + ", Incêndio ID: " + incendio.getId() + ", Equipe: " + equipe + ", Data: " + data + ", Área Controlada: " + areaControlada + " ha";
    }
}
