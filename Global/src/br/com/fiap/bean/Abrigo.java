package br.com.fiap.bean;

/**
 * Representa um abrigo específico para pessoas em situações de emergência.
 * Contém informações sobre nome, localização geográfica precisa, capacidade,
 * vagas disponíveis, endereço e contato.
 */
public class Abrigo {
    private String nomeAbrigo;
    private double latitude;
    private double longitude;
    private int capacidadeTotal;
    private int vagasDisponiveis;
    private String enderecoCompleto;
    private String contatoResponsavel; // Opcional

    /**
     * Constrói uma nova instância de Abrigo.
     *
     * @param nomeAbrigo O nome do abrigo.
     * @param latitude A latitude geográfica do abrigo.
     * @param longitude A longitude geográfica do abrigo.
     * @param capacidadeTotal A capacidade total de pessoas que o abrigo pode comportar.
     * @param vagasDisponiveis O número de vagas atualmente disponíveis.
     * @param enderecoCompleto O endereço textual completo do abrigo.
     * @param contatoResponsavel O contato do responsável pelo abrigo (opcional).
     */
    public Abrigo(String nomeAbrigo, double latitude, double longitude, int capacidadeTotal, int vagasDisponiveis, String enderecoCompleto, String contatoResponsavel) {
        this.nomeAbrigo = nomeAbrigo;
        this.latitude = latitude;
        this.longitude = longitude;
        this.capacidadeTotal = capacidadeTotal;
        this.vagasDisponiveis = vagasDisponiveis;
        this.enderecoCompleto = enderecoCompleto;
        this.contatoResponsavel = contatoResponsavel;
    }


    /**
     * Retorna o nome do abrigo.
     * @return O nome do abrigo.
     */
    public String getNomeAbrigo() { return nomeAbrigo; }

    /**
     * Retorna a latitude geográfica do abrigo.
     * @return A latitude.
     */
    public double getLatitude() { return latitude; }

    /**
     * Retorna a longitude geográfica do abrigo.
     * @return A longitude.
     */
    public double getLongitude() { return longitude; }

    /**
     * Retorna a capacidade total do abrigo.
     * @return A capacidade total.
     */
    public int getCapacidadeTotal() { return capacidadeTotal; }

    /**
     * Retorna o número de vagas atualmente disponíveis no abrigo.
     * @return O número de vagas disponíveis.
     */
    public int getVagasDisponiveis() { return vagasDisponiveis; }

    /**
     * Retorna o endereço completo do abrigo.
     * @return O endereço completo.
     */
    public String getEnderecoCompleto() { return enderecoCompleto; }

    /**
     * Retorna o contato do responsável pelo abrigo, se disponível.
     * @return O contato do responsável ou null.
     */
    public String getContatoResponsavel() { return contatoResponsavel; }


    /**
     * Define o número de vagas disponíveis.
     * Garante que as vagas não fiquem negativas ou excedam a capacidade total.
     * @param vagasDisponiveis O novo número de vagas disponíveis.
     */
    public void setVagasDisponiveis(int vagasDisponiveis) {
        if (vagasDisponiveis < 0) {
            this.vagasDisponiveis = 0;
        } else if (vagasDisponiveis > this.capacidadeTotal) {
            this.vagasDisponiveis = this.capacidadeTotal;
        } else {
            this.vagasDisponiveis = vagasDisponiveis;
        }
    }


    /**
     * Verifica se ainda existem vagas disponíveis no abrigo.
     * @return {@code true} se {@code vagasDisponiveis > 0}, {@code false} caso contrário.
     */
    public boolean temVagas() {
        return this.vagasDisponiveis > 0;
    }

    /**
     * Tenta ocupar um certo número de vagas no abrigo.
     * Decrementa as vagas disponíveis se houver capacidade.
     *
     * @param numOcupadas O número de vagas a serem ocupadas.
     * @return {@code true} se a operação foi bem-sucedida (vagas ocupadas),
     * {@code false} se não havia vagas suficientes.
     */
    public boolean ocuparVagas(int numOcupadas) {
        if (numOcupadas > 0 && this.vagasDisponiveis >= numOcupadas) {
            this.vagasDisponiveis -= numOcupadas;
            return true;
        }
        return false;
    }

    /**
     * Libera um certo número de vagas no abrigo.
     * Incrementa as vagas disponíveis, não excedendo a capacidade total.
     *
     * @param numLiberadas O número de vagas a serem liberadas.
     */
    public void liberarVagas(int numLiberadas) {
        if (numLiberadas > 0) {
            this.vagasDisponiveis += numLiberadas;
            if (this.vagasDisponiveis > this.capacidadeTotal) {
                this.vagasDisponiveis = this.capacidadeTotal;
            }
        }
    }

    /**
     * Retorna uma representação em String do objeto Abrigo.
     * @return Uma {@code String} formatada com os dados do abrigo.
     */
    @Override
    public String toString() {
        return "🏠 Abrigo: " + nomeAbrigo +
                " | Endereço: " + enderecoCompleto +
                " | Vagas: " + vagasDisponiveis + "/" + capacidadeTotal +
                " | Contato: " + (contatoResponsavel != null ? contatoResponsavel : "N/D") +
                " | Localização: (Lat: " + String.format("%.4f", latitude) + ", Lon: " + String.format("%.4f", longitude) + ")";
    }
}