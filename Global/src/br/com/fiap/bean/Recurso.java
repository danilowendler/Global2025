package br.com.fiap.bean;

public class Recurso {
    private String tipo;
    private String local;
    private boolean disponivel;

    public Recurso(String tipo, String local, boolean disponivel) {
        this.tipo = tipo;
        this.local = local;
        this.disponivel = disponivel;
    }

    public String getTipo() {
        return tipo;
    }

    public String getLocal() {
        return local;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    @Override
    public String toString() {
        return tipo + " disponível em " + local + (disponivel ? " ✅" : " ❌");
    }
}
