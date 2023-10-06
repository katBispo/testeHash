package util;

public class TabelaHash {

    private int operador;
    private ArvoreBinaria arvoreVetor[];

    FCHash fc = new FCHash(operador);

    public TabelaHash(int tamanho) {//construtor
        this.operador = tamanho;
        arvoreVetor = new ArvoreBinaria[tamanho];
        for (int i = 0; i < tamanho; i++) {
            arvoreVetor[i] = new ArvoreBinaria();
        }
    }

    public int getOperador() {
        return operador;
    }

    public void setOperador(int operador) {
        this.operador = operador;
    }

    public ArvoreBinaria[] getArvoreVetor() {
        return arvoreVetor;
    }

    public void setArvoreVetor(ArvoreBinaria[] arvoreVetor) {
        this.arvoreVetor = arvoreVetor;
    }

    public int hashChave(int chave) {
        return chave % this.operador;
    }

    ArvoreBinaria arvore = new ArvoreBinaria();

    public void inserir(int i, int elemento) {
        int chave = hashChave(i);
        if (this.arvoreVetor[chave] == null) {
            this.arvoreVetor[chave] = new ArvoreBinaria();
            this.arvoreVetor[chave].inserirRec(chave, elemento);
        } else {
            this.arvoreVetor[chave].inserirRec(chave, elemento);

        }
    }

    public void inserirTeste(int i, Object elemento) {

    }

    public void imprimir() {
        for (int i = 0; i < arvoreVetor.length; i++) {
            System.out.print("[" + i + "] : ");
            if (arvoreVetor[i].getRaiz() != null) {
                arvoreVetor[i].imprimirRec();
            } else {
                System.out.print("null");
            }
            System.out.println();
        }
    }

    public void verificaColisoes() {
        for (int i = 0; i < arvoreVetor.length; i++) {
            if (arvoreVetor[i].contaElementos() > 1) {
                if (verificaElementosIguais(arvoreVetor[i])) {
                    System.out.println("O elemento " + arvoreVetor[i].getRaiz().getElemento() + " esta duplicado");
                }
            }
        }
    }

    private boolean verificaElementosIguais(ArvoreBinaria arvore) {
        return verificaElementosIguaisRec(arvore.getRaiz(), arvore.getRaiz().getElemento());
    }

    private boolean verificaElementosIguaisRec(NodoArvore raiz, Object valor) {
        if (raiz == null) {
            return true;
        }
        if (!raiz.getElemento().equals(valor)) {
            return false;
        }
        return verificaElementosIguaisRec(raiz.getEsquerda(), valor) && verificaElementosIguaisRec(raiz.getDireita(), valor);
    }

    public Object buscar(int chave) {
        int posicao = hashChave(chave);
        NodoArvore nodo = (NodoArvore) this.arvoreVetor[posicao].obterNoPosicao(1);

        while (nodo != null) {
            if (nodo.getChave() == chave) {
                return nodo.getElemento();
            }
            nodo = (NodoArvore) nodo.getProximo();
        }
        return null;
    }

}
