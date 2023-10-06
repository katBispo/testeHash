package util;

public class FCHash {

    private int tamanho;

    public FCHash(int tamanho) {
        this.tamanho = tamanho;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public boolean ehPrimo(int n) {
        if (n <= 1) {
            return false;
        }
        if (n <= 3) {
            return true;
        }

        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }
        int i = 5;
        while (i * i <= n) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
            i += 6;
        }
        return true;
    }

    public int primoProximo() {
        int tamHash = getTamanho()*2;
        if (tamHash < 2) {
            return 2;
        }

        int abaixo = tamHash;
        int acima = tamHash;

        while (true) {
            abaixo--;
            acima++;
            if (ehPrimo(abaixo)) {
                return abaixo;
            }
            if (ehPrimo(acima)) {
                return acima;
            }
        }
    }
    public double fC(){
        return(getTamanho()/primoProximo());
    }
   
}
