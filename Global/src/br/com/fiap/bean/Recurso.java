package br.com.fiap.bean;

/**
 * Representa um recurso físico utilizado em situações de emergência.
 */
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

    /**
     * Verifica se o recurso está disponível.
     * @return true se estiver disponível, false caso contrário.
     */
    public boolean verificarDisponibilidade() {
        return disponivel;
    }

    /**
     * Verifica a disponibilidade considerando recursos inativos.
     * @param incluirInativos se true, considera recursos inativos também.
     * @return true se for considerado disponível, conforme o parâmetro.
     */
    public boolean verificarDisponibilidade(boolean incluirInativos) {
        return incluirInativos || disponivel;
    }
}
