package br.com.fiap.main;

import br.com.fiap.bean.*;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Classe principal que usa Scanner para inserir dados via terminal e simular o sistema de combate a incêndios.
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Localização
        System.out.println("Digite o município:");
        String municipio = scanner.nextLine();

        System.out.println("Digite o estado:");
        String estado = scanner.nextLine();

        System.out.println("Digite a latitude:");
        double latitude = scanner.nextDouble();

        System.out.println("Digite a longitude:");
        double longitude = scanner.nextDouble();
        scanner.nextLine(); // Limpar buffer

        Localizacao local = new Localizacao(municipio, estado, latitude, longitude);

        // Causa
        System.out.println("Digite a descrição da causa:");
        String descricaoCausa = scanner.nextLine();
        Causa causa = new Causa(1, descricaoCausa);

        // Previsão Meteorológica
        System.out.println("Digite a data da previsão (AAAA-MM-DD):");
        LocalDate dataPrevisao = LocalDate.parse(scanner.nextLine());

        System.out.println("Temperatura:");
        double temperatura = scanner.nextDouble();

        System.out.println("Umidade:");
        double umidade = scanner.nextDouble();

        System.out.println("Velocidade do vento:");
        double vento = scanner.nextDouble();

        System.out.println("Probabilidade de chuva:");
        double probChuva = scanner.nextDouble();
        scanner.nextLine(); // limpar buffer

        PrevisaoMeteorologica previsao = new PrevisaoMeteorologica(dataPrevisao, temperatura, umidade, vento, probChuva);

        // Tipo de Vegetação
        System.out.println("Digite a descrição do tipo de vegetação:");
        String tipoVegetacao = scanner.nextLine();
        TipoVegetacao vegetacao = new TipoVegetacao(1, tipoVegetacao);

        // Incêndio
        System.out.println("Digite a data da ocorrência do incêndio (AAAA-MM-DD):");
        LocalDate dataIncendio = LocalDate.parse(scanner.nextLine());

        System.out.println("Digite a área atingida (em hectares):");
        double areaAtingida = scanner.nextDouble();
        scanner.nextLine();

        Incendio incendio = new Incendio(1, local, causa, previsao, vegetacao, dataIncendio, areaAtingida);

        // Equipe
        System.out.println("Digite o nome da equipe:");
        String nomeEquipe = scanner.nextLine();

        System.out.println("Digite o órgão responsável:");
        String orgao = scanner.nextLine();

        System.out.println("Digite o contato da equipe:");
        String contato = scanner.nextLine();

        Equipe equipe = new Equipe(nomeEquipe, orgao, contato);

        // Ação de Combate
        System.out.println("Digite a data da ação de combate (AAAA-MM-DD):");
        LocalDate dataAcao = LocalDate.parse(scanner.nextLine());

        System.out.println("Digite o tipo de ação:");
        String tipoAcao = scanner.nextLine();

        System.out.println("Digite a área combatida (em hectares):");
        double areaCombatida = scanner.nextDouble();

        AcaoCombate acao = new AcaoCombate(1, incendio, equipe, dataAcao, tipoAcao, areaCombatida);

        // Resultados
        System.out.println("\n==== RELATÓRIO ====");
        System.out.println("Local: " + incendio.getLocalizacao());
        System.out.println("Causa: " + causa.getDescricao());
        System.out.println("Área atingida: " + areaAtingida + " ha");
        System.out.println("Vegetação: " + vegetacao.getDescricao());
        System.out.println("Previsão Crítica? " + previsao.isCondicaoCritica());
        System.out.println("Vegetação Inflamável? " + vegetacao.isInflamavel());
        System.out.println("Incêndio de grande proporção? " + incendio.isGrandeProporcao());
        System.out.println("Equipe: " + equipe);
        System.out.println("Ação: " + tipoAcao);
        System.out.println("Ação eficaz? " + acao.isEficaz());

        scanner.close();
    }
}