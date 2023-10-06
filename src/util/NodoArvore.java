package util;

public class NodoArvore {

    private Object elemento;
    private NodoArvore esquerda;
    private NodoArvore direita;

    public NodoArvore(Object elemento) {
        this.elemento = elemento;
        this.esquerda = null;
        this.direita = null;
    }

    public Object getElemento() {
        return elemento;
    }

    public void setElemento(Object elemento) {
        this.elemento = elemento;
    }

    public NodoArvore getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(NodoArvore esquerda) {
        this.esquerda = esquerda;
    }

    public NodoArvore getDireita() {
        return direita;
    }

    public void setDireita(NodoArvore direita) {
        this.direita = direita;
    }
    
    
}
