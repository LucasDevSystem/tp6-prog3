import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import Arvore.Arvore;
import Arvore.No;

public class App {
    static Arvore arvore;

    public static void main(String[] args) throws Exception {
        arvore = new Arvore();
        popularArvore();

        System.out.println("");
        System.out.print("Arvore atual: ");
        arvore.imprimirEmOrdem();
        System.out.println("");

        int nos = arvore.contarNos();

        System.out.println("Nos na arvore:" + nos);

        int nosNaoFolha = arvore.contarNosNaoFolha();
        System.out.println("Nos nao folha na arvore: " + nosNaoFolha);

        int nosFolha = arvore.contarNosFolha();
        System.out.println("Nos nao folha na arvore:" + nosFolha);

        int alturaArvore = arvore.contadorAlturaArvore();

        System.out.println("Altura da arvore:" + alturaArvore);

        arvore.removerPares();
        System.out.print("Arvore sem pares: ");
        arvore.imprimirEmOrdem();
        arvore.espelharArvore();
        System.out.println("");
        System.out.print("Arvore sem espelhada: ");
        arvore.imprimirEmOrdem();
    }


    private static void popularArvore() {
        arvore.inserir(8);
        arvore.inserir(3);
        arvore.inserir(10);
        arvore.inserir(1);
        arvore.inserir(6);
        arvore.inserir(4);
        arvore.inserir(7);
        arvore.inserir(14);
        arvore.inserir(13);
        arvore.inserir(9);
        arvore.inserir(20);
    }

}
