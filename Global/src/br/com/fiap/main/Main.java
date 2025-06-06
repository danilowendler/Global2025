package br.com.fiap.main;

import br.com.fiap.bean.*;

import java.util.Scanner;

/**
 * Classe principal da aplicação de emergência para queimadas.
 * Apresenta um menu interativo no console para que o usuário possa interagir
 * com as funcionalidades do sistema, como login, registro, busca por abrigos
 * próximos, visualização de recursos e recebimento de mensagens de apoio.
 * Esta versão foi refatorada para utilizar arrays e conceitos fundamentais de Java.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final AutenticacaoServico authServico = new AutenticacaoServico();
    private static EmergenciaServico emergenciaServico;
    private static Pessoa pessoaLogada = null;
    private static final RecursoRepository recursoRepoGlobal = new RecursoRepository();
    private static final AbrigoRepository abrigoRepoGlobal = new AbrigoRepository();


    /**
     * Ponto de entrada da aplicação.
     * @param args Argumentos de linha de comando (não utilizados).
     */
    public static void main(String[] args) {
        inicializarDadosDeExemplo();
        emergenciaServico = new EmergenciaServico(recursoRepoGlobal, abrigoRepoGlobal);

        System.out.println("---------------------------------------------------------");
        System.out.println(" BEM-VINDO AO SISTEMA DE APOIO EMERGENCIAL - QUEIMADAS ");
        System.out.println("---------------------------------------------------------");

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
        recursoRepoGlobal.adicionar(new Recurso("Cobertor", "Ginásio Municipal", false));

        abrigoRepoGlobal.adicionar(new Abrigo("Abrigo Paz Eterna", -23.550520, -46.633303, 100, 75, "Rua da Consolação, 900, São Paulo", "João Silva - (11) 98765-4321", "Zona Central"));
        abrigoRepoGlobal.adicionar(new Abrigo("Escola Segura ABC", -23.561398, -46.656356, 150, 15, "Av. Paulista, 1500, São Paulo", "Maria Oliveira - (11) 91234-5678", "Zona Central"));
        abrigoRepoGlobal.adicionar(new Abrigo("Centro Comunitário Esperança Leste", -23.547500, -46.573610, 80, 0, "Rua das Palmeiras, 30, Zona Leste", "Carlos Souza - (11) 99999-0000", "Zona Leste"));
        abrigoRepoGlobal.adicionar(new Abrigo("Ginásio Refúgio Sul", -23.590000, -46.690000, 200, 120, "Av. Santo Amaro, 7000, São Paulo", "Ana Costa - (11) 98888-1111", "Zona Sul"));
        abrigoRepoGlobal.adicionar(new Abrigo("Lar Acolhedor Norte", -23.500000, -46.620000, 60, 30, "Rua Voluntários da Pátria, 4000, São Paulo", "Pedro Lima - (11) 97777-2222", "Zona Norte"));
        abrigoRepoGlobal.adicionar(new Abrigo("Refúgio Oeste", -23.5547, -46.7260, 90, 50, "Av. Corifeu de Azevedo Marques, 1000, São Paulo", "Beatriz Alves - (11) 96666-3333", "Zona Oeste"));

    }

    /**
     * Loop principal do menu da aplicação.
     */
    private static void loopPrincipalMenu() {
        int escolhaInicial;
        do {
            System.out.println("\n--- MENU INICIAL ---");
            System.out.println("1. 🆘 PRECISO DE AJUDA URGENTE / ENCONTRAR ABRIGO POR ZONA");
            System.out.println("2. 🔑 Fazer Login");
            System.out.println("3. 📝 Registrar Novo Usuário");
            System.out.println("4. 🚪 Acessar como Convidado (Informações Gerais)");
            System.out.println("0. ❌ Sair da Aplicação");
            System.out.print("Escolha uma opção: ");
            escolhaInicial = lerOpcaoNumerica();

            switch (escolhaInicial) {
                case 1:
                    ajudaUrgenteAbrigoPorZona();
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
                    break;
                default:
                    System.out.println("⚠️ Opção inválida. Por favor, tente novamente.");
            }
        } while (escolhaInicial != 0);
    }

    /**
     * Fluxo para ajuda urgente, focando em encontrar abrigo por ZONA.
     */
    private static void ajudaUrgenteAbrigoPorZona() {
        System.out.println("\n--- 🆘 AJUDA URGENTE - ENCONTRAR ABRIGO POR ZONA ---");
        System.out.println("Para encontrar abrigos, por favor, informe a Zona onde você está ou para onde deseja ir.");
        System.out.println("Zonas comuns em SP: Zona Sul, Zona Leste, Zona Norte, Zona Oeste, Zona Central");
        System.out.print("Digite a Zona: ");
        String zonaUsuario = scanner.nextLine().trim();

        if (zonaUsuario.isEmpty()) {
            System.out.println("⚠️ Zona não informada. Não foi possível buscar abrigos.");
            System.out.println("\nPressione Enter para voltar ao menu inicial...");
            scanner.nextLine();
            return;
        }

        Abrigo[] abrigosNaZona = emergenciaServico.listarAbrigosDisponiveisPorZona(zonaUsuario);

        if (abrigosNaZona.length > 0) {
            System.out.println("\n✅ Abrigos com vagas encontrados na " + zonaUsuario + ":");
            for (Abrigo abrigo : abrigosNaZona) {
                System.out.println("----------------------------------------");
                System.out.println(abrigo);
            }
            System.out.println("----------------------------------------");
            System.out.println("Por favor, verifique o mais conveniente e dirija-se ao local com segurança ou aguarde instruções das autoridades.");
        } else {
            System.out.println("\n❌ Infelizmente, nenhum abrigo com vagas foi encontrado na " + zonaUsuario + " neste momento.");
            System.out.println("Procure um local seguro e entre em contato com a Defesa Civil (199) ou Bombeiros (193).");
        }
        System.out.println("\nPressione Enter para voltar ao menu inicial...");
        scanner.nextLine();
    }

    /**
     * Processa o login do usuário, agora esperando um objeto Pessoa ou null.
     */
    private static void fazerLogin() {
        System.out.println("\n--- 🔑 LOGIN ---");
        System.out.print("Email/Identificador de login: ");
        String identificador = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        pessoaLogada = authServico.login(identificador, senha);

        if (pessoaLogada != null) {
            System.out.println("Bem-vindo(a) de volta, " + pessoaLogada.getNome() + "!");
        }
    }

    /**
     * Processa o registro de um novo usuário, incluindo a ZONA.
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
        System.out.print("Sua Zona (ex: Zona Sul, Zona Leste): ");
        String zona = scanner.nextLine().trim();
        if (zona.isEmpty()) {
            System.out.println("❌ A Zona é obrigatória para o registro. Tente novamente.");
            return;
        }

        double lat = 0.0;
        double lon = 0.0;
        System.out.print("Deseja informar Latitude/Longitude agora? (S para Sim, qualquer outra tecla para Não): ");
        if(scanner.nextLine().trim().equalsIgnoreCase("S")){
            System.out.print("Sua Latitude (ex: -23.5505): ");
            lat = lerDouble();
            System.out.print("Sua Longitude (ex: -46.6333): ");
            lon = lerDouble();
            if (lat == Double.MIN_VALUE || lon == Double.MIN_VALUE) {
                System.out.println("⚠️ Coordenadas inválidas ou não fornecidas, serão ignoradas.");
                lat = 0.0; lon = 0.0;
            }
        }

        System.out.print("Tipo de Usuário (morador, voluntário, etc.) [padrão: morador]: ");
        String tipoUser = scanner.nextLine().trim();
        if (tipoUser.isEmpty()) {
            tipoUser = "morador";
        }

        boolean sucesso = authServico.registrar(email, senha, nome, locDesc, zona, lat, lon, tipoUser);
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
            System.out.println("\n--- MENU PRINCIPAL (Usuário: " + pessoaLogada.getNome() +
                    (pessoaLogada.getZona() != null && !pessoaLogada.getZona().isEmpty() ? " - Zona: " + pessoaLogada.getZona() : "") +
                    ") ---");
            System.out.println("1. 🆘 Encontrar Abrigos Disponíveis na Minha Zona");
            System.out.println("2. 🗺️ Encontrar Abrigo Mais Próximo (por Lat/Lon, se cadastradas)");
            System.out.println("3. 📦 Ver Recursos Gerais Disponíveis");
            System.out.println("4. 🏠 Ver Todos os Abrigos com Vagas");
            System.out.println("5. 💙 Receber Mensagem de Apoio");
            System.out.println("6. 📊 Contar Recursos Gerais Disponíveis");
            System.out.println("7. 📊 Contar Abrigos com Vagas");
            System.out.println("8. ❓ Verificar Tipo de Recurso Geral");
            System.out.println("9. 📱 Gerar Alerta Personalizado");
            System.out.println("10. 👤 Ver Meus Dados");
            System.out.println("11.🚶‍♀️ Simular Status Pessoa Resgatada");
            System.out.println("0. 🚪 Fazer Logout e Voltar ao Menu Inicial");
            System.out.print("Escolha uma opção: ");
            opcaoLogado = lerOpcaoNumerica();

            switch (opcaoLogado) {
                case 1:
                    if (pessoaLogada.getZona() != null && !pessoaLogada.getZona().isEmpty()) {
                        Abrigo[] abrigos = emergenciaServico.listarAbrigosDisponiveisPorZona(pessoaLogada.getZona());
                        if (abrigos.length > 0) {
                            System.out.println("\n✅ Abrigos com vagas encontrados na sua zona (" + pessoaLogada.getZona() + "):");
                            for(Abrigo abrigo : abrigos) {
                                System.out.println(abrigo);
                            }
                        } else {
                            System.out.println("\n❌ Nenhum abrigo com vagas encontrado na sua zona (" + pessoaLogada.getZona() + ").");
                        }
                    } else {
                        System.out.println("❌ Sua zona não está cadastrada. Por favor, informe sua zona para esta busca.");
                        ajudaUrgenteAbrigoPorZona();
                    }
                    break;
                case 2:
                    if (pessoaLogada.getLatitude() != 0.0 || pessoaLogada.getLongitude() != 0.0) {
                        Abrigo abrigo = emergenciaServico.encontrarAbrigoMaisProximoDisponivelPorCoordenadas(pessoaLogada.getLatitude(), pessoaLogada.getLongitude());
                        if (abrigo != null) {
                            System.out.println("\n✅ Abrigo mais próximo com vagas (baseado na sua localização Lat/Lon salva):");
                            System.out.println(abrigo);
                            System.out.println("   Distância aproximada: " + String.format("%.2f", CalculadoraDistancia.calcular(pessoaLogada.getLatitude(), pessoaLogada.getLongitude(), abrigo.getLatitude(), abrigo.getLongitude())) + " km");
                        } else {
                            System.out.println("\n❌ Nenhum abrigo com vagas encontrado próximo à sua localização Lat/Lon salva.");
                        }
                    } else {
                        System.out.println("❌ Sua localização precisa (latitude/longitude) não está cadastrada.");
                        System.out.println("   Para usar esta função, sua Lat/Lon precisa ser informada (ex: durante o registro ou uma futura atualização de perfil).");
                    }
                    break;
                case 3:
                    emergenciaServico.mostrarRecursosGeraisDisponiveis();
                    break;
                case 4:
                    emergenciaServico.mostrarAbrigosComVagas();
                    break;
                case 5:
                    emergenciaServico.oferecerMensagemDeApoio();
                    break;
                case 6:
                    System.out.println("Total de Recursos Gerais Disponíveis: " + emergenciaServico.contarRecursosGeraisDisponiveis());
                    break;
                case 7:
                    System.out.println("Total de Abrigos com Vagas: " + emergenciaServico.contarAbrigosComVagas());
                    break;
                case 8:
                    System.out.print("Digite o tipo de recurso geral para buscar: ");
                    String tipoBusca = scanner.nextLine();
                    boolean existe = emergenciaServico.existeRecursoGeralDoTipo(tipoBusca);
                    System.out.println(existe ? "✅ Recurso do tipo \"" + tipoBusca + "\" disponível!" : "❌ Nenhum recurso do tipo \"" + tipoBusca + "\" encontrado.");
                    break;
                case 9:
                    String alerta = emergenciaServico.gerarAlertaParaPessoa(pessoaLogada);
                    System.out.println("\n📱 Alerta Gerado:");
                    System.out.println(alerta);
                    break;
                case 10:
                    System.out.println("\n👤 Seus Dados Cadastrados:");
                    System.out.println(pessoaLogada);
                    break;
                case 11:
                    System.out.println("\n🔄 Simulação de Resgate:");
                    String zonaResgatada = pessoaLogada.getZona() != null ? pessoaLogada.getZona() : "Zona Desconhecida";
                    PessoaResgatada resgatada = new PessoaResgatada(
                            pessoaLogada.getNome(),
                            pessoaLogada.getLocalizacaoDescritiva(),
                            zonaResgatada,
                            "Acolhido(a) e seguro(a) no abrigo Y"
                    );
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
            System.out.println("\n--- MENU CONVIDADO (Acesso Limitado) ---");
            System.out.println("1. 🆘 Encontrar Abrigo por Zona");
            System.out.println("2. 📦 Ver Recursos Gerais Disponíveis");
            System.out.println("3. 🏠 Ver Todos os Abrigos com Vagas");
            System.out.println("4. 💙 Receber Mensagem de Apoio");
            System.out.println("0. 🚪 Voltar ao Menu Inicial");
            System.out.print("Escolha uma opção: ");
            opcaoConvidado = lerOpcaoNumerica();

            switch (opcaoConvidado) {
                case 1:
                    ajudaUrgenteAbrigoPorZona();
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
                case 0:
                    break;
                default:
                    System.out.println("⚠️ Opção inválida.");
            }
        } while (opcaoConvidado != 0);
    }

    /**
     * Lê uma opção numérica do console, tratando NumberFormatException.
     * @return O número lido, ou -99 em caso de erro de formato ou entrada vazia.
     */
    private static int lerOpcaoNumerica() {
        try {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return -99;
            }
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("‼️ ERRO: Entrada inválida. Por favor, digite um número.");
            return -99;
        }
    }

    /**
     * Lê um valor double do console, tratando NumberFormatException.
     * Permite vírgula como separador decimal.
     * @return O double lido, ou Double.MIN_VALUE em caso de erro de formato ou entrada vazia.
     */
    private static double lerDouble() {
        try {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("⚠️ Entrada vazia não permitida para coordenadas.");
                return Double.MIN_VALUE;
            }
            return Double.parseDouble(input.replace(",", "."));
        } catch (NumberFormatException e) {
            System.out.println("‼️ ERRO: Formato de número inválido. Use números e, se necessário, '.' ou ',' como separador decimal.");
            return Double.MIN_VALUE;
        }
    }
}