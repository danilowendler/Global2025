package br.com.fiap.bean;

import java.time.LocalDate;

/**
 * Representa um recurso físico genérico (ex: água, alimento, kit de higiene)
 * que pode ser utilizado ou disponibilizado em situações de emergência.
 */
public class Recurso {
    private final String tipo;
    private final String localDescritivo;
    private final boolean disponivel;
    private int quantidade;
    private int prioridade;
    private final LocalDate dataDisponibilizacao;
    /**
     * Constrói uma nova instância de Recurso.
     * @param tipo O tipo do recurso (ex: "Água", "Alimento").
     * @param localDescritivo A localização descritiva onde o recurso está disponível.
     * @param disponivel Indica se o recurso está atualmente disponível (true) ou não (false).
     */
    public Recurso(String tipo, String localDescritivo, boolean disponivel) {
        this.tipo = tipo;
        this.localDescritivo = localDescritivo;
        this.disponivel = disponivel;
        this.quantidade = 1;
        this.prioridade = 2; // Padrão Média
        this.dataDisponibilizacao = LocalDate.now();
    }

    public String getTipo() {
        return tipo;
    }


    public boolean isDisponivel() {
        return disponivel;
    }


    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }


    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }


    /**
     * Retorna uma representação em String do objeto Recurso.
     * @return Uma {@code String} formatada com os dados do recurso.
     */
    public String toString() {
        return "Recurso: " + tipo + " em " + localDescritivo +
                (disponivel ? " ✅ Disponível" : " ❌ Indisponível") +
                " | Quantidade: " + quantidade +
                " | Prioridade: " + prioridade +
                " | Desde: " + dataDisponibilizacao;
    }
}