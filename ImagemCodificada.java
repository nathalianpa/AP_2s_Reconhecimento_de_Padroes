/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reconhecimento_de_padroes;

/**
 *
 * @author nathalia
 * 
 * Esta classe representa uma imagem codificada.
 * A classe "Main" criará uma instância desta classe para cada matriz existente
 * no arquivo de importacao.
 */
public class ImagemCodificada {
    private int totalLinhas;
    private int totalColunas;
    private double[][] matriz;
    private int eixoLinha;
    private int eixoColuna;
    private double resultadoLinha;
    private double resultadoColuna;
    
    public ImagemCodificada(int totalLinhas, int totalColunas, double[][] matriz) {
        this.totalLinhas = totalLinhas;
        this.totalColunas = totalColunas;
        this.matriz = matriz;
    }
    
    public void executar() {
        encontrarEixoLinha();
        encontrarEixoColuna();
    }
    
    public int getEixoLinha() {
        return eixoLinha;
    }

    public int getEixoColuna() {
        return eixoColuna;
    }
    
    private void encontrarEixoLinha() {
        for (int i = 0; i < totalLinhas; i++) {
            if (i == 0) {
                resultadoLinha = calcularLinha(i);
                eixoLinha = i + 1;
            } else {
                double resultadoProvisorio = calcularLinha(i);

                if (resultadoLinha > resultadoProvisorio) {
                    resultadoLinha = resultadoProvisorio;
                    eixoLinha = i + 1;
                }
            }
        }

    }
    
    private void encontrarEixoColuna() {
        for (int i = 0; i < totalColunas; i++) {
            if (i == 0) {
                resultadoColuna = calcularColuna(i);
                eixoColuna = i + 1;
            } else {
                double resultadoProvisorio = calcularColuna(i);

                if (resultadoColuna > resultadoProvisorio) {
                    resultadoColuna = resultadoProvisorio;
                    eixoColuna = i + 1;
                }
            }
        }
    }
    
    private double calcularLinha(int idLinhaGravidade) {
        double valorAcima = 0;
        double valorAbaixo = 0;

        for (int idLinha = 0; idLinha < idLinhaGravidade; idLinha++) {
            for (int coluna = 0; coluna < totalColunas; coluna++) {
                valorAcima += matriz[idLinha][coluna];
            }
        }

        for (int idLinha = idLinhaGravidade + 1; idLinha < totalLinhas; idLinha++) {
            for (int coluna = 0; coluna < totalColunas; coluna++) {
                valorAbaixo += matriz[idLinha][coluna];
            }
        }

        if (valorAbaixo > valorAcima) {
            return valorAbaixo - valorAcima;
        }

        return valorAcima - valorAbaixo;
    }

    private double calcularColuna(int idColunaGravidade) {
        double valorEsquerda = 0;
        double valorDireita = 0;

        for (int idColuna = 0; idColuna < idColunaGravidade; idColuna++) {
            for (int idLinha = 0; idLinha < totalLinhas; idLinha++) {
                valorEsquerda += matriz[idLinha][idColuna];
            }
        }

        for (int idColuna = idColunaGravidade + 1; idColuna < totalColunas; idColuna++) {
            for (int idLinha = 0; idLinha < totalLinhas; idLinha++) {
                valorDireita += matriz[idLinha][idColuna];
            }
        }

        if (valorEsquerda > valorDireita) {
            return valorEsquerda - valorDireita;
        }

        return valorDireita - valorEsquerda;
    }
}
