/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

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
    public int[] individuoFitness;
    private Integer numeroGeracoes;
    private Integer[] individuoVitorias;
    private Integer[] individuoSelecionado;
    private int tipoSelecao;
    private int[][] cruzamentos;
    private int tipoCruzamento;
    private int quantidadeMaxCruzamentos;
    private int[] individuoFitnessNovo;

    public Populacao(int tamanhoPopulacao, Double txMutacao, Double txCruzamento, Double txSubstituicao, Integer numeroGeracoes) {
        this.tamanhoPopulacao = tamanhoPopulacao;
        this.txMutacao = txMutacao;
        this.txCruzamento = txCruzamento;
        this.txSubstituicao = txSubstituicao;
        this.numeroGeracoes = numeroGeracoes;
        posicaoY = new int[tamanhoPopulacao][8];
        individuoFitness = new int[tamanhoPopulacao];
        individuoVitorias = new Integer[tamanhoPopulacao];

        tipoSelecao = 0;
        tipoCruzamento = 0;
        int resultadoSum = 0;

        for (int i = 1; i < (int) ((tamanhoPopulacao - 1) * txCruzamento); i++) {
            resultadoSum = resultadoSum + (i * 2);
        }
        quantidadeMaxCruzamentos = resultadoSum;
        cruzamentos = new int[quantidadeMaxCruzamentos][8];
        individuoFitnessNovo = new int[quantidadeMaxCruzamentos];
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
            unsetTable();
        }
    }

    public void showGeneration() {

        int bestFitness = Integer.MAX_VALUE;

        for (int i = 0; i < tamanhoPopulacao; i++) {

            if (individuoFitness[i] < bestFitness) {
                bestFitness = i;
            }

            System.out.println("Indivíduo " + i + ":" + individuoFitness[i]);
        }

        System.out.println("Melhor Indivíduo: #" + bestFitness);

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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Metodo de seleção de pais: ");
        System.out.println("1. Torneio");
        System.out.println("2. Roleta");
        while (tipoSelecao == 0) {
            tipoSelecao = scanner.nextInt();

            switch (tipoSelecao) {

                case 1:
                    tournament();
                    break;
                case 2:
                    roulette();
                    break;
                default:
                    break;
            }
        }
    }

    public void cruzar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o tipo de cruzamento: ");
        System.out.println("1. Crossover Uniforme");
        System.out.println("2. Crossover 1 ponto");

        while (tipoCruzamento != 1 && tipoCruzamento != 2) {
            tipoCruzamento = scanner.nextInt();
            switch (tipoCruzamento) {
                case 1:
                    int cruzamento = 0;
                    for (int i = 0; i < individuoSelecionado.length; i++) {
                        uniformCrossover(individuoSelecionado[i], individuoSelecionado[i + 1], cruzamento);
                        cruzamento = cruzamento + 2;
                    }
                    fazerMutacao(cruzamentos);
                    break;
                case 2:
                    crossOver();
                    break;
            }
        }
    }

    public void fazerMutacao(int[][] cruzamentos) {
//        int quantidadeElementosMutacao = (int) (quantidadeMaxCruzamentos * txMutacao);
//        int[] elementosEscolhidos = new int[quantidadeElementosMutacao];
//        int numero;
//        int quantidadeEscolhas = 0;
//        boolean increment = false;
        Random random = new Random();
        double resultado;

        for (int i = 0; i < quantidadeMaxCruzamentos; i++) {
            for (int j = 0; j < 8; j++) {
                resultado = (double) random.nextInt() / 100;
                if (resultado < txCruzamento) {
                    cruzamentos[i][j] = random.nextInt(8);
                }
            }
//
//        for (int i = 0; i < quantidadeElementosMutacao; i++) {
//            numero = random.nextInt();
//            for (int j = 0; j < quantidadeElementosMutacao; j++) {
//                if (elementosEscolhidos[j] == numero) {
//                    increment = false;
//                    i--;
//                    break;
//                }
//            }
//            if (increment) {
//                elementosEscolhidos[i] = i;
//                quantidadeEscolhas++;
//                increment = false;
//            }
//        }
//        for (int i = 0; i < quantidadeElementosMutacao; i++) {
//            for (int j = 0; j < 8; j++) {
//                
//            }
//        }

        }
        avaliarResultado();
    }

    public void avaliarResultado() {
        unsetTable();
        int[] posicoesY = new int[8];
        for (int i = 0; i < quantidadeMaxCruzamentos; i++) {
            for (int j = 0; j < 8; j++) {
                // popula o tabuleiro com os valores randomizados
                matrizTabuleiro[j][posicaoY[i][j]] = 1;
                // guardado os valores os quais 
                posicoesY[j] = cruzamentos[i][j];
            }
            //fitness de cada indivíduo calculado
            individuoFitnessNovo[i] = checkTableFitness(posicoesY);
            unsetTable();
        }
    }
    
    public void atualizarPopulacao() {
//        Arrays.sort(individuoFitness);
        
        for (int i = 0; i < tamanhoPopulacao; i++) {
            
            for (int j = 0; j < 8; j++) {
                
            }
        }
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

    public void tournament() {
        for (int i = 0; i < tamanhoPopulacao; i++) {
            for (int j = i + 1; j < tamanhoPopulacao; j++) {
                if (individuoFitness[i] < individuoFitness[j]) {
                    individuoVitorias[i]++;
                } else {
                    individuoVitorias[j]++;
                }
            }
        }
    }

    public void roulette() {
        int soma = 0;
        int i;
        int quantidadeSelecionados = (int) (tamanhoPopulacao * txCruzamento);
        for (i = 0; i < tamanhoPopulacao; i++) {
            soma = soma + individuoFitness[i];
        }
        int selecionados = 0;
        i = 1;
        Random random = new Random();
        int s;
        int aux;
        while (selecionados < quantidadeSelecionados) {
            s = random.nextInt(soma);
            aux = individuoFitness[0];
            while (aux < s) {
                aux = aux + individuoFitness[i];
                i++;
            }
            individuoSelecionado[selecionados] = i;
            i = 1;
        }
    }

    public void uniformCrossover(int pai, int pai2, int cruzamento) {
        Random random = new Random(2);
        int resultado;

        //filho 1
        for (int i = 0; i < 8; i++) {
            resultado = random.nextInt();
            if (resultado == 0) {
                cruzamentos[cruzamento][i] = posicaoY[individuoSelecionado[pai]][i];
            } else {
                cruzamentos[cruzamento][i] = posicaoY[individuoSelecionado[pai2]][i];
            }
        }
        //filho 2
        for (int i = 0; i < 8; i++) {
            resultado = random.nextInt();
            if (resultado == 0) {
                cruzamentos[cruzamento + 1][i] = posicaoY[individuoSelecionado[pai]][i];
            } else {
                cruzamentos[cruzamento + 1][i] = posicaoY[individuoSelecionado[pai2]][i];
            }
        }
    }

    public void crossOver() {

    }
}
