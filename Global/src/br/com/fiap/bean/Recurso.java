package br.com.fiap.bean;

import java.time.LocalDate;

/**
 * Representa um recurso físico genérico (ex: água, alimento, kit de higiene)
 * que pode ser utilizado ou disponibilizado em situações de emergência.
 * Inclui informações sobre tipo, localização descritiva, disponibilidade, quantidade,
 * prioridade e data de disponibilização. Para abrigos, veja a classe {@link Abrigo}.
 */
public class Recurso {
    private String tipo;
    private String localDescritivo;
    private boolean disponivel;
    private int quantidade;
    private int prioridade;
    private LocalDate dataDisponibilizacao;

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
     * Retorna o local descritivo onde o recurso está disponível.
     * @return O local descritivo do recurso.
     */
    public String getLocalDescritivo() {
        return localDescritivo;
    }

    /**
     * Verifica se o recurso está disponível.
     * @return {@code true} se o recurso está disponível, {@code false} caso contrário.
     */
    public boolean isDisponivel() {
        return disponivel;
    }

    /**
     * Define a disponibilidade do recurso.
     * @param disponivel O novo estado de disponibilidade.
     */
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    /**
     * Retorna a quantidade disponível do recurso.
     * @return A quantidade do recurso.
     */
    public int getQuantidade() {
        return quantidade;
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
     * Retorna o nível de prioridade do recurso.
     * @return O nível de prioridade.
     */
    public int getPrioridade() {
        return prioridade;
    }

    /**
     * Define o nível de prioridade do recurso.
     * @param prioridade O novo nível de prioridade.
     */
    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    /**
     * Retorna a data em que o recurso foi disponibilizado.
     * @return A data de disponibilização.
     */
    public LocalDate getDataDisponibilizacao() {
        return dataDisponibilizacao;
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

    /**
     * Verifica a disponibilidade primária do recurso.
     * @return {@code true} se o recurso está marcado como disponível, {@code false} caso contrário.
     */
    public boolean verificarDisponibilidade() {
        return isDisponivel();
    }

    /**
     * Verifica a disponibilidade do recurso, com a opção de incluir recursos inativos na verificação.
     * @param incluirInativos Se {@code true}, retorna {@code true} (considera disponível para essa lógica).
     * Se {@code false}, retorna o status real de disponibilidade.
     * @return {@code true} se o recurso deve ser considerado disponível com base no parâmetro.
     */
    public boolean verificarDisponibilidade(boolean incluirInativos) {
        return incluirInativos || isDisponivel();
    }
}