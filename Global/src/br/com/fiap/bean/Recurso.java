package br.com.fiap.bean;

import java.time.LocalDate;

/**
 * Representa um recurso físico utilizado em situações de emergência.
 */
public class Recurso {
    private String tipo;
    private String local;
    private boolean disponivel;
    private int quantidade;
    private int prioridade;
    private LocalDate dataDisponibilizacao;

    public Recurso(String tipo, String local, boolean disponivel) {
        this.tipo = tipo;
        this.local = local;
        this.disponivel = disponivel;
        this.quantidade = 1;
        this.prioridade = 2;
        this.dataDisponibilizacao = LocalDate.now();
    }

    public String getTipo() {
        return tipo;
    }

    public String getLocal() {
        return local;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public LocalDate getDataDisponibilizacao() {
        return dataDisponibilizacao;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    @Override
    public String toString() {
        return tipo + " disponível em " + local +
                (disponivel ? " ✅" : " ❌") +
                " | Quantidade: " + quantidade +
                " | Prioridade: " + prioridade +
                " | Desde: " + dataDisponibilizacao;
    }

    public boolean verificarDisponibilidade() {
        return disponivel;
    }

    public boolean verificarDisponibilidade(boolean incluirInativos) {
        return incluirInativos || disponivel;
    }
}
