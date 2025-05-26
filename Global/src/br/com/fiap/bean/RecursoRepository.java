package br.com.fiap.bean;

import java.util.ArrayList;import java.util.List;import java.util.stream.Collectors;
public class RecursoRepository {

    private final List<Recurso> recursos = new ArrayList<>();

    public void adicionar(Recurso recurso) {
        recursos.add(recurso);
    }

    public List<Recurso> listarDisponiveis() {
        return recursos.stream()
                .filter(Recurso::isDisponivel)
                .collect(Collectors.toList());
    }
}
