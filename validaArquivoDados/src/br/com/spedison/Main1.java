package br.com.spedison;

import br.com.spedison.process.ProcessadorArquivo;
import br.com.spedison.regras.RegraLinhaVazia;
import br.com.spedison.regras.RegraQuantidadeDeTokens;
import br.com.spedison.regras.RegraValidaCampos;

import java.io.IOException;

public class Main1 {
    public static void main(String[] args) throws IOException {

        ProcessadorArquivo p = new ProcessadorArquivo(args[0]);
        p.adicionaRegra(new RegraLinhaVazia());
        p.adicionaRegra(new RegraQuantidadeDeTokens());
        p.adicionaRegra(new RegraValidaCampos());
        p.processa();
        p.imprimeResultado();

    }
}