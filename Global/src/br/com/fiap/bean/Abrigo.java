package br.com.fiap.bean;

/**
 * Representa um abrigo específico para pessoas em situações de emergência.
 * Contém informações sobre nome, localização geográfica precisa, zona, capacidade,
 * vagas disponíveis, endereço e contato.
 */
public class Abrigo {
    private final String nomeAbrigo;
    private final double latitude;
    private final double longitude;
    private final int capacidadeTotal;
    private int vagasDisponiveis;
    private final String enderecoCompleto;
    private final String contatoResponsavel; // Opcional
    private final String zonaDoAbrigo; // Ex: "Zona Sul", "Zona Leste"

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
     * @param zonaDoAbrigo A zona da cidade onde o abrigo está localizado.
     */
    public Abrigo(String nomeAbrigo, double latitude, double longitude, int capacidadeTotal, int vagasDisponiveis, String enderecoCompleto, String contatoResponsavel, String zonaDoAbrigo) {
        this.nomeAbrigo = nomeAbrigo;
        this.latitude = latitude;
        this.longitude = longitude;
        this.capacidadeTotal = capacidadeTotal;
        this.setVagasDisponiveis(vagasDisponiveis); // Usa o setter para validação
        this.enderecoCompleto = enderecoCompleto;
        this.contatoResponsavel = contatoResponsavel;
        this.zonaDoAbrigo = zonaDoAbrigo;
    }

    // Getters


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
     * Retorna a zona onde o abrigo está localizado.
     * @return A zona do abrigo.
     */
    public String getZonaDoAbrigo() {
        return zonaDoAbrigo;
    }

    // Setters

    /**
     * Define o número de vagas disponíveis.
     * Garante que as vagas não fiquem negativas ou excedam a capacidade total.
     * @param vagasDisponiveis O novo número de vagas disponíveis.
     */
    public void setVagasDisponiveis(int vagasDisponiveis) {
        if (vagasDisponiveis < 0) {
            this.vagasDisponiveis = 0;
        } else this.vagasDisponiveis = Math.min(vagasDisponiveis, this.capacidadeTotal);
    }


    /**
     * Verifica se ainda existem vagas disponíveis no abrigo.
     * @return {@code true} se {@code vagasDisponiveis > 0}, {@code false} caso contrário.
     */
    public boolean temVagas() {
        return this.vagasDisponiveis > 0;
    }



    /**
     * Retorna uma representação em String do objeto Abrigo.
     * @return Uma {@code String} formatada com os dados do abrigo.
     */
    @Override
    public String toString() {
        return "🏠 Abrigo: " + nomeAbrigo +
                " | Endereço: " + enderecoCompleto + " (Zona: " + zonaDoAbrigo + ")" +
                " | Vagas: " + vagasDisponiveis + "/" + capacidadeTotal +
                " | Contato: " + (contatoResponsavel != null && !contatoResponsavel.isEmpty() ? contatoResponsavel : "N/D") +
                " | Localização Exata: (Lat: " + String.format("%.4f", latitude) + ", Lon: " + String.format("%.4f", longitude) + ")";
    }
}