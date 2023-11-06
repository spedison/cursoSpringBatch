package br.com.spedison.primeirobach.processor;

import br.com.spedison.primeirobach.model.Livro;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ProcessorLivro implements ItemProcessor<Livro, Livro> {
    @Override
    public Livro process(Livro item) throws Exception {
        item.setPublicador(item.getPublicador().toUpperCase());
        return item;
    }
}
