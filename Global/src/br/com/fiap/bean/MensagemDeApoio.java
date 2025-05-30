package br.com.fiap.bean;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Classe utilitária responsável por gerar mensagens aleatórias de apoio emocional
 * para usuários em situações de emergência.
 * As mensagens são predefinidas e selecionadas aleatoriamente.
 */
public class MensagemDeApoio {

    private static final List<String> mensagens = Arrays.asList(
            "Você não está sozinho(a). Mantenha a calma e procure um local seguro.",
            "Respire fundo. A ajuda está a caminho. Estamos com você.",
            "A esperança é a luz que nos guia na escuridão. Permaneça forte.",
            "Em breve, o socorro chegará. Concentre-se em sua segurança. Força!",
            "Você é mais forte do que imagina. Aguente firme, estamos trabalhando por você."
    );

    private static final Random randomGenerator = new Random();

    /**
     * Construtor privado para prevenir a instanciação da classe utilitária.
     */
    private MensagemDeApoio() {
        // Classe utilitária não deve ser instanciada
    }

    /**
     * Gera e retorna uma mensagem de apoio emocional aleatória da lista predefinida.
     *
     * @return Uma {@code String} contendo uma mensagem de apoio.
     */
    public static String gerarMensagem() {
        if (mensagens.isEmpty()) {
            return "Mantenha a calma e a esperança.";
        }
        return mensagens.get(randomGenerator.nextInt(mensagens.size()));
    }
}