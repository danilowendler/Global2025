package br.com.fiap.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Repositório em memória responsável por armazenar e gerenciar uma coleção
 * de objetos {@link Abrigo}.
 * Permite adicionar novos abrigos e listar aqueles que possuem vagas.
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
     * Lista todos os abrigos cadastrados, independentemente da disponibilidade de vagas.
     *
     * @return Uma {@code List<Abrigo>} contendo todos os abrigos.
     */
    public List<Abrigo> listarTodos() {
        return new ArrayList<>(abrigos); // Retorna uma cópia
    }
}
