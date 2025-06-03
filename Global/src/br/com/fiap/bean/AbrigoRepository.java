package br.com.fiap.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Repositório em memória responsável por armazenar e gerenciar uma coleção
 * de objetos {@link Abrigo}.
 * Permite adicionar novos abrigos e listar aqueles que possuem vagas, opcionalmente filtrando por zona.
 */
public class AbrigoRepository {

    private final List<Abrigo> abrigos = new ArrayList<>();

    /**
     * Adiciona um novo abrigo à coleção gerenciada pelo repositório.
     *
     * @param abrigo O objeto {@link Abrigo} a ser adicionado. Se nulo, não é adicionado.
     */
    public void adicionar(Abrigo abrigo) {
        if (abrigo != null) {
            abrigos.add(abrigo);
        }
    }

    /**
     * Lista todos os abrigos que atualmente possuem vagas disponíveis.
     *
     * @return Uma {@code List<Abrigo>} contendo apenas os abrigos com vagas.
     * Retorna uma lista vazia se nenhum abrigo com vagas for encontrado.
     */
    public List<Abrigo> listarComVagas() {
        return abrigos.stream()
                .filter(Abrigo::temVagas)
                .collect(Collectors.toList());
    }

    /**
     * Lista todos os abrigos que possuem vagas disponíveis em uma determinada zona.
     * A comparação da zona é case-insensitive.
     *
     * @param zona A zona para filtrar os abrigos (ex: "Zona Sul").
     * @return Uma {@code List<Abrigo>} contendo os abrigos com vagas na zona especificada.
     * Retorna uma lista vazia se a zona for nula, vazia ou se nenhum abrigo atender aos critérios.
     */
    public List<Abrigo> listarComVagasPorZona(String zona) {
        if (zona == null || zona.trim().isEmpty()) {
            return new ArrayList<>();
        }
        return abrigos.stream()
                .filter(abrigo -> abrigo.temVagas() && zona.equalsIgnoreCase(abrigo.getZonaDoAbrigo()))
                .collect(Collectors.toList());
    }

}