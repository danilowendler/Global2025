package br.com.fiap.main;

import br.com.fiap.bean.EmergenciaService;
import br.com.fiap.bean.Pessoa;
import br.com.fiap.bean.Recurso;
import br.com.fiap.bean.RecursoRepository;

import java.util.Scanner;

public class Main {

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

        // Menu interativo
        int opcao;

        do {
            System.out.println("\n🔥 Aplicativo de Emergência - Queimadas 🔥");
            System.out.println("1. Ver recursos físicos próximos");
            System.out.println("2. Receber mensagem de apoio");
            System.out.println("3. Ver meus dados");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> emergenciaService.mostrarRecursosDisponiveis();
                case 2 -> emergenciaService.oferecerMensagemDeApoio();
                case 3 -> System.out.println(pessoa);
                case 0 -> System.out.println("Encerrando... fique seguro!");
                default -> System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        scanner.close();
    }
}