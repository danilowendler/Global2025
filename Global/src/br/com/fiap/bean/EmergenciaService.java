package br.com.fiap.bean;

import java.util.List;
public class EmergenciaService {

    private final RecursoRepository recursoRepository;

    public EmergenciaService(RecursoRepository recursoRepository) {
        this.recursoRepository = recursoRepository;
    }

    public void mostrarRecursosDisponiveis() {
        List<Recurso> recursos = recursoRepository.listarDisponiveis();
        if (recursos.isEmpty()) {
            System.out.println("⚠️ Nenhum recurso disponível no momento.");
        } else {
            System.out.println("📦 Recursos disponíveis:");
            recursos.forEach(System.out::println);
        }
    }

    public void oferecerMensagemDeApoio() {
        System.out.println("💙 Suporte emocional: " + MensagemDeApoio.gerarMensagem());
    }
}
