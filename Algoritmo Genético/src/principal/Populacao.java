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

    public Populacao(int tamanhoPopulacao, Double txMutacao, Double txCruzamento, Double txSubstituicao) {
        this.tamanhoPopulacao = tamanhoPopulacao;
        this.txMutacao = txMutacao;
        this.txCruzamento = txCruzamento;
        this.txSubstituicao = txSubstituicao;
        posicaoY = new int[tamanhoPopulacao][8];
    }
    //gerando a primeira populacao
    public void generatePopulation() {
        Random random = new Random();
        for (int i = 0; i < tamanhoPopulacao; i++) {
            for (int j = 0; j < 8; j++) {
                posicaoY[i][j] = random.nextInt(8);
            }
        }
    }
    //checar a qualidade dos meus indivíduos gerados
    public void checkFitness() {
        
    }
    
    public void unsetTable() {

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
