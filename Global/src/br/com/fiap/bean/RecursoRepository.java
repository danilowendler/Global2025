package br.com.fiap.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Repositório responsável por armazenar e recuperar recursos.
 */
public class RecursoRepository {

    private final List<Recurso> recursos = new ArrayList<>();

    /**
     * Adiciona um novo recurso ao repositório.
     * @param recurso o recurso a ser adicionado
     */
    public void adicionar(Recurso recurso) {
        recursos.add(recurso);
    }

    /**
     * Lista todos os recursos que estão disponíveis.
     * @return lista de recursos disponíveis
     */
    public List<Recurso> listarDisponiveis() {
        return recursos.stream()
                .filter(Recurso::isDisponivel)
                .collect(Collectors.toList());
    }
}