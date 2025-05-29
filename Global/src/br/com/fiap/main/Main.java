package br.com.fiap.main;

import br.com.fiap.bean.*;

import java.util.Scanner;

/**
 * Classe principal responsável por executar o sistema de apoio emergencial em casos de queimadas.
 * Permite ao usuário interagir com o sistema, visualizar recursos, receber mensagens de apoio e simular resgate.
 */
public class Main {

    /**
     * Metodo principal que inicializa o sistema e exibe um menu interativo para o usuário.
     * @param args argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Simulando uma pessoa
        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite sua localização: ");
        String localizacao = scanner.nextLine();

        Pessoa pessoa = new Pessoa(nome, localizacao);

        // Setup de dados (pré-cadastrados)
        RecursoRepository recursoRepo = new RecursoRepository();
        recursoRepo.adicionar(new Recurso("Abrigo", "Escola Municipal 7 de Setembro", true));
        recursoRepo.adicionar(new Recurso("Água Potável", "Posto Central", true));
        recursoRepo.adicionar(new Recurso("Alimentos", "Centro Comunitário", false));

        EmergenciaService emergenciaService = new EmergenciaService(recursoRepo);

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
            scanner.nextLine(); // limpa buffer

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