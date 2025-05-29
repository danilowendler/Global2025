package br.com.fiap.bean;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Gera mensagens aleatórias de apoio emocional para usuários em situações de emergência.
 */
public class MensagemDeApoio {

    private static final List<String> mensagens = Arrays.asList(
            "Você não está sozinho. Mantenha a calma.",
            "Respire fundo. Estamos com você.",
            "A esperança é mais forte que o fogo.",
            "Em breve, o socorro chegará. Força!",
            "Você é mais forte do que imagina. Aguente firme."
    );

    /**
     * Gera uma mensagem aleatória de apoio.
     * @return mensagem de apoio emocional
     */
    public static String gerarMensagem() {
        Random r = new Random();
        return mensagens.get(r.nextInt(mensagens.size()));
    }
}
