package br.com.spedison;

import br.com.spedison.busca.Buscador;
import br.com.spedison.process.ProcessadorArquivo;
import br.com.spedison.regras.RegraLinhaVazia;
import br.com.spedison.regras.RegraQuantidadeDeTokens;
import br.com.spedison.regras.RegraValidaCampos;

import java.io.IOException;
import java.sql.SQLException;

public class Main2 {
    public static void main(String[] args) throws IOException,ClassNotFoundException, SQLException {

        Buscador b = new Buscador();
        b.processa(args[0].replaceAll("[.]csv$",""));
    }
}