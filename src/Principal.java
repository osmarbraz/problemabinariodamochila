/*
 * Universidade Federal de Santa Catarina - UFSC
 * Departamento de Informática e Estatística - INE
 * Programa de Pós-Graduação em Ciências da Computação - PROPG
 * Disciplinas: Projeto e Análise de Algoritmos
 * Prof Alexandre Gonçalves da Silva 
 * Baseado nos slides 48 da aula 03/11/2017 
 *
 * Página 310 Cormen 3 ed
 */

/**
 *
 * @author Osmar de Oliveira Braz Junior
 */
public class Principal {

    //Matriz da solução
    static int z[][];

    /**
     * Mostra na tela tabela calculada.
     *  
     * @param c Vetor com valor de cada item
     * @param w Vetor com peso de cada item
     * @param W Capacidade da mochila
     * @param n Quantidade de itens
     */
    public static void imprimirTabela(int[] c, int[] w, int W, int n) {      
        
        System.out.println("Tabela Calculada do Problema da Mochila Binária ");
        System.out.printf("k\\d \t");
        for ( int d = 0; d <= W; d++ ) {            
            System.out.printf(" %d \t", d);             
        }               
        System.out.println(); //Pula a linha
        for (int k = 0; k <= n; k++) {
            System.out.printf("%d \t",k);            
            for (int j = 0; j <= W; j++) {                                                 
                System.out.printf(" %d \t", z[k][j]);                                
            }
            System.out.println();
        }        
    } 
    
    /**
     * Método auxiliar da solução do problema da mochila.
     * 
     * @param x Vetor de resposta do problema
     * @param z Matriz do resultado
     * @param w  Vetor com peso de cada item
     * @param k Quantidade de itens
     * @param d Capacidade da mochila
     */
    public static void mochilaSolucaoAux(int[] x, int[][] z, int[] w, int k, int d){
        if(k!=0){
            if (z[k][d] == z[k-1][d]){
                x[k-1] = 0;
                mochilaSolucaoAux(x,z,w,k-1,d);
            } else {
                x[k-1] = 1;
                mochilaSolucaoAux(x,z,w,k-1,d-w[k-1]);
            }
        }        
    }    
    
    /**
     * Recupera a solução do problema da mochila binária
     * @param z Matriz do resultado
     * @param w Vetor com peso de cada item
     * @param n Quantidade de itens
     * @param W Capacidade da mochila
     * @return Vetor indicando os itens da mochila
     */
    public static int[] mochilaSolucao(int[][] z, int[] w, int n, int W){
        //Cria o vetor da resposta
        int[] x = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = 0;
            mochilaSolucaoAux(x,z,w,n,W);
        }
        return x;
    }
    
    /**
     * Problema Binário da mochila.
     * 
     * @param c Vetor com valor de cada item
     * @param w Vetor com peso de cada item
     * @param W Capacidade da mochila
     * @param n Quantidade de itens
     */    
    public static void mochila(int[] c, int[] w, int W, int n) {
        //Inicializa a matriz do resultado
        z = new int[n + 1][W + 1];
        for (int d = 0; d <= W; d++) {
            z[0][d] = 0;
        }
        for (int k = 1; k <= n; k++) {
            z[k][0] = 0;
        }
        for (int k = 1; k <= n; k++) {
            for (int d = 1; d <= W; d++) {
                z[k][d] = z[k - 1][d];
                if ((w[k-1] <= d) && ((c[k-1] + (z[k - 1][d - w[k-1]])) > z[k][d])) {
                    z[k][d] = c[k-1] + (z[k - 1][d - w[k-1]]);
                }
            }
        }
    }

    public static void main(String[] args) {
        //Vetor com valor de cada item
        int[] c = {10, 7, 25, 24};
        //Vetor de tamanho de cada item
        int[] w = {2, 1, 6, 5};
        //Capacidade da mochila
        int W = 7;

        //Quantidade de items
        int n = w.length;

        //Executa o algoritmo
        mochila(c, w, W, n);
        
        //Mostra a tabela
        imprimirTabela(c, w, W, n);
        
        //Recupera a solução
        int[] x = mochilaSolucao(z, w, n, W);
        
        System.out.println("Solução:");
        for(int i=0;i<x.length;i++){
            System.out.println("x["+(i+1)+"]="+x[i]);
        }
        System.out.println("\n Itens a serem inseridos:");
        int total = 0;
        for(int i=0;i<x.length;i++){
            if (x[i]==1) {
                System.out.println("w["+(i+1)+"]="+w[i]);
                total = total + c[i];
            }
        }
        System.out.println("Total:"+total);
    }
}