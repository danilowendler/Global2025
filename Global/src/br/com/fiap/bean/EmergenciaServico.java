package br.com.fiap.bean;

/**
 * Serviço responsável por orquestrar ações de emergência, como
 * visualização de recursos e abrigos, oferta de mensagens de apoio e geração de alertas.
 * Interage com {@link RecursoRepository} e {@link AbrigoRepository} para obter os dados.
 * Esta versão foi refatorada para trabalhar com arrays primitivos, alinhada aos conceitos
 * fundamentais de Java.
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
     */
    public void mostrarRecursosGeraisDisponiveis() {
        Recurso[] recursos = recursoRepository.listarDisponiveis();
        if (recursos.length == 0) {
            System.out.println("ℹ️ Nenhum recurso geral disponível no momento.");
        } else {
            System.out.println("📦 Recursos Gerais Disponíveis:");
            for (Recurso recurso : recursos) {
                System.out.println(recurso);
            }
        }
    }

    /**
     * Exibe na console a lista de abrigos atualmente com vagas.
     */
    public void mostrarAbrigosComVagas() {
        Abrigo[] abrigos = abrigoRepository.listarComVagas();
        if (abrigos.length == 0) {
            System.out.println("ℹ️ Nenhum abrigo com vagas disponível no momento.");
        } else {
            System.out.println("🏠 Abrigos com Vagas Disponíveis:");
            for (Abrigo abrigo : abrigos) {
                System.out.println(abrigo);
            }
        }
    }

    /**
     * Lista os abrigos disponíveis (com vagas) em uma determinada zona.
     * @param zonaUsuario A zona informada pelo usuário.
     * @return Um array de objetos {@code Abrigo[]} disponíveis na zona,
     * ou um array vazio se nenhum for encontrado ou a zona for inválida.
     */
    public Abrigo[] listarAbrigosDisponiveisPorZona(String zonaUsuario) {
        if (zonaUsuario == null || zonaUsuario.trim().isEmpty()){
            System.out.println("⚠️ Zona não informada para busca de abrigos.");
            return new Abrigo[0]; // Retorna array vazio
        }
        return abrigoRepository.listarComVagasPorZona(zonaUsuario);
    }

    /**
     * Encontra e retorna o abrigo com vagas mais próximo da localização geográfica fornecida.
     * Este método utiliza um laço 'for' para iterar e encontrar a menor distância.
     * @param userLatitude  Latitude atual do usuário.
     * @param userLongitude Longitude atual do usuário.
     * @return O objeto {@link Abrigo} mais próximo com vagas, ou {@code null} se nenhum for encontrado.
     */
    public Abrigo encontrarAbrigoMaisProximoDisponivelPorCoordenadas(double userLatitude, double userLongitude) {
        Abrigo[] abrigosComVagas = abrigoRepository.listarComVagas();

        if (abrigosComVagas.length == 0) {
            return null;
        }

        Abrigo maisProximo = null;
        double menorDistancia = Double.MAX_VALUE;

        for (Abrigo abrigo : abrigosComVagas) {
            double distancia = CalculadoraDistancia.calcular(userLatitude, userLongitude, abrigo.getLatitude(), abrigo.getLongitude());
            if (distancia < menorDistancia) {
                menorDistancia = distancia;
                maisProximo = abrigo;
            }
        }
        return maisProximo;
    }


    /**
     * Exibe uma mensagem de apoio emocional aleatória na console.
     */
    public void oferecerMensagemDeApoio() {
        System.out.println("💙 Mensagem de Apoio: " + MensagemDeApoio.gerarMensagem());
    }

    /**
     * Conta o número total de recursos genéricos disponíveis.
     * @return O número inteiro de recursos genéricos disponíveis.
     */
    public int contarRecursosGeraisDisponiveis() {
        return recursoRepository.listarDisponiveis().length;
    }

    /**
     * Conta o número total de abrigos com vagas.
     * @return O número inteiro de abrigos com vagas.
     */
    public int contarAbrigosComVagas() {
        return abrigoRepository.listarComVagas().length;
    }


    /**
     * Verifica se existe algum recurso genérico disponível do tipo especificado.
     * A comparação do tipo é case-insensitive. Utiliza um laço 'for'.
     * @param tipo O tipo de recurso a ser verificado (ex: "água", "alimento").
     * @return {@code true} se existir ao menos um recurso disponível do tipo especificado,
     * {@code false} caso contrário.
     */
    public boolean existeRecursoGeralDoTipo(String tipo) {
        if (tipo == null || tipo.trim().isEmpty()) {
            return false;
        }

        Recurso[] recursosDisponiveis = recursoRepository.listarDisponiveis();

        for (Recurso recurso : recursosDisponiveis) {
            if (tipo.equalsIgnoreCase(recurso.getTipo())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Gera uma mensagem de alerta personalizada para uma pessoa específica.
     * @param pessoa A {@link Pessoa} para a qual o alerta será gerado.
     * @return Uma {@code String} contendo a mensagem de alerta formatada.
     */
    public String gerarAlertaParaPessoa(Pessoa pessoa) {
        if (pessoa == null) {
            return "🚨 Alerta: Pessoa não identificada.";
        }
        String localInfo = pessoa.getLocalizacaoDescritiva();
        if (pessoa.getZona() != null && !pessoa.getZona().isEmpty()) {
            localInfo += " (Zona: " + pessoa.getZona() + ")";
        }

        return "🚨 ALERTA URGENTE para " + pessoa.getNome() +
                " em/próximo a '" + localInfo +
                "'. Procure informações oficiais e siga as orientações de segurança!";
    }
}