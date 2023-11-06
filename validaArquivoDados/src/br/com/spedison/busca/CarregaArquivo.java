package br.com.spedison.busca;

import br.com.spedison.model.Livro;
import lombok.AllArgsConstructor;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class CarregaArquivo {
    private String nomeArquivo;

    public List<Livro> carregaISBNs() throws IOException {
        FileInputStream fi = new FileInputStream(nomeArquivo+".csv");
        InputStreamReader isr = new InputStreamReader(fi, "ISO-8859-1");
        BufferedReader br = new BufferedReader(isr);
        String linha;
        List<Livro> ret = new LinkedList<Livro>();
        while((linha = br.readLine()) != null){
            Livro l = new Livro();
            l.setIsbn(linha.split("[;]")[0].replaceAll("[\"]",""));
            ret.add(l);
        }
        return ret;
    }
}
