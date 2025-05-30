package br.com.fiap.bean;

import java.util.List;

/**
 * Serviço responsável por ações de emergência.
 */
public class EmergenciaServico {

    private final RecursoRepository recursoRepository;

    public EmergenciaServico(RecursoRepository recursoRepository) {
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

    public int contarRecursosDisponiveis() {
        return recursoRepository.listarDisponiveis().size();
    }

    public boolean existeRecursoDoTipo(String tipo) {
        return recursoRepository.listarDisponiveis().stream()
                .anyMatch(r -> r.getTipo().equalsIgnoreCase(tipo));
    }

    public String gerarAlertaParaPessoa(Pessoa pessoa) {
        return "🚨 Alerta enviado para " + pessoa.getNome() + " na localização: " + pessoa.getLocalizacao();
    }
}
