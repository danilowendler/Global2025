package br.com.fiap.bean;

/**
 * Representa uma pessoa que já foi resgatada.
 */
public class PessoaResgatada extends Pessoa {
    private String status;

    public PessoaResgatada(String nome, String localizacao, String status) {
        super(nome, localizacao);
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "✅ Pessoa resgatada: " + getNome() + " | Localização: " + getLocalizacao() + " | Status: " + status;
    }
}

