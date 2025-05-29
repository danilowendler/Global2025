package br.com.fiap.bean;

/**
 * Representa uma equipe de combate a incêndios.
 */
public class Equipe {
    private String nome;
    private String orgao;
    private String contato;

    public Equipe(String nome, String orgao, String contato) {
        this.nome = nome;
        this.orgao = orgao;
        this.contato = contato;
    }

    public String getNome() {
        return nome;
    }

    public String getOrgao() {
        return orgao;
    }

    public String getContato() {
        return contato;
    }

    /**
     * Verifica se a equipe pertence a um órgão governamental.
     *
     * @return true se for governamental, false caso contrário.
     */
    public boolean isGovernamental() {
        return orgao.toLowerCase().contains("ibama") || orgao.toLowerCase().contains("defesa civil");
    }

    @Override
    public String toString() {
        return nome + " (" + orgao + ")";
    }
}
