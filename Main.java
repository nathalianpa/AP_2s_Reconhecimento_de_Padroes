/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reconhecimento_de_padroes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author nathalia
 * 
 * Eu,
 * Nathalia Pereira Alves
 * declaro que,
 * todas as respostas são fruto de meu próprio trabalho,
 * não copiei respostas de colegas externos à equipe,
 * não disponibilizei respostas para colegas externos à equipe e
 * não realizei quaisquer outras atividades desonestas para me beneficiar ou
 * prejudicar outros.
 * 
 */
public class Main {
    public static void main(String[] args) {
        String caminho = "./src/main/java/com/mycompany/reconhecimento_de_padroes/imagem.txt";
        
        int caso = 0;
        
        // Este bloco de codigo vai ler um arquivo contendo matrizes que 
        // representam uma imagem "scanneada".
        // Para cada matriz um objeto do tipo "imagemCodificada" sera criado
        // contendo alguns dados, dentre eles os 
        // eixos de gravidade (linha x coluna).
        try {
            FileReader arq = new FileReader(caminho);
            BufferedReader lerArq = new BufferedReader(arq);
            
            String linha = lerArq.readLine();
            
            boolean repetir = true;
            
            while(repetir) {
                double[] valoresLinha = converterLinha(linha);
                int totalLinhas = (int) valoresLinha[0];
                int totalColunas = (int) valoresLinha[1];
                double[][] matriz = new double[totalLinhas][totalColunas];

                int count = 0;

                linha = lerArq.readLine();

                do {
                    valoresLinha = converterLinha(linha);

                    for (int coluna = 0; coluna < valoresLinha.length; coluna++) {
                        matriz[count][coluna] = valoresLinha[coluna];
                    }

                    count += 1;
                    
                    linha = lerArq.readLine();

                } while (linha != null && !linha.equals(""));
                            
                caso += 1;
                
                // Este objeto representa uma "imagem codificada".
                // Após executar seu metodo "executar", os eixos de gravidade, 
                // linha e coluna, serão calculados e salvos nos atributos
                // eixoLinha e eixoColuna respectivamente, podendo acessa-los
                // atraves dos metodos getEixoLinha() e getEixoLinha().
                ImagemCodificada imagem = new ImagemCodificada(totalLinhas, totalColunas, matriz);
                imagem.executar();
                
                System.out.println("caso " + caso + ": Centro ( " + imagem.getEixoLinha() + ", " + imagem.getEixoColuna() + " )");

                if(linha == null) {
                    repetir = false;
                } else {
                    linha = lerArq.readLine();
                }
            }
            
            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
    }
    
    private static double[] converterLinha(String linha) {
        String[] chars = linha.split(" ");
        double[] valores = new double[chars.length];

        System.out.println(chars);
        for (int i = 0; i < chars.length; i++) {
            valores[i] = Double.parseDouble(chars[i]);
        }

        return valores;
    }
}