package br.com.spedison.primeirobach.itens.writer;

import br.com.spedison.primeirobach.model.Livro;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

@Configuration
public class ConfigArquivoLivrosToFileItemWriter {

    @Bean("processaArquivoItemToFileWriterBean")
    @StepScope
    public ItemWriter<Livro> writer( ) {
        return new ItemWriter<Livro>() {
            @Override
            public void write(List<? extends Livro> items) throws Exception {
                StringBuffer out = new StringBuffer();
                items
                        .stream()
                        .map(l->"%s\n".formatted(l.getIsbn()))
                        .forEach(out::append);
                Files.write(Paths.get("/home/spedison/tmp.tmp"),out.toString().getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE_NEW);
            }
        };
    }


}
