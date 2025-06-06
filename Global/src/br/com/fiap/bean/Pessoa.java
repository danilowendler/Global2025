package br.com.fiap.bean;

import java.time.LocalDate;

/**
 * Representa uma pessoa no sistema, com seus dados pessoais, status em uma situação
 * de emergência, tipo de usuário, data de registro, credenciais de login (simuladas),
 * localização descritiva e por zona, além de coordenadas geográficas precisas opcionais.
 */
public class Pessoa {
    private final String nome;
    private final String localizacaoDescritiva;
    private final String zona;
    private String status;
    private final LocalDate dataRegistro;
    private final String tipoUsuario;
    private String loginIdentificador;
    private String senha;
    private double latitude;
    private double longitude;

    /**
     * Construtor completo para Pessoa.
     * @param nome O nome completo da pessoa.
     * @param localizacaoDescritiva A localização descritiva.
     * @param zona A zona da cidade onde a pessoa se encontra.
     * @param loginIdentificador O identificador de login (username/email).
     * @param senha A senha (para fins de simulação).
     * @param tipoUsuario O tipo de usuário.
     */
    public Pessoa(String nome, String localizacaoDescritiva, String zona, String loginIdentificador, String senha, String tipoUsuario) {
        this.nome = nome;
        this.localizacaoDescritiva = localizacaoDescritiva;
        this.zona = zona;
        this.loginIdentificador = loginIdentificador;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
        this.status = "cadastrado";
        this.dataRegistro = LocalDate.now();
        this.latitude = 0.0;
        this.longitude = 0.0;
    }

    /**
     * Construtor simplificado para Pessoa.
     * @param nome O nome completo da pessoa.
     * @param localizacaoDescritiva A localização descritiva.
     * @param zona A zona da cidade onde a pessoa se encontra.
     */
    public Pessoa(String nome, String localizacaoDescritiva, String zona) {
        this.nome = nome;
        this.localizacaoDescritiva = localizacaoDescritiva;
        this.zona = zona;
        this.status = "aguardando";
        this.dataRegistro = LocalDate.now();
        this.tipoUsuario = "morador";
        this.latitude = 0.0;
        this.longitude = 0.0;
    }

    public String getNome() {
        return nome;
    }


    public String getLocalizacaoDescritiva() {
        return localizacaoDescritiva;
    }


    public String getZona() {
        return zona;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getLoginIdentificador() {
        return loginIdentificador;
    }


    public String getSenha() {
        return senha;
    }


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Retorna uma representação em String do objeto Pessoa.
     * @return Uma {@code String} formatada com os dados da pessoa.
     */
    public String toString() {
        String info = "Pessoa: " + nome;
        if (loginIdentificador != null && !loginIdentificador.isEmpty()) {
            info += " | Login: " + loginIdentificador;
        }
        info += " | Localização: " + localizacaoDescritiva;
        if (zona != null && !zona.isEmpty()) {
            info += " (Zona: " + zona + ")";
        }
        if (latitude != 0.0 || longitude != 0.0) {
            info += " (Lat: " + String.format("%.4f", latitude) +
                    ", Lon: " + String.format("%.4f", longitude) + ")";
        }
        info += " | Status: " + status;
        info += " | Tipo: " + tipoUsuario;
        info += " | Registro: " + dataRegistro;
        return info;
    }
}