package br.com.fiap.bean;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Representa uma pessoa no sistema, com seus dados pessoais, status em uma situação
 * de emergência, tipo de usuário, data de registro, credenciais de login (simuladas),
 * localização descritiva e por zona, além de coordenadas geográficas precisas opcionais.
 */
public class Pessoa {
    private final String nome;
    private final String localizacaoDescritiva; // Endereço, ponto de referência
    private final String zona; // Ex: "Zona Sul", "Zona Leste"
    private String status;
    private final LocalDate dataRegistro;
    private final String tipoUsuario;

    // Atributos para login (simulados para este exemplo)
    private String loginIdentificador; // Ex: email ou username
    private String senha; // ATENÇÃO: Em produção, armazenar HASH e SAL, nunca a senha pura!

    // Atributos para localização precisa (opcional ou para uso interno/futuro)
    private double latitude;
    private double longitude;

    /**
     * Constrói uma nova instância de Pessoa com nome, localização descritiva e zona.
     * Outros campos são inicializados com valores padrão.
     *
     * @param nome O nome completo da pessoa.
     * @param localizacaoDescritiva A localização descritiva (ex: rua, bairro).
     * @param zona A zona da cidade onde a pessoa se encontra (ex: "Zona Sul").
     */
    public Pessoa(String nome, String localizacaoDescritiva, String zona) {
        this.nome = nome;
        this.localizacaoDescritiva = localizacaoDescritiva;
        this.zona = zona;
        this.status = "aguardando";
        this.dataRegistro = LocalDate.now();
        this.tipoUsuario = "morador";
        this.latitude = 0.0; // Valor padrão, pode ser definido depois
        this.longitude = 0.0; // Valor padrão, pode ser definido depois
    }

    /**
     * Construtor completo para Pessoa, incluindo credenciais (simuladas), zona e tipo de usuário.
     * Latitude e longitude são opcionais e podem ser definidas depois.
     *
     * @param nome O nome completo da pessoa.
     * @param localizacaoDescritiva A localização descritiva.
     * @param zona A zona da cidade onde a pessoa se encontra.
     * @param loginIdentificador O identificador de login (username/email).
     * @param senha A senha (para fins de simulação; em produção, use hash).
     * @param tipoUsuario O tipo de usuário.
     */
    public Pessoa(String nome, String localizacaoDescritiva, String zona, String loginIdentificador, String senha, String tipoUsuario) {
        this.nome = nome;
        this.localizacaoDescritiva = localizacaoDescritiva;
        this.zona = zona;
        this.loginIdentificador = loginIdentificador;
        this.senha = senha; // SIMULAÇÃO!
        this.tipoUsuario = tipoUsuario;
        this.status = "cadastrado"; // Status padrão para um usuário registrado
        this.dataRegistro = LocalDate.now();
        this.latitude = 0.0; // Inicializa como 0, pode ser atualizado
        this.longitude = 0.0; // Inicializa como 0, pode ser atualizado
    }

    // Getters e Setters

    /**
     * Retorna o nome da pessoa.
     * @return O nome da pessoa.
     */
    public String getNome() {
        return nome;
    }


    /**
     * Retorna a localização descritiva da pessoa.
     * @return A localização descritiva.
     */
    public String getLocalizacaoDescritiva() {
        return localizacaoDescritiva;
    }


    /**
     * Retorna a zona da pessoa.
     * @return A zona.
     */
    public String getZona() {
        return zona;
    }


    /**
     * Retorna o status atual da pessoa na emergência.
     * @return O status da pessoa.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Define o status da pessoa.
     * @param status O novo status da pessoa.
     */
    public void setStatus(String status) {
        this.status = status;
    }


    /**
     * Retorna o identificador de login da pessoa.
     * @return O identificador de login.
     */
    public String getLoginIdentificador() {
        return loginIdentificador;
    }


    /**
     * Retorna a senha (simulada).
     * @return A senha (simulada).
     */
    public String getSenha() {
        return senha;
    }



    /**
     * Retorna a latitude geográfica da pessoa.
     * @return A latitude.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Define a latitude geográfica da pessoa.
     * @param latitude A nova latitude.
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Retorna a longitude geográfica da pessoa.
     * @return A longitude.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Define a longitude geográfica da pessoa.
     * @param longitude A nova longitude.
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Retorna uma representação em String do objeto Pessoa.
     * @return Uma {@code String} formatada com os dados da pessoa.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pessoa: ").append(nome);
        if (loginIdentificador != null && !loginIdentificador.isEmpty()) {
            sb.append(" | Login: ").append(loginIdentificador);
        }
        sb.append(" | Localização: ").append(localizacaoDescritiva);
        if (zona != null && !zona.isEmpty()) {
            sb.append(" (Zona: ").append(zona).append(")");
        }
        if (latitude != 0.0 || longitude != 0.0) { // Mostrar lat/lon se não forem padrão (0.0)
            sb.append(" (Lat: ").append(String.format("%.4f", latitude))
                    .append(", Lon: ").append(String.format("%.4f", longitude)).append(")");
        }
        sb.append(" | Status: ").append(status);
        sb.append(" | Tipo: ").append(tipoUsuario);
        sb.append(" | Registro: ").append(dataRegistro);
        return sb.toString();
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