package br.com.fiap.bean;

import java.util.Arrays;import java.util.List;import java.util.Random;

public class MensagemDeApoio {

    private static final List<String> mensagens = Arrays.asList(
            "Você não está sozinho. Mantenha a calma.",
            "Respire fundo. Estamos com você.",
            "A esperança é mais forte que o fogo.",
            "Em breve, o socorro chegará. Força!",
            "Você é mais forte do que imagina. Aguente firme."    );

    public static String gerarMensagem() {
        Random r = new Random();
        return mensagens.get(r.nextInt(mensagens.size()));
    }
}
