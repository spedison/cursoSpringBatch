package br.com.spedison.busca;

import br.com.spedison.model.Livro;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Buscador {
    public void processa(String nomeArquivo) throws IOException, SQLException, ClassNotFoundException {
        CarregaArquivo ca = new CarregaArquivo(nomeArquivo);
        List<Livro> livros = ca.carregaISBNs();
        try (ContadorRegistros cr = new ContadorRegistros()) {
            for (Livro l : livros) {
                if (!cr.achouID(l)) {
                    System.out.println("Não foi inserido o registro com ISBN [" + l.getIsbn() + "]");
                }
            }
        }
    }
}
