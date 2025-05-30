package br.com.fiap.bean;

import java.time.LocalDate;
import java.util.Objects; // Importado para hashCode e equals

/**
 * Representa uma pessoa no sistema, com seus dados pessoais, status em uma situação
 * de emergência, tipo de usuário, data de registro, credenciais de login (simuladas)
 * e localização geográfica precisa.
 */
public class Pessoa {
    private String nome;
    private String localizacaoDescritiva; // Mantém a localização descritiva
    private String status;
    private LocalDate dataRegistro;
    private String tipoUsuario;

    // Novos atributos para login (simulados para este exemplo)
    private String loginIdentificador; // Ex: email ou username
    private String senha; // ATENÇÃO: Em produção, armazenar HASH e SAL, nunca a senha pura!

    // Novos atributos para localização precisa
    private double latitude;
    private double longitude;

    /**
     * Constrói uma nova instância de Pessoa com nome e localização descritiva.
     * Outros campos são inicializados com valores padrão.
     *
     * @param nome O nome completo da pessoa.
     * @param localizacaoDescritiva A localização descritiva atual ou de referência da pessoa.
     */
    public Pessoa(String nome, String localizacaoDescritiva) {
        this.nome = nome;
        this.localizacaoDescritiva = localizacaoDescritiva;
        this.status = "aguardando";
        this.dataRegistro = LocalDate.now();
        this.tipoUsuario = "morador";
        // Latitude e longitude podem ser definidas posteriormente ou via construtor mais completo
        this.latitude = 0.0; // Valor padrão
        this.longitude = 0.0; // Valor padrão
    }

    /**
     * Construtor completo para Pessoa, incluindo credenciais (simuladas) e localização precisa.
     *
     * @param nome O nome completo da pessoa.
     * @param localizacaoDescritiva A localização descritiva.
     * @param loginIdentificador O identificador de login (username/email).
     * @param senha A senha (para fins de simulação; em produção, use hash).
     * @param latitude A latitude geográfica.
     * @param longitude A longitude geográfica.
     * @param tipoUsuario O tipo de usuário.
     */
    public Pessoa(String nome, String localizacaoDescritiva, String loginIdentificador, String senha, double latitude, double longitude, String tipoUsuario) {
        this.nome = nome;
        this.localizacaoDescritiva = localizacaoDescritiva;
        this.loginIdentificador = loginIdentificador;
        this.senha = senha; // SIMULAÇÃO!
        this.latitude = latitude;
        this.longitude = longitude;
        this.tipoUsuario = tipoUsuario;
        this.status = "cadastrado";
        this.dataRegistro = LocalDate.now();
    }


    // Getters e Setters para todos os atributos...

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacaoDescritiva() {
        return localizacaoDescritiva;
    }

    public void setLocalizacaoDescritiva(String localizacaoDescritiva) {
        this.localizacaoDescritiva = localizacaoDescritiva;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDataRegistro() {
        return dataRegistro;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getLoginIdentificador() {
        return loginIdentificador;
    }

    public void setLoginIdentificador(String loginIdentificador) {
        this.loginIdentificador = loginIdentificador;
    }

    /**
     * Retorna a senha (simulada).
     * ATENÇÃO: Em um sistema real, este método não deveria existir ou deveria
     * lidar com hashes de forma segura.
     * @return A senha (simulada).
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Define a senha (simulada).
     * ATENÇÃO: Em um sistema real, este método deveria processar a senha para
     * gerar um hash e sal seguros.
     * @param senha A nova senha (simulada).
     */
    public void setSenha(String senha) {
        this.senha = senha; // SIMULAÇÃO!
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

    @Override
    public String toString() {
        return "Pessoa: " + nome +
                " | Login: " + (loginIdentificador != null ? loginIdentificador : "N/A") +
                " | Localização: " + localizacaoDescritiva +
                " (Lat: " + String.format("%.4f", latitude) + ", Lon: " + String.format("%.4f", longitude) + ")" +
                " | Status: " + status +
                " | Tipo: " + tipoUsuario +
                " | Registro: " + dataRegistro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(loginIdentificador, pessoa.loginIdentificador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loginIdentificador);
    }
}