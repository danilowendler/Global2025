package br.com.fiap.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Repositório em memória responsável por armazenar e gerenciar uma coleção
 * de objetos {@link Recurso} genéricos (não abrigos).
 * Permite adicionar novos recursos e listar aqueles que estão atualmente disponíveis.
 * Para gerenciamento de Abrigos, veja {@link AbrigoRepository}.
 */
public class RecursoRepository {

    private final List<Recurso> recursos = new ArrayList<>();

    /**
     * Adiciona um novo recurso genérico à coleção gerenciada pelo repositório.
     *
     * @param recurso O objeto {@link Recurso} a ser adicionado. Se nulo, não é adicionado.
     */
    public void adicionar(Recurso recurso) {
        if (recurso != null) {
            recursos.add(recurso);
        }
    }

    /**
     * Lista todos os recursos genéricos que estão atualmente marcados como disponíveis.
     *
     * @return Uma {@code List<Recurso>} contendo apenas os recursos disponíveis.
     * Retorna uma lista vazia se nenhum recurso estiver disponível.
     */
    public List<Recurso> listarDisponiveis() {
        return recursos.stream()
                .filter(Recurso::isDisponivel)
                .collect(Collectors.toList());
    }

    /**
     * Lista todos os recursos genéricos cadastrados, independentemente da disponibilidade.
     *
     * @return Uma {@code List<Recurso>} contendo todos os recursos.
     */
    public List<Recurso> listarTodos() {
        return new ArrayList<>(recursos);
    }
}