package br.com.fiap.bean;

import java.util.Comparator;
import java.util.List;


/**
 * Serviço responsável por orquestrar ações de emergência, como
 * visualização de recursos e abrigos, oferta de mensagens de apoio e geração de alertas.
 * Interage com {@link RecursoRepository} e {@link AbrigoRepository}.
 */
public class EmergenciaServico {

    private final RecursoRepository recursoRepository;
    private final AbrigoRepository abrigoRepository;

    /**
     * Constrói uma nova instância de EmergenciaServico.
     *
     * @param recursoRepository O repositório de recursos genéricos a ser utilizado.
     * @param abrigoRepository  O repositório de abrigos a ser utilizado.
     */
    public EmergenciaServico(RecursoRepository recursoRepository, AbrigoRepository abrigoRepository) {
        this.recursoRepository = recursoRepository;
        this.abrigoRepository = abrigoRepository;
    }

    /**
     * Exibe na console a lista de recursos genéricos atualmente disponíveis.
     * Se não houver recursos, uma mensagem informativa é exibida.
     */
    public void mostrarRecursosGeraisDisponiveis() {
        List<Recurso> recursos = recursoRepository.listarDisponiveis();
        if (recursos.isEmpty()) {
            System.out.println("ℹ️ Nenhum recurso geral disponível no momento.");
        } else {
            System.out.println("📦 Recursos Gerais Disponíveis:");
            recursos.forEach(System.out::println);
        }
    }

    /**
     * Exibe na console a lista de abrigos atualmente com vagas.
     * Se não houver abrigos com vagas, uma mensagem informativa é exibida.
     */
    public void mostrarAbrigosComVagas() {
        List<Abrigo> abrigos = abrigoRepository.listarComVagas();
        if (abrigos.isEmpty()) {
            System.out.println("ℹ️ Nenhum abrigo com vagas disponível no momento.");
        } else {
            System.out.println("🏠 Abrigos com Vagas Disponíveis:");
            abrigos.forEach(System.out::println);
        }
    }


    /**
     * Encontra e retorna o abrigo com vagas mais próximo da localização fornecida.
     *
     * @param userLatitude  Latitude atual do usuário.
     * @param userLongitude Longitude atual do usuário.
     * @return O objeto {@link Abrigo} mais próximo com vagas, ou {@code null} se nenhum for encontrado.
     */
    public Abrigo encontrarAbrigoMaisProximoDisponivel(double userLatitude, double userLongitude) {
        List<Abrigo> abrigosComVagas = abrigoRepository.listarComVagas();

        if (abrigosComVagas.isEmpty()) {
            return null;
        }

        return abrigosComVagas.stream()
                .min(Comparator.comparingDouble(abrigo ->
                        CalculadoraDistancia.calcular(userLatitude, userLongitude, abrigo.getLatitude(), abrigo.getLongitude())))
                .orElse(null);
    }


    /**
     * Exibe uma mensagem de apoio emocional aleatória na console.
     * Utiliza la clase {@link MensagemDeApoio} para generar el mensaje.
     */
    public void oferecerMensagemDeApoio() {
        System.out.println("💙 Mensagem de Apoio: " + MensagemDeApoio.gerarMensagem());
    }

    /**
     * Conta o número total de recursos genéricos disponíveis.
     *
     * @return O número inteiro de recursos genéricos disponíveis.
     */
    public int contarRecursosGeraisDisponiveis() {
        return recursoRepository.listarDisponiveis().size();
    }

    /**
     * Conta o número total de abrigos com vagas.
     *
     * @return O número inteiro de abrigos com vagas.
     */
    public int contarAbrigosComVagas() {
        return abrigoRepository.listarComVagas().size();
    }


    /**
     * Verifica se existe algum recurso genérico disponível do tipo especificado.
     * A comparação do tipo é case-insensitive.
     *
     * @param tipo O tipo de recurso a ser verificado (ex: "água", "alimento").
     * @return {@code true} se existir ao menos um recurso disponível do tipo especificado,
     * {@code false} caso contrário.
     */
    public boolean existeRecursoGeralDoTipo(String tipo) {
        if (tipo == null || tipo.trim().isEmpty()) return false;
        return recursoRepository.listarDisponiveis().stream()
                .anyMatch(r -> tipo.equalsIgnoreCase(r.getTipo()));
    }

    /**
     * Gera uma mensagem de alerta personalizada para uma pessoa específica.
     *
     * @param pessoa A {@link Pessoa} para a qual o alerta será gerado.
     * @return Uma {@code String} contendo a mensagem de alerta formatada com o nome
     * e localização descritiva da pessoa.
     */
    public String gerarAlertaParaPessoa(Pessoa pessoa) {
        if (pessoa == null) {
            return "🚨 Alerta: Pessoa não identificada.";
        }
        return "🚨 ALERTA URGENTE para " + pessoa.getNome() +
                " em/próximo a '" + pessoa.getLocalizacaoDescritiva() +
                "'. Procure informações oficiais e siga as orientações de segurança!";
    }
}