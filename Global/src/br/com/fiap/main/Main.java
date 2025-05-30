package br.com.fiap.main;

import br.com.fiap.bean.*;
import java.util.Optional;
import java.util.Scanner;

/**
 * Classe principal da aplicação de emergência para queimadas.
 * Apresenta um menu interativo no console para que o usuário possa interagir
 * com as funcionalidades do sistema, como login, registro, busca por abrigos
 * próximos, visualização de recursos e recebimento de mensagens de apoio.
 */
public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static AutenticacaoServico authServico = new AutenticacaoServico();
    private static EmergenciaServico emergenciaServico;
    private static Pessoa pessoaLogada = null;

    // Repositórios para simulação de dados
    private static RecursoRepository recursoRepoGlobal = new RecursoRepository();
    private static AbrigoRepository abrigoRepoGlobal = new AbrigoRepository();


    /**
     * Ponto de entrada da aplicação.
     * Inicializa os serviços necessários, interage com o usuário através de um menu
     * via console e processa as opções escolhidas.
     *
     * @param args Argumentos de linha de comando (não utilizados nesta aplicação).
     */
    public static void main(String[] args) {
        inicializarDadosDeExemplo(); // Popula com alguns dados para teste
        emergenciaServico = new EmergenciaServico(recursoRepoGlobal, abrigoRepoGlobal);

        System.out.println("----------------------------------------------------------");
        System.out.println(" BEM-VINDO AO SISTEMA DE APOIO EMERGENCIAL - QUEIMADAS ");
        System.out.println("----------------------------------------------------------");

        loopPrincipalMenu();

        scanner.close();
        System.out.println("\n--- Aplicação Finalizada ---");
    }

    /**
     * Popula os repositórios com dados de exemplo para demonstração.
     */
    private static void inicializarDadosDeExemplo() {
        Recurso agua = new Recurso("Água Potável 1L", "Centro de Distribuição A", true);
        agua.setQuantidade(200);
        agua.setPrioridade(1);
        recursoRepoGlobal.adicionar(agua);

        Recurso kitHigiene = new Recurso("Kit de Higiene", "Posto Avançado B", true);
        kitHigiene.setQuantidade(150);
        kitHigiene.setPrioridade(2);
        recursoRepoGlobal.adicionar(kitHigiene);

        recursoRepoGlobal.adicionar(new Recurso("Cobertor", "Ginásio Municipal", false)); // Indisponível

        abrigoRepoGlobal.adicionar(new Abrigo("Abrigo Paz Eterna", -23.550520, -46.633303, 100, 75, "Rua da Consolação, 900, São Paulo", "João Silva - (11) 98765-4321"));
        abrigoRepoGlobal.adicionar(new Abrigo("Escola Segura ABC", -23.561398, -46.656356, 150, 15, "Av. Paulista, 1500, São Paulo", "Maria Oliveira - (11) 91234-5678"));
        abrigoRepoGlobal.adicionar(new Abrigo("Centro Comunitário Esperança", -23.547500, -46.573610, 80, 0, "Rua das Palmeiras, 30, Zona Leste", "Carlos Souza - (11) 99999-0000")); // Sem vagas
        abrigoRepoGlobal.adicionar(new Abrigo("Ginásio Refúgio", -23.570000, -46.690000, 200, 120, "Av. Morumbi, 7000, São Paulo", "Ana Costa - (11) 98888-1111"));

    }


    /**
     * Loop principal do menu da aplicação.
     */
    private static void loopPrincipalMenu() {
        int escolhaInicial;
        do {
            System.out.println("\n--- MENU INICIAL ---");
            System.out.println("1. 🆘 PRECISO DE AJUDA URGENTE / ENCONTRAR ABRIGO");
            System.out.println("2. 🔑 Fazer Login");
            System.out.println("3. 📝 Registrar Novo Usuário");
            System.out.println("4. 🚪 Acessar como Convidado (Informações Gerais)");
            System.out.println("0. ❌ Sair da Aplicação");
            System.out.print("Escolha uma opção: ");
            escolhaInicial = lerOpcaoNumerica();

            switch (escolhaInicial) {
                case 1:
                    ajudaUrgenteAbrigo();
                    break;
                case 2:
                    fazerLogin();
                    if (pessoaLogada != null) {
                        menuUsuarioLogado();
                    }
                    break;
                case 3:
                    registrarNovoUsuario();
                    break;
                case 4:
                    menuConvidado();
                    break;
                case 0:
                    System.out.println("\n🙏 Encerrando o aplicativo... Cuide-se e fique seguro!");
                    break;
                default:
                    System.out.println("⚠️ Opção inválida. Por favor, tente novamente.");
            }
        } while (escolhaInicial != 0);
    }

    /**
     * Fluxo para ajuda urgente, focando em encontrar abrigo.
     */
    private static void ajudaUrgenteAbrigo() {
        System.out.println("\n--- 🆘 AJUDA URGENTE - ENCONTRAR ABRIGO ---");
        System.out.println("Para encontrar o abrigo mais próximo, precisamos da sua localização.");
        System.out.print("Sua Latitude (ex: -23.5505): ");
        double lat = lerDouble();
        System.out.print("Sua Longitude (ex: -46.6333): ");
        double lon = lerDouble();

        if (lat == Double.MIN_VALUE || lon == Double.MIN_VALUE) { // Checa se a leitura falhou
            System.out.println("Localização inválida. Não foi possível buscar abrigos.");
            return;
        }


        Abrigo abrigoProximo = emergenciaServico.encontrarAbrigoMaisProximoDisponivel(lat, lon);

        if (abrigoProximo != null) {
            System.out.println("\n✅ Abrigo mais próximo com vagas encontrado:");
            System.out.println(abrigoProximo);
            System.out.println("Por favor, dirija-se ao local com segurança ou aguarde instruções das autoridades.");
        } else {
            System.out.println("\n❌ Infelizmente, nenhum abrigo com vagas foi encontrado próximo à sua localização neste momento.");
            System.out.println("Procure um local seguro e entre em contato com a Defesa Civil (199) ou Bombeiros (193).");
        }
        System.out.println("\nPressione Enter para voltar ao menu inicial...");
        scanner.nextLine();
    }

    /**
     * Processa o login do usuário.
     */
    private static void fazerLogin() {
        System.out.println("\n--- 🔑 LOGIN ---");
        System.out.print("Email/Identificador de login: ");
        String identificador = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Optional<Pessoa> pessoaOpt = authServico.login(identificador, senha);
        if (pessoaOpt.isPresent()) {
            pessoaLogada = pessoaOpt.get();
            System.out.println("Bem-vindo(a) de volta, " + pessoaLogada.getNome() + "!");
        } else {
            pessoaLogada = null;
        }
    }

    /**
     * Processa o registro de um novo usuário.
     */
    private static void registrarNovoUsuario() {
        System.out.println("\n--- 📝 REGISTRO DE NOVO USUÁRIO ---");
        System.out.print("Seu Nome Completo: ");
        String nome = scanner.nextLine();
        System.out.print("Seu Email (será seu login): ");
        String email = scanner.nextLine();
        System.out.print("Crie uma Senha: ");
        String senha = scanner.nextLine();
        System.out.print("Confirme a Senha: ");
        String confirmaSenha = scanner.nextLine();

        if (!senha.equals(confirmaSenha)) {
            System.out.println("❌ As senhas não coincidem. Tente novamente.");
            return;
        }

        System.out.print("Sua Localização Descritiva (ex: Bairro, Rua): ");
        String locDesc = scanner.nextLine();
        System.out.print("Sua Latitude (ex: -23.5505): ");
        double lat = lerDouble();
        System.out.print("Sua Longitude (ex: -46.6333): ");
        double lon = lerDouble();

        if (lat == Double.MIN_VALUE || lon == Double.MIN_VALUE) {
            System.out.println("Localização geográfica inválida. Registro não pode continuar com estes dados.");
            return;
        }

        System.out.print("Tipo de Usuário (morador, voluntário, etc.) [padrão: morador]: ");
        String tipoUser = scanner.nextLine();
        if (tipoUser.trim().isEmpty()) {
            tipoUser = "morador";
        }

        boolean sucesso = authServico.registrar(email, senha, nome, locDesc, lat, lon, tipoUser);
        if (sucesso) {
            System.out.println("Registro concluído! Agora você pode fazer login.");
        } else {
            System.out.println("Não foi possível completar o registro. Verifique os dados ou tente outro email.");
        }
    }


    /**
     * Menu de opções para usuários logados.
     */
    private static void menuUsuarioLogado() {
        int opcaoLogado;
        do {
            System.out.println("\n--- MENU PRINCIPAL (Usuário: " + pessoaLogada.getNome() + ") ---");
            System.out.println("1. 🆘 Encontrar Abrigo Mais Próximo (com minha localização salva)");
            System.out.println("2. 📦 Ver Recursos Gerais Disponíveis");
            System.out.println("3. 🏠 Ver Abrigos com Vagas");
            System.out.println("4. 💙 Receber Mensagem de Apoio");
            System.out.println("5. 📊 Contar Recursos Gerais Disponíveis");
            System.out.println("6. 📊 Contar Abrigos com Vagas");
            System.out.println("7. ❓ Verificar Tipo de Recurso Geral");
            System.out.println("8. 📱 Gerar Alerta Personalizado (simulação)");
            System.out.println("9. 👤 Ver Meus Dados");
            System.out.println("10. 🚶‍♀️ Simular Status Pessoa Resgatada");
            System.out.println("0. 🚪 Fazer Logout e Voltar ao Menu Inicial");
            System.out.print("Escolha uma opção: ");
            opcaoLogado = lerOpcaoNumerica();

            switch (opcaoLogado) {
                case 1:
                    if (pessoaLogada.getLatitude() != 0.0 || pessoaLogada.getLongitude() != 0.0) {
                        Abrigo abrigo = emergenciaServico.encontrarAbrigoMaisProximoDisponivel(pessoaLogada.getLatitude(), pessoaLogada.getLongitude());
                        if (abrigo != null) {
                            System.out.println("\n✅ Abrigo mais próximo com vagas (baseado na sua localização salva):");
                            System.out.println(abrigo);
                        } else {
                            System.out.println("\n❌ Nenhum abrigo com vagas encontrado próximo à sua localização salva.");
                        }
                    } else {
                        System.out.println("❌ Sua localização precisa não está cadastrada. Use a opção de ajuda urgente no menu inicial ou atualize seu perfil.");
                    }
                    break;
                case 2:
                    emergenciaServico.mostrarRecursosGeraisDisponiveis();
                    break;
                case 3:
                    emergenciaServico.mostrarAbrigosComVagas();
                    break;
                case 4:
                    emergenciaServico.oferecerMensagemDeApoio();
                    break;
                case 5:
                    System.out.println("Total de Recursos Gerais Disponíveis: " + emergenciaServico.contarRecursosGeraisDisponiveis());
                    break;
                case 6:
                    System.out.println("Total de Abrigos com Vagas: " + emergenciaServico.contarAbrigosComVagas());
                    break;
                case 7:
                    System.out.print("Digite o tipo de recurso geral para buscar: ");
                    String tipoBusca = scanner.nextLine();
                    boolean existe = emergenciaServico.existeRecursoGeralDoTipo(tipoBusca);
                    System.out.println(existe ? "✅ Recurso do tipo \"" + tipoBusca + "\" disponível!" : "❌ Nenhum recurso do tipo \"" + tipoBusca + "\" encontrado.");
                    break;
                case 8:
                    String alerta = emergenciaServico.gerarAlertaParaPessoa(pessoaLogada);
                    System.out.println(alerta);
                    break;
                case 9:
                    System.out.println(pessoaLogada);
                    break;
                case 10:
                    PessoaResgatada resgatada = new PessoaResgatada(pessoaLogada.getNome(), pessoaLogada.getLocalizacaoDescritiva(), "Acolhido(a) e seguro(a)");
                    System.out.println(resgatada);
                    break;
                case 0:
                    System.out.println("Logout realizado. Voltando ao menu inicial...");
                    pessoaLogada = null;
                    break;
                default:
                    System.out.println("⚠️ Opção inválida.");
            }
        } while (opcaoLogado != 0);
    }

    /**
     * Menu de opções para acesso de convidado.
     */
    private static void menuConvidado() {
        int opcaoConvidado;
        do {
            System.out.println("\n--- MENU CONVIDADO ---");
            System.out.println("1. 📦 Ver Recursos Gerais Disponíveis");
            System.out.println("2. 🏠 Ver Abrigos com Vagas");
            System.out.println("3. 💙 Receber Mensagem de Apoio");
            System.out.println("4. 📊 Contar Recursos Gerais Disponíveis");
            System.out.println("5. 📊 Contar Abrigos com Vagas");
            System.out.println("0. 🚪 Voltar ao Menu Inicial");
            System.out.print("Escolha uma opção: ");
            opcaoConvidado = lerOpcaoNumerica();

            switch (opcaoConvidado) {
                case 1:
                    emergenciaServico.mostrarRecursosGeraisDisponiveis();
                    break;
                case 2:
                    emergenciaServico.mostrarAbrigosComVagas();
                    break;
                case 3:
                    emergenciaServico.oferecerMensagemDeApoio();
                    break;
                case 4:
                    System.out.println("Total de Recursos Gerais Disponíveis: " + emergenciaServico.contarRecursosGeraisDisponiveis());
                    break;
                case 5:
                    System.out.println("Total de Abrigos com Vagas: " + emergenciaServico.contarAbrigosComVagas());
                    break;
                case 0:
                    System.out.println("Voltando ao menu inicial...");
                    break;
                default:
                    System.out.println("⚠️ Opção inválida.");
            }
        } while (opcaoConvidado != 0);
    }

    /**
     * Lê uma opção numérica do console, tratando InputMismatchException.
     * @return O número lido, ou -1 em caso de erro de formato.
     */
    private static int lerOpcaoNumerica() {
        try {
            String input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                return -99;
            }
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("‼️ ERRO: Entrada inválida. Por favor, digite um número.");
            return -99;
        }
    }

    /**
     * Lê um valor double do console, tratando InputMismatchException.
     * @return O double lido, ou Double.MIN_VALUE em caso de erro de formato.
     */
    private static double lerDouble() {
        try {
            String input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                System.out.println("⚠️ Entrada vazia não permitida para coordenadas.");
                return Double.MIN_VALUE;
            }
            return Double.parseDouble(input.replace(",", ".")); // Aceita vírgula como separador decimal
        } catch (NumberFormatException e) {
            System.out.println("‼️ ERRO: Formato de número inválido.");
            return Double.MIN_VALUE;
        }
    }
}