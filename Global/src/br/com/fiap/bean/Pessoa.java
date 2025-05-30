package br.com.fiap.bean;

import java.time.LocalDate;

/**
 * Representa uma pessoa no sistema, com dados pessoais e de situação emergencial.
 */
public class Pessoa {
    private String nome;
    private String localizacao;
    private String status;
    private LocalDate dataRegistro;
    private String tipoUsuario;

    public Pessoa(String nome, String localizacao) {
        this.nome = nome;
        this.localizacao = localizacao;
        this.status = "aguardando";
        this.dataRegistro = LocalDate.now();
        this.tipoUsuario = "morador";
    }

    public String getNome() {
        return nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getDataRegistro() {
        return dataRegistro;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public String toString() {
        return "Pessoa: " + nome +
                " | Localização: " + localizacao +
                " | Status: " + status +
                " | Tipo: " + tipoUsuario +
                " | Registro: " + dataRegistro;
    }
}
