package br.com.fiap.bean;

public class Pessoa {
    private String nome;
    private String localizacao;

    public Pessoa(String nome, String localizacao) {
        this.nome = nome;
        this.localizacao = localizacao;
    }

    public String getNome() {
        return nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    @Override
    public String toString() {
        return "Pessoa: " + nome + " | Localização: " + localizacao;
    }
}
