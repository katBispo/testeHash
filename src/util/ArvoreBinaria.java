package util;

import util.NodoArvore;

public class ArvoreBinaria {

    private NodoArvore raiz;

    public NodoArvore getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoArvore raiz) {
        this.raiz = raiz;
    }

    public void inicializaArvore() {
        setRaiz(null);
    }

    private int compararObjetos(Object elemento1, Object elemento2) {
        if (elemento1.toString().compareTo(elemento2.toString()) < 0) {
            return -1;
        } else if (elemento1.toString().compareTo(elemento2.toString()) > 0) {
            return 1;
        } else {
            return 0;//se forem iguais
        }
    }

    public NodoArvore inserir(NodoArvore raizInsercao, Object elemento) {
        if (raizInsercao == null) {
            raizInsercao = new NodoArvore(elemento);
            return raizInsercao;
        }
        if (compararObjetos(elemento, raizInsercao.getElemento()) < 0) {
            raizInsercao.setEsquerda(inserir(raizInsercao.getEsquerda(), elemento));
        } else if (compararObjetos(elemento, raizInsercao.getElemento()) > 0) {
            raizInsercao.setDireita(inserir(raizInsercao.getDireita(), elemento));
        } else {
            raizInsercao.setEsquerda(inserir(raizInsercao.getEsquerda(), elemento));
        }
        return raizInsercao;
    }

    public void inserirRec(Object elemento, int chave) {
        this.raiz = inserir(this.raiz, elemento);
    }

    private NodoArvore encontrarUltimoFilhoDireita(NodoArvore raiz) {//função que vai encontrar o nodo substituto da raiz
        while (raiz.getEsquerda() != null) {
            raiz = raiz.getEsquerda();
        }
        return raiz;//vai retornar o ultimo nó a esquerda do nodo imediatamente a direita da raiz da arvore
    }

    private NodoArvore encontrarUltimoFilhoEsquerda(NodoArvore raiz) {//função que vai encontrar o nodo substituto da raiz
        while (raiz.getDireita() != null) {
            raiz = raiz.getDireita();
        }
        return raiz;//vai retornar o ultimo nó a direita do nodo imediatamente a esquerda da raiz da arvore
    }

    public NodoArvore remover(NodoArvore raiz, Object elementoRemover) {
        if (raiz == null) {
            System.out.println("Elemento não encontrado");
            return null;
        }
        int comparacao = compararObjetos(raiz.getElemento(), elementoRemover);

        //procurando as raizes dos elementos que serao removidos
        if (comparacao > 0) {
            raiz.setEsquerda(remover(raiz.getEsquerda(), elementoRemover));//procurar elemento na esquerrda
        } else if (comparacao < 0) {
            raiz.setDireita(remover(raiz.getEsquerda(), elementoRemover));//procurar elemento esquerda
        } else {//elemento buscado == elemento presente na arvore

            if ((raiz.getDireita() == null) && (raiz.getEsquerda() == null)) {//NÓ FOLHA(raiz sem nenhum filho)
                return null;//removendo nó desejado
            }
            if (raiz.getDireita() == null) {//raiz(no) sem filho na direita
                return raiz.getEsquerda();//retornar filho da esquerda para substituir a raiz
            }
            if (raiz.getEsquerda() == null) {
                return raiz.getDireita();
            }
            if (raiz.getEsquerda() != null) {
                NodoArvore ultimoFilho = encontrarUltimoFilhoEsquerda(raiz.getEsquerda());
                raiz.setElemento(ultimoFilho.getElemento());
                raiz.setEsquerda(remover(raiz.getEsquerda(), ultimoFilho.getElemento()));
            } else {
                NodoArvore ultimoFilho = encontrarUltimoFilhoDireita(raiz.getDireita());
                raiz.setElemento(ultimoFilho.getElemento());
                raiz.setDireita(remover(raiz.getDireita(), ultimoFilho.getElemento()));
            }
        }
        return raiz;
    }

    public void removerRec(Object elemento, int chave) {
        remover(this.raiz, elemento);
    }

    public NodoArvore buscaBinaria(NodoArvore raizBuscada, Object elemento) {
        if (raizBuscada == null) {
            System.out.println("Elemento " + elemento + " NÃO encontrado");
            return null;
        }

        int comparacao = compararObjetos(elemento, raizBuscada.getElemento());

        if (comparacao == 0) { // Elemento encontrado
            System.out.println("Elemento " + elemento + " encontrado");
            return raizBuscada;
        } else if (comparacao < 0) { // Elemento menor que o elemento da raiz
            // System.out.println("Elemento " + elemento + " encontrado");
            return buscaBinaria(raizBuscada.getEsquerda(), elemento); // Procurar elemento em nós esquerdos
        } else { // Elemento maior que o elemento da raiz
            // System.out.println("Elemento " + elemento + " encontrado");
            return buscaBinaria(raizBuscada.getDireita(), elemento); // Procurar elemento em nós direitos
        }
    }

    public NodoArvore obterNoPosicao(int posicao) {
        int[] contador = {0}; 
        return obterNoPosicao(raiz, posicao, contador);
    }

    private NodoArvore obterNoPosicao(NodoArvore nodo, int posicao, int[] contador) {
        if (nodo == null) {
            return null;
        }
        NodoArvore esquerda = obterNoPosicao(nodo.getEsquerda(), posicao, contador);

        if (contador[0] == posicao) {
            return nodo;
        }
        contador[0]++;

        NodoArvore direita = obterNoPosicao(nodo.getDireita(), posicao, contador);

        if (esquerda != null) {
            return esquerda;
        }
        return direita;
    }

    public Object buscarElemento(Object elemento, int chave) {
        NodoArvore aux = buscaBinaria(this.raiz, elemento);
        if (aux != null) {
            System.out.println("Elemento " + elemento + " encontrado");
            return aux.getElemento();
        } else {
            System.out.println("Elemento não encontrado");
            return null;
        }
    }

    public int contaElementos() {
        return contaElementosRec(this.raiz);
    }

    private int contaElementosRec(NodoArvore raiz) {
        if (raiz == null) {
            return 0;
        }
        return 1 + contaElementosRec(raiz.getEsquerda()) + contaElementosRec(raiz.getDireita());
    }

    public void imprimirEmOrdem(NodoArvore raiz) {
        if (raiz != null) {
            imprimirEmOrdem(raiz.getEsquerda()); // Recursivamente imprimir subárvore esquerda
            System.out.print(raiz.getElemento() + " "); // Imprimir elemento atual
            imprimirEmOrdem(raiz.getDireita()); // Recursivamente imprimir subárvore direita
        }
    }

    public void imprimirRec() {
        imprimirEmOrdem(this.raiz);
    }

}
