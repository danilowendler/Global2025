package br.com.fiap.main;

import br.com.fiap.bean.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Entrada da pessoa
        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();

        System.out.print("Digite sua localização: ");
        String localizacao = scanner.nextLine();

        System.out.print("Informe o tipo de usuário (morador, fiscal, voluntário): ");
        String tipoUsuario = scanner.nextLine();

        System.out.print("Informe o status da pessoa (aguardando, resgatada, seguro): ");
        String status = scanner.nextLine();

        Pessoa pessoa = new Pessoa(nome, localizacao);
        pessoa.setTipoUsuario(tipoUsuario);
        pessoa.setStatus(status);

        RecursoRepository recursoRepo = new RecursoRepository();

        // Cadastro manual de recurso
        System.out.println("\nCadastro de recurso:");
        System.out.print("Digite o tipo do recurso: ");
        String tipoRecurso = scanner.nextLine();

        System.out.print("Digite o local do recurso: ");
        String localRecurso = scanner.nextLine();

        System.out.print("Está disponível? (true/false): ");
        boolean disponivel = Boolean.parseBoolean(scanner.nextLine());

        System.out.print("Quantidade disponível: ");
        int quantidade = scanner.nextInt();

        System.out.print("Prioridade do recurso (1 = alta, 2 = média, 3 = baixa): ");
        int prioridade = scanner.nextInt();
        scanner.nextLine(); // limpar buffer

        Recurso recurso = new Recurso(tipoRecurso, localRecurso, disponivel);
        recurso.setQuantidade(quantidade);
        recurso.setPrioridade(prioridade);

        recursoRepo.adicionar(recurso);

        // Recursos adicionais
        recursoRepo.adicionar(new Recurso("Abrigo", "Escola Municipal 7 de Setembro", true));
        recursoRepo.adicionar(new Recurso("Água Potável", "Posto Central", true));
        recursoRepo.adicionar(new Recurso("Alimentos", "Centro Comunitário", false));

        EmergenciaServico emergenciaService = new EmergenciaServico(recursoRepo);

        int opcao;

        do {
            System.out.println("\n🔥 Aplicativo de Emergência - Queimadas 🔥");
            System.out.println("1. Ver recursos físicos disponíveis");
            System.out.println("2. Receber mensagem de apoio");
            System.out.println("3. Ver meus dados");
            System.out.println("4. Contar recursos disponíveis");
            System.out.println("5. Verificar se há um tipo de recurso");
            System.out.println("6. Enviar alerta personalizado");
            System.out.println("7. Simular pessoa resgatada");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> emergenciaService.mostrarRecursosDisponiveis();
                case 2 -> emergenciaService.oferecerMensagemDeApoio();
                case 3 -> System.out.println(pessoa);
                case 4 -> {
                    int total = emergenciaService.contarRecursosDisponiveis();
                    System.out.println("📊 Total de recursos disponíveis: " + total);
                }
                case 5 -> {
                    System.out.print("Digite o tipo de recurso para buscar: ");
                    String tipoBusca = scanner.nextLine();
                    boolean existe = emergenciaService.existeRecursoDoTipo(tipoBusca);
                    System.out.println(existe
                            ? "✅ Recurso do tipo \"" + tipoBusca + "\" disponível!"
                            : "❌ Nenhum recurso do tipo \"" + tipoBusca + "\" foi encontrado.");
                }
                case 6 -> {
                    String alerta = emergenciaService.gerarAlertaParaPessoa(pessoa);
                    System.out.println(alerta);
                }
                case 7 -> {
                    PessoaResgatada resgatada = new PessoaResgatada(nome, localizacao, "Em abrigo seguro");
                    System.out.println(resgatada);
                }
                case 0 -> System.out.println("Encerrando... fique seguro!");
                default -> System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        scanner.close();
    }
}