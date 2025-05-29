package br.com.fiap.bean;

/**
 * Representa uma pessoa no sistema, com nome e localização.
 */
public class Pessoa {
    private String nome;
    private String localizacao;

    /**
     * Construtor da classe Pessoa.
     * @param nome nome da pessoa
     * @param localizacao localização atual da pessoa
     */
    public Pessoa(String nome, String localizacao) {
        this.nome = nome;
        this.localizacao = localizacao;
    }

    /**
     * Retorna o nome da pessoa.
     * @return nome da pessoa
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna a localização da pessoa.
     * @return localização atual
     */
    public String getLocalizacao() {
        return localizacao;
    }

    @Override
    public String toString() {
        return "Pessoa: " + nome + " | Localização: " + localizacao;
    }
}
