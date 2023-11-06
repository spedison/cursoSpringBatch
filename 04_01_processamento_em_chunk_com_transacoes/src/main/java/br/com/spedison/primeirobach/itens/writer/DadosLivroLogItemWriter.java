package br.com.spedison.primeirobach.itens.writer;

import br.com.spedison.primeirobach.model.Livro;
import lombok.extern.java.Log;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Log
public class DadosLivroLogItemWriter implements ItemWriter<Livro> {

    @Override
    public void write(List<? extends Livro> items) throws Exception {
        items
                .stream()
                .map(s -> s.toString())
                .forEach(log::info);
    }
}
