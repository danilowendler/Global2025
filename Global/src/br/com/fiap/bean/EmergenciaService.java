package br.com.fiap.bean;

import java.util.List;

/**
 * Serviço responsável por ações de emergência.
 */
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

    /**
     * Conta quantos recursos estão disponíveis.
     * @return número de recursos disponíveis
     */
    public int contarRecursosDisponiveis() {
        return recursoRepository.listarDisponiveis().size();
    }

    /**
     * Verifica se há recursos do tipo informado.
     * @param tipo tipo de recurso
     * @return true se existir algum recurso do tipo disponível
     */
    public boolean existeRecursoDoTipo(String tipo) {
        return recursoRepository.listarDisponiveis().stream()
                .anyMatch(r -> r.getTipo().equalsIgnoreCase(tipo));
    }

    /**
     * Gera uma mensagem de alerta para a pessoa informada.
     * @param pessoa a pessoa a ser avisada
     * @return string com a mensagem
     */
    public String gerarAlertaParaPessoa(Pessoa pessoa) {
        return "🚨 Alerta enviado para " + pessoa.getNome() + " na localização: " + pessoa.getLocalizacao();
    }
}
