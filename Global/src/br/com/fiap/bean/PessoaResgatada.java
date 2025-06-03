package br.com.fiap.bean;


/**
 * Representa uma pessoa que já foi resgatada em uma situação de emergência.
 * Herda da classe {@link Pessoa}. O campo {@code statusResgate} desta classe
 * representa o status específico pós-resgate.
 */
public class PessoaResgatada extends Pessoa {
    private final String statusResgate;

    /**
     * Constrói uma nova instância de PessoaResgatada.
     *
     * @param nome O nome completo da pessoa resgatada.
     * @param localizacaoDescritiva A localização de onde a pessoa foi resgatada ou sua localização atual.
     * @param zona A zona da cidade onde a pessoa se encontrava ou foi resgatada.
     * @param statusResgate O status da pessoa após o resgate (ex: "Em abrigo seguro", "Hospitalizada").
     */
    public PessoaResgatada(String nome, String localizacaoDescritiva, String zona, String statusResgate) {
        super(nome, localizacaoDescritiva, zona);
        super.setStatus("Resgatada");
        this.statusResgate = statusResgate;
    }


    /**
     * Retorna uma representação em String do objeto PessoaResgatada,
     * indicando que a pessoa foi resgatada e incluindo seu nome, localização (com zona),
     * o status geral da superclasse e o status específico do resgate.
     * Este metodo sobrescreve o metodo {@code toString} da classe {@link Pessoa}.
     *
     * @return Uma {@code String} formatada com os dados da pessoa resgatada.
     */
    @Override
    public String toString() {
        String zonaInfo = "";
        if (getZona() != null && !getZona().isEmpty()) {
            zonaInfo = " (Zona: " + getZona() + ")";
        }

        return "✅ Pessoa resgatada: " + getNome() +
                " | Localização: " + getLocalizacaoDescritiva() + zonaInfo +
                " | Status Geral: " + super.getStatus() +
                " | Detalhe Resgate: " + statusResgate;
    }
}