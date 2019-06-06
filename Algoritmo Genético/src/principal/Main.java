/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.util.Scanner;

/**
 *
 * @author a1819879
 */
public class Main {

    public static void main(String[] args) {
//        System.out.println("Al");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o tamanho da população :");
        int tamPopulacao = scanner.nextInt();
        System.out.println("Digite a taxa de cruzamento: ");
        Double txCruzamento = scanner.nextDouble();
        System.out.println("Digite a taxa de mutação: ");
        Double txMutacao = scanner.nextDouble();
        System.out.println("Digite a taxa da população a ser substituída: ");
        Double txSubstituicao = scanner.nextDouble();
        Populacao populacao = new Populacao(tamPopulacao, txCruzamento, txMutacao, txSubstituicao);
        System.out.println("Inicializando o Algoritmo Genético com uma popu"
                + "lação de " + populacao.getTamanhoPopulacao() + ""
                + " indivíduos \n");

    }
}
