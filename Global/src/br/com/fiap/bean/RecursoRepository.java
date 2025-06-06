package br.com.fiap.bean;

/**
 * Repositório em memória responsável por armazenar e gerenciar uma coleção
 * de objetos {@link Recurso} genéricos (não abrigos), utilizando um array de tamanho fixo.
 * Permite adicionar novos recursos e listar aqueles que estão atualmente disponíveis.
 */
public class RecursoRepository {

    private final Recurso[] recursos = new Recurso[50];
    private int proximaPosicaoLivre = 0;

    /**
     * Adiciona um novo recurso genérico à coleção gerenciada pelo repositório.
     * A adição só ocorre se o array não estiver cheio.
     * @param recurso O objeto {@link Recurso} a ser adicionado. Se nulo, não é adicionado.
     */
    public void adicionar(Recurso recurso) {
        if (recurso != null && proximaPosicaoLivre < recursos.length) {
            recursos[proximaPosicaoLivre] = recurso;
            proximaPosicaoLivre++;
        } else if (recurso != null) {
            System.out.println("ERRO: Repositório de recursos gerais está cheio. Não foi possível adicionar: " + recurso.getTipo());
        }
    }

    /**
     * Lista todos os recursos genéricos que estão atualmente marcados como disponíveis.
     * @return Um array de {@code Recurso[]} contendo apenas os recursos disponíveis.
     */
    public Recurso[] listarDisponiveis() {
        int contadorDeDisponiveis = 0;
        for (int i = 0; i < proximaPosicaoLivre; i++) {
            if (recursos[i].isDisponivel()) {
                contadorDeDisponiveis++;
            }
        }

        Recurso[] recursosDisponiveis = new Recurso[contadorDeDisponiveis];
        int indiceNovoArray = 0;
        for (int i = 0; i < proximaPosicaoLivre; i++) {
            if (recursos[i].isDisponivel()) {
                recursosDisponiveis[indiceNovoArray] = recursos[i];
                indiceNovoArray++;
            }
        }
        return recursosDisponiveis;
    }

}
