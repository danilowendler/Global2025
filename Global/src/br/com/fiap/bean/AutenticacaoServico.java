package br.com.fiap.bean;

/**
 * Serviço responsável pela autenticação e registro de usuários (Pessoas).
 * Esta implementação é uma SIMULAÇÃO para fins didáticos, utilizando um array em memória
 * para armazenar os usuários e senhas em texto puro.
 * Em um sistema de produção, utilize mecanismos seguros de hashing e salting
 * para senhas e persista os dados em um banco de dados real.
 */
public class AutenticacaoServico {

    private static final Pessoa[] usuariosRegistrados = new Pessoa[50];
    private static int proximoIndiceLivre = 0; // Para controlar o número de usuários registrados

    static {
        Pessoa admin = new Pessoa("Admin FIAP", "Sede FIAP", "Zona Central", "admin@fiap.com.br", "admin123", "administrador");
        admin.setLatitude(-23.5739);
        admin.setLongitude(-46.6960);
        adicionarUsuarioParaTeste(admin); // Usa o método de adicionar para popular

        Pessoa usuarioTeste = new Pessoa("Danilo Wendler", "Rua Teste, 123", "Zona Sul", "danilo@fiap.com.br", "senha123", "morador");
        usuarioTeste.setLatitude(-23.5500);
        usuarioTeste.setLongitude(-46.6300);
        adicionarUsuarioParaTeste(usuarioTeste);
    }

    /**
     * Tenta autenticar um usuário com base no identificador de login e senha.
     * Utiliza um laço 'for' para buscar o usuário na lista em memória.
     * @param loginIdentificador O identificador de login (username/email) do usuário.
     * @param senha A senha fornecida pelo usuário.
     * @return O objeto {@code Pessoa} se a autenticação for bem-sucedida,
     * ou {@code null} caso contrário.
     */
    public Pessoa login(String loginIdentificador, String senha) {
        if (loginIdentificador == null || senha == null) {
            return null;
        }

        // Itera sobre o array de usuários registrados (apenas até o último índice preenchido)
        for (int i = 0; i < proximoIndiceLivre; i++) {
            Pessoa p = usuariosRegistrados[i];
            if (loginIdentificador.equalsIgnoreCase(p.getLoginIdentificador()) && senha.equals(p.getSenha())) {
                System.out.println("Login bem-sucedido para: " + p.getNome());
                return p;
            }
        }

        System.out.println("Falha no login: Identificador ou senha inválidos.");
        return null;
    }

    /**
     * Registra um novo usuário (Pessoa) no sistema.
     * Verifica se o identificador de login já está em uso antes de registrar.
     * @param loginIdentificador O identificador de login (username/email) para o novo usuário.
     * @param senha A senha para o novo usuário.
     * @param nome O nome completo da pessoa.
     * @param localizacaoDescritiva A localização descritiva da pessoa.
     * @param zona A zona da cidade onde a pessoa se encontra.
     * @param latitude A latitude geográfica da pessoa (opcional, pode ser 0.0).
     * @param longitude A longitude geográfica da pessoa (opcional, pode ser 0.0).
     * @param tipoUsuario O tipo de usuário (ex: "morador").
     * @return {@code true} se o registro for bem-sucedido, {@code false} se o identificador de login já existir,
     * os dados essenciais estiverem faltando, ou se não houver mais espaço no repositório.
     */
    public boolean registrar(String loginIdentificador, String senha, String nome, String localizacaoDescritiva, String zona, double latitude, double longitude, String tipoUsuario) {
        if (loginIdentificador == null || loginIdentificador.trim().isEmpty() ||
                senha == null || senha.trim().isEmpty() ||
                nome == null || nome.trim().isEmpty() ||
                zona == null || zona.trim().isEmpty()) {
            System.out.println("Registro falhou: Login, senha, nome e zona não podem ser vazios.");
            return false;
        }

        if (proximoIndiceLivre >= usuariosRegistrados.length) {
            System.out.println("Registro falhou: O sistema atingiu o número máximo de usuários.");
            return false;
        }

        for (int i = 0; i < proximoIndiceLivre; i++) {
            if (loginIdentificador.equalsIgnoreCase(usuariosRegistrados[i].getLoginIdentificador())) {
                System.out.println("Registro falhou: O login '" + loginIdentificador + "' já está em uso.");
                return false;
            }
        }

        Pessoa novaPessoa = new Pessoa(nome, localizacaoDescritiva, zona, loginIdentificador, senha, tipoUsuario);
        novaPessoa.setLatitude(latitude);
        novaPessoa.setLongitude(longitude);

        usuariosRegistrados[proximoIndiceLivre] = novaPessoa;
        proximoIndiceLivre++;

        System.out.println("Usuário '" + loginIdentificador + "' registrado com sucesso!");
        return true;
    }

    /**
     * Método auxiliar usado para popular a lista inicial de usuários de teste.
     * @param pessoa O objeto Pessoa a ser adicionado.
     */
    private static void adicionarUsuarioParaTeste(Pessoa pessoa) {
        if (pessoa != null && proximoIndiceLivre < usuariosRegistrados.length) {
            usuariosRegistrados[proximoIndiceLivre] = pessoa;
            proximoIndiceLivre++;
        }
    }
}