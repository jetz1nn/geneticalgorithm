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
    private int numeroGeracoes;
    private int[] individuoVitorias;
    private int[] individuoSelecionado;
    private int tipoSelecao;
    private int[][] cruzamentos;
    private int tipoCruzamento;
    private int quantidadeMaxCruzamentos;
    private int[] individuoFitnessNovo;
    private int[][] novaPopulacao;
    private boolean achouSolucao;
    
    public Populacao(int tamanhoPopulacao, Double txMutacao, Double txCruzamento, Double txSubstituicao, Integer numeroGeracoes) {
        this.tamanhoPopulacao = tamanhoPopulacao;
        this.txMutacao = txMutacao;
        this.txCruzamento = txCruzamento;
        this.txSubstituicao = txSubstituicao;
        this.numeroGeracoes = numeroGeracoes;
        novaPopulacao = new int[tamanhoPopulacao][8];
        posicaoY = new int[tamanhoPopulacao][8];
        individuoFitness = new int[tamanhoPopulacao];
        this.individuoVitorias = new int[tamanhoPopulacao];
        this.individuoSelecionado = new int[(int) (tamanhoPopulacao * txCruzamento)];
        tipoSelecao = 0;
        tipoCruzamento = 0;
        int resultadoSum = 0;
        for (int i = 0; i < (int) (tamanhoPopulacao * txCruzamento); i++) {
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
        int individuo = 0;
        for (int i = 0; i < tamanhoPopulacao; i++) {
            if (bestFitness > individuoFitness[i]) {
                bestFitness = individuoFitness[i];
                individuo = i;
            }

            System.out.println("Indivíduo " + i + ":" + individuoFitness[i]);
            if (individuoFitness[i] == 0) {
                achouSolucao = true;
                printTable(individuo);
            }
        }

        System.out.println("Melhor Indivíduo: #" + individuo);
        printTable(individuo);
        numeroGeracoes++;
    }

    public void printTable(int individuo) {
        for (int i = 0; i < 8; i++) {
            matrizTabuleiro[i][posicaoY[individuo][i]] = 1;
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
//                System.out.print('|');
                if (matrizTabuleiro[i][j] == 1) {
                    System.out.print('X');
                } else {
                    System.out.print(' ');
                }
                System.out.print('|');
            }
            System.out.print("\n");
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
            if (percorrerBaixo(posicoesY[i], i)) {
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
            if (matrizTabuleiro[valor][posicaoColuna] == 1) {
                return true;
            }
        }
        return false;
    }

    public boolean percorrerLadoDireito(int posicaoColuna, int linha) {
        int valor = linha;
        while (valor < 7) {
            valor++;
            if (matrizTabuleiro[valor][posicaoColuna] == 1) {
                return true;
            }
        }
        return false;
    }

    public boolean percorrerCima(int posicaoColuna, int linha) {
        int valor = posicaoColuna;
        while (valor < 7) {
            valor++;
            if (matrizTabuleiro[linha][valor] == 1) {
                return true;
            }
        }
        return false;
    }

    public boolean percorrerBaixo(int posicaoColuna, int linha) {
        int valor = posicaoColuna;
        while (valor > 0) {
            valor--;
            if (matrizTabuleiro[linha][valor] == 1) {
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
            if (matrizTabuleiro[valor2][valor] == 1) {
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
            if (matrizTabuleiro[valor2][valor] == 1) {
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
            if (matrizTabuleiro[valor2][valor] == 1) {
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
            if (matrizTabuleiro[valor2][valor] == 1) {
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
                    for (int i = 0; i < individuoSelecionado.length - 1; i++) {
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
                if (resultado < txMutacao) {
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
                matrizTabuleiro[j][cruzamentos[i][j]] = 1;
                // guardado os valores os quais 
                posicoesY[j] = cruzamentos[i][j];
            }
            //fitness de cada indivíduo calculado
            individuoFitnessNovo[i] = checkTableFitness(posicoesY);
            unsetTable();
        }

    }

    public void atualizarPopulacao() {
        //pegar os melhores individuos provindos dos cruzamentos
        boolean darContinue = false;
        int[] melhoresIndividuos = new int[(int) (tamanhoPopulacao * txSubstituicao)];
        for (int i = 0; i < tamanhoPopulacao * txSubstituicao; i++) {
            melhoresIndividuos[i] = Integer.MAX_VALUE;
            for (int j = 0; j < quantidadeMaxCruzamentos; j++) {
                for (int k = 0; k < tamanhoPopulacao * txSubstituicao; k++) {
                    if (melhoresIndividuos[k] == j) {
                        darContinue = true;
                        break;
                    }

                }
                //para nao pegar os mesmo individuos
                if (darContinue) {
                    darContinue = false;
                    continue;
                }
                if (melhoresIndividuos[i] >= individuoFitnessNovo[j]) {
                    melhoresIndividuos[i] = j;
                }

            }
        }

        for (int i = 0; i < tamanhoPopulacao * txCruzamento; i++) {
            for (int j = 0; j < 8; j++) {
                novaPopulacao[i][j] = cruzamentos[melhoresIndividuos[i]][j];
                posicaoY[i][j] = novaPopulacao[i][j];
            }
        }
        int[] melhoresIndividuosGeracaoAnterior = new int[tamanhoPopulacao - (int) (tamanhoPopulacao * txCruzamento)];
        darContinue = false;
        switch (tipoSelecao) {
            case 1:
                for (int i = 0; i < (tamanhoPopulacao - (int) (tamanhoPopulacao * txCruzamento)); i++) {
                    melhoresIndividuosGeracaoAnterior[i] = Integer.MIN_VALUE;
                    for (int j = 0; j < tamanhoPopulacao; j++) {

                        for (int k = 0; k < (tamanhoPopulacao - (int) (tamanhoPopulacao * txCruzamento)); k++) {
                            if (melhoresIndividuosGeracaoAnterior[k] == j) {
                                darContinue = true;
                                break;
                            }

                        }
                        //para nao pegar os mesmo indivíduos
                        if (darContinue) {
                            darContinue = false;
                            continue;
                        }
                        //pega os individuos com o maior numero de vitorias
                        if (melhoresIndividuosGeracaoAnterior[i] <= individuoVitorias[j]) {
                            melhoresIndividuosGeracaoAnterior[i] = j;
                        }
                    }

                }
                for (int i = (int) (tamanhoPopulacao * txCruzamento); i < tamanhoPopulacao; i++) {
                    for (int j = 0; j < 8; j++) {
                        novaPopulacao[i][j] = cruzamentos[melhoresIndividuosGeracaoAnterior[i - ((int) (tamanhoPopulacao * txCruzamento))]][j];
                        posicaoY[i][j] = novaPopulacao[i][j];
                    }
                }
                break;
            case 2:
                break;

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
        int selecionados = 0;
//        while (selecionados < (int) (tamanhoPopulacao * txCruzamento)) {
//            for (int i = 0; i < tamanhoPopulacao * txCruzamento; i++) {
//                if () {
//                    individuoSelecionado[selecionados]
//                }
//            }
//        }
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
            selecionados++;
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

    /**
     * @return the achouSolucao
     */
    public boolean isAchouSolucao() {
        return achouSolucao;
    }

    /**
     * @param achouSolucao the achouSolucao to set
     */
    public void setAchouSolucao(boolean achouSolucao) {
        this.achouSolucao = achouSolucao;
    }

    /**
     * @return the numeroGeracoes
     */
    public int getNumeroGeracoes() {
        return numeroGeracoes;
    }
}
