package br.com.fiap.bean;

import java.time.LocalDate;

/**
 * Representa um recurso físico genérico (ex: água, alimento, kit de higiene)
 * que pode ser utilizado ou disponibilizado em situações de emergência.
 * Inclui informações sobre tipo, localização descritiva, disponibilidade, quantidade,
 * prioridade e data de disponibilização. Para abrigos, veja a classe {@link Abrigo}.
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
     * A quantidade inicial é definida como 1, a prioridade como 2 (Média),
     * e a data de disponibilização para a data atual.
     *
     * @param tipo O tipo do recurso (ex: "Água", "Alimento").
     * @param localDescritivo A localização descritiva onde o recurso está disponível.
     * @param disponivel Indica se o recurso está atualmente disponível (true) ou não (false).
     */
    public Recurso(String tipo, String localDescritivo, boolean disponivel) {
        this.tipo = tipo;
        this.localDescritivo = localDescritivo;
        this.disponivel = disponivel;
        this.quantidade = 1;
        this.prioridade = 2;
        this.dataDisponibilizacao = LocalDate.now();
    }

    // Getters e Setters com Javadoc

    /**
     * Retorna o tipo do recurso.
     * @return O tipo do recurso.
     */
    public String getTipo() {
        return tipo;
    }


    /**
     * Verifica se o recurso está disponível.
     * @return {@code true} se o recurso está disponível, {@code false} caso contrário.
     */
    public boolean isDisponivel() {
        return disponivel;
    }


    /**
     * Define a quantidade do recurso.
     * @param quantidade A nova quantidade do recurso. Deve ser não negativa.
     */
    public void setQuantidade(int quantidade) {
        if (quantidade >= 0) {
            this.quantidade = quantidade;
        }
    }


    /**
     * Define o nível de prioridade do recurso.
     * @param prioridade O novo nível de prioridade.
     */
    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }


    /**
     * Retorna uma representação em String do objeto Recurso.
     * @return Uma {@code String} formatada com os dados do recurso.
     */
    @Override
    public String toString() {
        return "Recurso: " + tipo + " em " + localDescritivo +
                (disponivel ? " ✅ Disponível" : " ❌ Indisponível") +
                " | Quantidade: " + quantidade +
                " | Prioridade: " + prioridade +
                " | Desde: " + dataDisponibilizacao;
    }

}