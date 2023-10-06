
package questao02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import util.FCHash;
import util.TabelaHash;


public class Main02 {
    public static void main(String[] args){
         String caminho = "./src/entrada/DadosQ2.txt";
        int tamanhoVet = 0;
        int array[];

        String linha, conteudo[];

        //int tamanhoDaTabela = new FCHash(tamanhoVet).primoProximo();

        //TabelaHash hash = new TabelaHash(tamanhoVet);
        try {
            FileReader leitorArquivo = new FileReader(caminho);
            BufferedReader buffer = new BufferedReader(leitorArquivo);

            while ((linha = buffer.readLine()) != null) {
                conteudo = linha.split(",");
                tamanhoVet = conteudo.length;
                array = new int[conteudo.length]; // Inicializa o array com o tamanho apropriado
                int tamanhoDaTabela = new FCHash(tamanhoVet).primoProximo();
                TabelaHash hash = new TabelaHash(tamanhoDaTabela);

                // Preenche o array com os elementos convertidos para inteiros
                for (int i = 0; i < conteudo.length; i++) {
                    array[i] = Integer.parseInt(conteudo[i]);
                    hash.inserir(array[i]);
                }
                hash.imprimir();
                hash.verificaColisoes();

            }

        } catch (IOException e) {
            System.out.println(e.getMessage());

        }
    }
}
