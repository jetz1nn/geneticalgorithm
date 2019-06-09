/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.util.Random;

/**
 *
 * @author a1819879
 */
public class Populacao {

    private Integer tamanhoPopulacao;
    private Double txMutacao;
    private Double txCruzamento;
    private Double txSubstituicao;
    private int[][] posicaoY;
    private int[][] matrizTabuleiro = new int[8][8];
    public int [] individuoFitness;
    
    public Populacao(int tamanhoPopulacao, Double txMutacao, Double txCruzamento, Double txSubstituicao) {
        this.tamanhoPopulacao = tamanhoPopulacao;
        this.txMutacao = txMutacao;
        this.txCruzamento = txCruzamento;
        this.txSubstituicao = txSubstituicao;
        posicaoY = new int[tamanhoPopulacao][8];
        individuoFitness = new int [tamanhoPopulacao];
    }

    public void generatePopulation() {
        Random random = new Random();
        for (int i = 0; i < tamanhoPopulacao; i++) {
            for (int j = 0; j < 8; j++) {
                posicaoY[i][j] = random.nextInt(8);
            }
        }
    }
    
    public void checkFitness() {
        int[] posicoesY = new int[8];
        
        for (int i = 0; i < tamanhoPopulacao; i++) {
            for (int j = 0; j < 8; j++) {
                // popula o tabuleiro com os valores randomizados
                matrizTabuleiro[j][posicaoY[i][j]] = 1;
                // guardado os valores os quais 
                posicoesY[j] = posicaoY[i][j];
            }
            //fitness de cada indivíduo calculado
            individuoFitness[i] = checkTableFitness(posicoesY);
        }
    }
    
    public int checkTableFitness(int[] posicoesY) {
        int colisoes = 0;
        for (int i = 0; i < 8; i++) {
            if (percorrerLadoEsquerdo(posicoesY[i], i)) {
                colisoes++;
            }
            if (percorrerLadoDireito(posicoesY[i], i)) {
                colisoes++;
            }
            if (percorrerCima(posicoesY[i], i)) {
                colisoes++;
            }
            if (percorrerCima(posicoesY[i], i)) {
                colisoes++;
            }
            if (percorrerDiagonalSobeDireita(posicoesY[i], i)) {
                colisoes++;
            }
            if (percorrerDiagonalSobeEsquerda(posicoesY[i], i)) {
                colisoes++;
            }
            if (percorrerDiagonalDesceDireita(posicoesY[i], i)) {
                colisoes++;
            }
            if (percorrerDiagonalDesceEsquerda(posicoesY[i], i)) {
                colisoes++;
            }
        }
        return colisoes;
    }

    public boolean percorrerLadoEsquerdo(int posicaoColuna, int linha) {
        int valor = linha;
        while (valor > 0) {
            valor--;
            if (matrizTabuleiro[linha][posicaoColuna] == 1) {
                return true;
            }
        }
        return false;
    }

    public boolean percorrerLadoDireito(int posicaoColuna, int linha) {
        int valor = linha;
        while (valor < 7) {
            valor++;
            if (matrizTabuleiro[linha][posicaoColuna] == 1) {
                return true;
            }
        }
        return false;
    }

    public boolean percorrerCima(int posicaoColuna, int linha) {
        int valor = posicaoColuna;
        while (valor < 7) {
            valor++;
            if (matrizTabuleiro[linha][posicaoColuna] == 1) {
                return true;
            }
        }
        return false;
    }

    public boolean percorrerBaixo(int posicaoColuna, int linha) {
        int valor = posicaoColuna;
        while (valor > 0) {
            valor--;
            if (matrizTabuleiro[linha][posicaoColuna] == 1) {
                return true;
            }
        }
        return false;
    }

    public boolean percorrerDiagonalSobeDireita(int posicaoColuna, int linha) {
        int valor = posicaoColuna;
        int valor2 = linha;
        // cima sobe direita
        while (valor < 7 && valor2 < 0) {
            valor++;
            valor2--;
            if (matrizTabuleiro[linha][posicaoColuna] == 1) {
                return true;
            }
        }
        return false;
    }

    public boolean percorrerDiagonalSobeEsquerda(int posicaoColuna, int linha) {
        int valor = posicaoColuna;
        int valor2 = linha;
        // cima sobe direita
        while (valor > 0 && valor2 > 0) {
            valor--;
            valor2--;
            if (matrizTabuleiro[linha][posicaoColuna] == 1) {
                return true;
            }
        }
        return false;
    }

    public boolean percorrerDiagonalDesceDireita(int posicaoColuna, int linha) {
        int valor = posicaoColuna;
        int valor2 = linha;
        // cima sobe direita
        while (valor < 7 && valor2 < 7) {
            valor++;
            valor2++;
            if (matrizTabuleiro[linha][posicaoColuna] == 1) {
                return true;
            }
        }
        return false;
    }

    public boolean percorrerDiagonalDesceEsquerda(int posicaoColuna, int linha) {
        int valor = posicaoColuna;
        int valor2 = linha;
        while (valor > 0 && valor2 < 7) {
            valor--;
            valor2++;
            if (matrizTabuleiro[linha][posicaoColuna] == 1) {
                return true;
            }
        }
        return false;
    }

    public void unsetTable() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                matrizTabuleiro[i][j] = 0;
            }
        }
    }

    public void selectParents() {

    }

    public void cruzar() {

    }

    public void fazerMutacao() {

    }

    public void avaliarResultado() {

    }

    public void atualizarPopulacao() {

    }

    /**
     * @return the tamanhoPopulacao
     */
    public Integer getTamanhoPopulacao() {
        return tamanhoPopulacao;
    }

    /**
     * @param tamanhoPopulacao the tamanhoPopulacao to set
     */
    public void setTamanhoPopulacao(Integer tamanhoPopulacao) {
        this.tamanhoPopulacao = tamanhoPopulacao;
    }

    /**
     * @return the txMutacao
     */
    public Double getTxMutacao() {
        return txMutacao;
    }

    /**
     * @param txMutacao the txMutacao to set
     */
    public void setTxMutacao(Double txMutacao) {
        this.txMutacao = txMutacao;
    }

    /**
     * @return the txCruzamento
     */
    public Double getTxCruzamento() {
        return txCruzamento;
    }

    /**
     * @param txCruzamento the txCruzamento to set
     */
    public void setTxCruzamento(Double txCruzamento) {
        this.txCruzamento = txCruzamento;
    }

    /**
     * @return the txSubstituicao
     */
    public Double getTxSubstituicao() {
        return txSubstituicao;
    }

    /**
     * @param txSubstituicao the txSubstituicao to set
     */
    public void setTxSubstituicao(Double txSubstituicao) {
        this.txSubstituicao = txSubstituicao;
    }

    /**
     * @return the posicaoY
     */
    public int[][] getPosicaoY() {
        return posicaoY;
    }

    /**
     * @param posicaoY the posicaoY to set
     */
    public void setPosicaoY(int[][] posicaoY) {
        this.posicaoY = posicaoY;
    }

}
