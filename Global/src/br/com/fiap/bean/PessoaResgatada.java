package br.com.fiap.bean;

/**
 * Representa uma pessoa que já foi resgatada em uma situação de emergência.
 * Herda da classe {@link Pessoa}. O campo {@code statusResgate} desta classe
 * representa o status específico pós-resgate.
 */
public class PessoaResgatada extends Pessoa {
    private String statusResgate;

    /**
     * Constrói uma nova instância de PessoaResgatada.
     *
     * @param nome O nome completo da pessoa resgatada.
     * @param localizacaoDescritiva A localização de onde a pessoa foi resgatada ou sua localização atual.
     * @param statusResgate O status da pessoa após o resgate (ex: "Em abrigo seguro", "Hospitalizada").
     */
    public PessoaResgatada(String nome, String localizacaoDescritiva, String statusResgate) {
        super(nome, localizacaoDescritiva);
        super.setStatus("Resgatada");
        this.statusResgate = statusResgate;
    }

    /**
     * Retorna o status específico da pessoa após o resgate.
     * @return O status pós-resgate da pessoa.
     */
    public String getStatusResgate() {
        return statusResgate;
    }

    /**
     * Define o status específico da pessoa após o resgate.
     * @param statusResgate O novo status pós-resgate.
     */
    public void setStatusResgate(String statusResgate) {
        this.statusResgate = statusResgate;
    }

    /**
     * Retorna uma representação em String do objeto PessoaResgatada,
     * indicando que a pessoa foi resgatada e incluindo seu nome, localização e status pós-resgate.
     * Este método sobrescreve o método {@code toString} da classe {@link Pessoa}.
     *
     * @return Uma {@code String} formatada com os dados da pessoa resgatada.
     */
    @Override
    public String toString() {
        return "✅ Pessoa resgatada: " + getNome() +
                " | Localização: " + getLocalizacaoDescritiva() +
                " | Status Geral: " + super.getStatus() +
                " | Detalhe Resgate: " + statusResgate;
    }
}