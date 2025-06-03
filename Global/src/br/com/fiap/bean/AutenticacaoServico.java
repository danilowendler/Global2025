package br.com.fiap.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Serviço responsável pela autenticação e registro de usuários (Pessoas).
 */
public class AutenticacaoServico {

    private static final List<Pessoa> usuariosRegistrados = new ArrayList<>();

    static {
        Pessoa admin = new Pessoa("Admin FIAP", "Sede FIAP", "Zona Central", "admin@fiap.com.br", "admin123", "administrador");
        admin.setLatitude(-23.5739);
        admin.setLongitude(-46.6960);
        usuariosRegistrados.add(admin);

        Pessoa usuarioTeste = new Pessoa("Danilo Wendler", "Rua Teste, 123", "Zona Sul", "danilo@fiap.com.br", "senha123", "morador");
        usuarioTeste.setLatitude(-23.5500);
        usuarioTeste.setLongitude(-46.6300);
        usuariosRegistrados.add(usuarioTeste);
    }

    /**
     * Tenta autenticar um usuário com base no identificador de login e senha.
     * Compara o identificador e a senha (simulada, texto puro) com os usuários registrados.
     *
     * @param loginIdentificador O identificador de login (username/email) do usuário.
     * @param senha A senha fornecida pelo usuário.
     * @return Um {@code Optional<Pessoa>} contendo o objeto Pessoa se a autenticação
     * for bem-sucedida, ou um {@code Optional.empty()} caso contrário.
     */
    public Optional<Pessoa> login(String loginIdentificador, String senha) {
        if (loginIdentificador == null || senha == null) {
            return Optional.empty();
        }
        for (Pessoa p : usuariosRegistrados) {
            // Em um sistema real: comparar hash da senha fornecida + sal com o hash armazenado
            if (loginIdentificador.equalsIgnoreCase(p.getLoginIdentificador()) && senha.equals(p.getSenha())) {
                System.out.println("Login bem-sucedido para: " + p.getNome());
                return Optional.of(p);
            }
        }
        System.out.println("Falha no login: Identificador ou senha inválidos.");
        return Optional.empty();
    }

    /**
     * Registra um novo usuário (Pessoa) no sistema.
     * Verifica se o identificador de login já está em uso antes de registrar.
     * A senha é armazenada em texto puro nesta simulação.
     *
     * @param loginIdentificador O identificador de login (username/email) para o novo usuário.
     * @param senha A senha para o novo usuário.
     * @param nome O nome completo da pessoa.
     * @param localizacaoDescritiva A localização descritiva da pessoa.
     * @param zona A zona da cidade onde a pessoa se encontra.
     * @param latitude A latitude geográfica da pessoa (opcional, pode ser 0.0).
     * @param longitude A longitude geográfica da pessoa (opcional, pode ser 0.0).
     * @param tipoUsuario O tipo de usuário (ex: "morador").
     * @return {@code true} se o registro for bem-sucedido, {@code false} se o identificador de login já existir ou dados essenciais estiverem faltando.
     */
    public boolean registrar(String loginIdentificador, String senha, String nome, String localizacaoDescritiva, String zona, double latitude, double longitude, String tipoUsuario) {
        if (loginIdentificador == null || loginIdentificador.trim().isEmpty() ||
                senha == null || senha.trim().isEmpty() ||
                nome == null || nome.trim().isEmpty() ||
                zona == null || zona.trim().isEmpty()) {
            System.out.println("Registro falhou: Login, senha, nome e zona não podem ser vazios.");
            return false;
        }

        for (Pessoa p : usuariosRegistrados) {
            if (loginIdentificador.equalsIgnoreCase(p.getLoginIdentificador())) {
                System.out.println("Registro falhou: O login '" + loginIdentificador + "' já está em uso.");
                return false;
            }
        }

        Pessoa novaPessoa = new Pessoa(nome, localizacaoDescritiva, zona, loginIdentificador, senha, tipoUsuario);
        novaPessoa.setLatitude(latitude);
        novaPessoa.setLongitude(longitude);
        usuariosRegistrados.add(novaPessoa);
        System.out.println("Usuário '" + loginIdentificador + "' registrado com sucesso!");
        return true;
    }

}