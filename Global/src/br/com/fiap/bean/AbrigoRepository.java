package br.com.fiap.bean;

/**
 * Repositório em memória responsável por armazenar e gerenciar uma coleção
 * de objetos {@link Abrigo}, utilizando um array de tamanho fixo.
 * Permite adicionar novos abrigos e listar aqueles que possuem vagas, opcionalmente filtrando por zona.
 */
public class AbrigoRepository {

    private final Abrigo[] abrigos = new Abrigo[50];
    private int proximaPosicaoLivre = 0;

    /**
     * Adiciona um novo abrigo à coleção gerenciada pelo repositório.
     *
     * @param abrigo O objeto {@link Abrigo} a ser adicionado. Se nulo, não é adicionado.
     */
    public void adicionar(Abrigo abrigo) {
        if (abrigo != null && proximaPosicaoLivre < abrigos.length) {
            abrigos[proximaPosicaoLivre] = abrigo;
            proximaPosicaoLivre++;
        } else if (abrigo != null) {
            System.out.println("ERRO: Repositório de abrigos está cheio. Não foi possível adicionar: " + abrigo.getNomeAbrigo());
        }
    }

    /**
     * Lista todos os abrigos que atualmente possuem vagas disponíveis.
     *
     * @return Um array de {@code Abrigo[]} contendo apenas os abrigos com vagas.
     */
    public Abrigo[] listarComVagas() {
        int contadorDeVagas = 0;
        for (int i = 0; i < proximaPosicaoLivre; i++) {
            if (abrigos[i].temVagas()) {
                contadorDeVagas++;
            }
        }

        Abrigo[] abrigosComVagas = new Abrigo[contadorDeVagas];
        int indiceNovoArray = 0;
        for (int i = 0; i < proximaPosicaoLivre; i++) {
            if (abrigos[i].temVagas()) {
                abrigosComVagas[indiceNovoArray] = abrigos[i];
                indiceNovoArray++;
            }
        }
        return abrigosComVagas;
    }

    /**
     * Lista todos os abrigos que possuem vagas disponíveis em uma determinada zona.
     * A comparação da zona é case-insensitive.
     *
     * @param zona A zona para filtrar os abrigos (ex: "Zona Sul").
     * @return Um array de {@code Abrigo[]} contendo os abrigos com vagas na zona especificada.
     */
    public Abrigo[] listarComVagasPorZona(String zona) {
        if (zona == null || zona.trim().isEmpty()) {
            return new Abrigo[0];
        }

        int contadorDeVagasNaZona = 0;
        for (int i = 0; i < proximaPosicaoLivre; i++) {
            if (zona.equalsIgnoreCase(abrigos[i].getZonaDoAbrigo()) && abrigos[i].temVagas()) {
                contadorDeVagasNaZona++;
            }
        }

        Abrigo[] abrigosComVagasNaZona = new Abrigo[contadorDeVagasNaZona];
        int indiceNovoArray = 0;
        for (int i = 0; i < proximaPosicaoLivre; i++) {
            if (zona.equalsIgnoreCase(abrigos[i].getZonaDoAbrigo()) && abrigos[i].temVagas()) {
                abrigosComVagasNaZona[indiceNovoArray] = abrigos[i];
                indiceNovoArray++;
            }
        }
        return abrigosComVagasNaZona;
    }
}