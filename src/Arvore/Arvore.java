package Arvore;
import java.util.ArrayList;
import java.util.List;

public class Arvore {
  No raiz;

  public Arvore() {
    this.raiz = null;
  }

  private No inserirNovo(int valor, No raiz) {
    if (raiz == null) {
      raiz = new No(valor);
      return raiz;
    }

    if (valor < raiz.valor)
      raiz.esquerda = inserirNovo(valor, raiz.esquerda);
    else if (valor > raiz.valor)
      raiz.direita = inserirNovo(valor, raiz.direita);

    return raiz;
  }

  private No inserirNovoReverso(int valor, No raiz) {
    if (raiz == null) {
      raiz = new No(valor);
      return raiz;
    }

    if (valor > raiz.valor)
      raiz.esquerda = inserirNovo(valor, raiz.esquerda);
    else if (valor < raiz.valor)
      raiz.direita = inserirNovo(valor, raiz.direita);

    return raiz;
  }

  public void inserirReverso(int valor) {
    raiz = inserirNovoReverso(valor, raiz);
  }

  public void inserir(int valor) {
    raiz = inserirNovo(valor, raiz);
  }

  public int contarNos() {
    int nos = 0;

    nos = contadorRecursivo(raiz);

    return nos;
  }

  public int contadorRecursivo(No no) {
    if (no == null)
      return 0;

    return 1 + contadorRecursivo(no.direita) + contadorRecursivo(no.esquerda);
  }

  public int contarNosNaoFolha() {
    int nos = 0;

    nos = contadorNosNaoFolhaRecursivo(raiz);

    return nos;
  }

  public int contadorNosNaoFolhaRecursivo(No no) {
    int val = 0;
    if (no == null)
      return 0;
    // verifica se nao e um no folha
    if (no.direita != null || no.esquerda != null) {
      val = 1;
    }

    return val + contadorNosNaoFolhaRecursivo(no.direita) + contadorNosNaoFolhaRecursivo(no.esquerda);
  }

  public int contarNosFolha() {
    int nos = 0;

    nos = contadorNosFolhaRecursivo(raiz);

    return nos;
  }

  public int contadorNosFolhaRecursivo(No no) {
    int val = 0;
    if (no == null)
      return 0;
    // verifica se e um no folha
    if (no.direita == null || no.esquerda == null) {
      val = 1;
    }

    return val + contadorNosNaoFolhaRecursivo(no.direita) +
        contadorNosNaoFolhaRecursivo(no.esquerda);
  }

  public int contadorAlturaArvore() {
    int val = 0;

    val = contadorAlturaArvoreRecursivo(raiz, 0);

    return val;

  }

  // int maiorSalto = 0;

  // desce ate os nos folhas e volta ate a raiz contanndo
  // recursao junto com backtrack
  public int contadorAlturaArvoreRecursivo(No no, int saltos) {
    if (no == null) {
      return saltos - 1;
    }

    if (no.direita == null && no.esquerda == null) {
      return saltos;
    }

    // Incrementa os saltos e faz chamadas recursivas para direita e esquerda
    int alturaDireita = contadorAlturaArvoreRecursivo(no.direita, saltos + 1);
    int alturaEsquerda = contadorAlturaArvoreRecursivo(no.esquerda, saltos + 1);

    // Retorna o maior valor entre as alturas das subárvores
    return alturaDireita >= alturaEsquerda ? alturaDireita : alturaEsquerda;
  }

  public void removerPares() {
    removerParesRecursivo(raiz);
  }

  public List<Integer> listarNos() {
    List<Integer> valores = new ArrayList<>();
    listarNosRecursivo(raiz, valores);
    return valores;
  }

  private void listarNosRecursivo(No no, List<Integer> valores) {
    if (no != null) {
      listarNosRecursivo(no.esquerda, valores);
      valores.add(no.valor);
      listarNosRecursivo(no.direita, valores);
    }
  }

  public void removerEReestruturar(int valor) {
    List<Integer> valores = listarNos();

    valores.remove((Integer) valor); 
    raiz = null; 
    for (int val : valores) {
      this.inserir(val);
    }
  }

  public No removerParesRecursivo(No no) {
    if (no == null) {
      return null;
    }

    // Remove nós filhos primeiro, para garantir que a subárvore seja tratada
    // corretamente
    no.esquerda = removerParesRecursivo(no.esquerda);
    no.direita = removerParesRecursivo(no.direita);

    // Verifica se o valor do nó atual é par
    if (no.valor % 2 == 0) {
      removerEReestruturar(no.valor);
    }

    return no; // Retorna o nó atualizado
  }

  private No maiorValor(No no) {
    while (no.direita != null) {
      no = no.direita;
    }

    return no;
  }

  // Função auxiliar para encontrar o menor valor na subárvore direita
  private No encontrarMinimo(No no) {
    while (no.esquerda != null) {
      no = no.esquerda;
    }
    return no;
  }

  public void imprimirEmOrdem(No no) {
    if (no == null) {
      return;
    }

    imprimirEmOrdem(no.esquerda);
    System.out.print(no.valor + " ");
    imprimirEmOrdem(no.direita);
  }

  public void espelharArvore() {
    List<Integer> valores = listarNos();
    // Espelha a lista
    List<Integer> valoresEspelhados = new ArrayList<>();
    for (int i = valores.size() - 1; i >= 0; i--) {
      valoresEspelhados.add(valores.get(i));
    }

    // 3. Reconstruir a árvore a partir da lista espelhada
    raiz = null; // Limpa a árvore atual
    for (int val : valoresEspelhados) {
      this.inserirReverso(val); // Adiciona os valores de volta à árvore
    }
  }

  // Método recursivo que troca os filhos esquerdo e direito de cada nó
  private void espelharRecursivo(No no) {
    if (no == null) {
      return;
    }

    // Troca as referências dos filhos esquerdo e direito do nó atual
    No temp = no.esquerda;
    no.esquerda = no.direita;
    no.direita = temp;

    // Chama recursivamente para espelhar os filhos
    espelharRecursivo(no.esquerda);
    espelharRecursivo(no.direita);
  }

  // public void imprimirEmOrdem(No no) {
  // if (no == null) {
  // return;
  // }

  // // Visita o filho esquerdo
  // imprimirEmOrdem(no.esquerda);

  // // Imprime o valor do nó atual
  // System.out.print(no.valor + " ");

  // // Visita o filho direito
  // imprimirEmOrdem(no.direita);
  // }

  public void imprimirEmOrdem() {
    imprimirEmOrdemRecursivo(raiz);
  }

  public void imprimirEmOrdemRecursivo(No no) {
    if (no == null) {
      return;
    }

    // Visita o filho esquerdo
    imprimirEmOrdemRecursivo(no.esquerda);

    // Imprime o valor do nó atual
    System.out.print(no.valor + " ");

    // Visita o filho direito
    imprimirEmOrdemRecursivo(no.direita);
  }

  // Método auxiliar para encontrar o valor mínimo na subárvore
  private int valorMinimo(No no) {
    int minValor = no.valor;
    while (no.esquerda != null) {
      no = no.esquerda;
      minValor = no.valor;
    }
    return minValor;
  }

  private void PreOrdem(No elemento) {
    if (elemento != null) {
      // System.out.print(elemento.valor + " ");
      PreOrdem(elemento.esquerda);
      PreOrdem(elemento.direita);
    }
  }

  private void EmOrdem(No elemento) {
    if (elemento != null) {
      EmOrdem(elemento.esquerda);
      System.out.print(elemento.palavra + " ");
      for (int i = 0; i < elemento.linhas.size(); i++) {
        System.out.print(elemento.linhas.get(i) + " ");
      }
      System.out.println("");
      EmOrdem(elemento.direita);
    }
  }

  public void EmOrdem() {
    EmOrdem(raiz);
    System.out.println();
  }

}
