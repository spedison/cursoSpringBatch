package br.com.spedison.primeirobach.itens.reader;

import br.com.spedison.primeirobach.model.Livro;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

/***
 * Cria um ItemReader que lê arquivos CSVs.
 * O que teremos será processado a partir do /dados/books.csv
 */
@Configuration
public class ConfigArquivoLivrosItemReader {

    @Bean("processaArquivoItemReaderBean")
    @StepScope
    public ItemReader<Livro> reader(
            @Value("#{jobParameters['nomeArquivoLivro']}")
            String nomeArquivo) {
        var ret = new FlatFileItemReaderBuilder<Livro>()
                .name("processaArquivoItemReader")
                .resource(new FileSystemResource(nomeArquivo))
                .linesToSkip(1)
                //.maxItemCount(15)
                //.comments("--")
                .encoding("ISO-8859-15")
                .delimited()
                .delimiter(";")
                .quoteCharacter('"')
                .names("isbn", "titulo", "autor", "anoPublicacao", "publicador", "urlImagemS", "urlImagemM", "urlImagemL")
                .targetType(Livro.class)
                .build();
        ret.open(new ExecutionContext());
        return ret;
    }

}
