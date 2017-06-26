package com.weullermarcos.buscafarma;

/**
 * Created by allanalves on 26/06/17.
 */

public class RemedioEntity {
    private String nome;
    private String nomeDaFarmacia;
    private Double preco;
    private Double distancia;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeDaFarmacia() {
        return nomeDaFarmacia;
    }

    public void setNomeDaFarmacia(String nomeDaFarmacia) {
        this.nomeDaFarmacia = nomeDaFarmacia;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Double getDistancia() {
        return distancia;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }
}
