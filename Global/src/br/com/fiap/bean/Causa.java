package br.com.fiap.bean;

/**
 * Representa a causa de um incêndio.
 */
public class Causa {
    private int id;
    private String descricao;

    public Causa(int id, String descricao) {
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
     * Verifica se a causa é de origem humana.
     *
     * @return true se for humana, false caso contrário.
     */
    public boolean isOrigemHumana() {
        return descricao.toLowerCase().contains("humana");
    }

    @Override
    public String toString() {
        return descricao;
    }
}
