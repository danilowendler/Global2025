package br.com.fiap.bean;

/**
 * Representa o tipo de vegetação de uma área.
 */
public class TipoVegetacao {
    private int id;
    private String descricao;

    public TipoVegetacao(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    /**
     * Verifica se o tipo de vegetação é altamente inflamável.
     *
     * @return true se for inflamável, false caso contrário.
     */
    public boolean isInflamavel() {
        return descricao.toLowerCase().contains("seca") || descricao.toLowerCase().contains("gramínea");
    }

    @Override
    public String toString() {
        return descricao;
    }
}
