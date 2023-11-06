package br.com.spedison.primeirobach.step;

import br.com.spedison.primeirobach.itens.writer.DadosLivroLogItemWriter;
import br.com.spedison.primeirobach.processor.ProcessorLivro;
import br.com.spedison.primeirobach.model.Livro;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Arrays;

@Configuration
@Log4j2
public class ProcessaArquivoLivrosStepConfig {

    @Autowired
    private StepBuilderFactory sbf;

    @Bean("processaLivrosStep")
    public Step step(
            @Qualifier("processaArquivoItemReaderBean") ItemReader<Livro> reader,
            @Qualifier("processaArquivoItemWriterBean") ItemWriter<Livro> writerDB,
            DadosLivroLogItemWriter writerLog,
            ProcessorLivro processorLivro,
            @Qualifier("appTransactionManager")   PlatformTransactionManager transactionManager) {

        var writers = new CompositeItemWriter<Livro>();
        writers.setDelegates(Arrays.asList(writerDB,writerLog));

        ItemWriter<Livro> writerUsado = writerDB;

        return sbf
                .get("processaArquivoLivroStep")
                .<Livro, Livro>chunk(10_000)
                .reader(reader)
                .processor(processorLivro)
                .writer(writerUsado)
                .throttleLimit(0)
                .transactionManager(transactionManager)
                .listener(new ChunkListener() {
                    static int contador = 0;

                    @Override
                    public void beforeChunk(ChunkContext context) {
                        log.info("Iniciando a gravação do Chunck " + (++contador));
                    }

                    @Override
                    public void afterChunk(ChunkContext context) {
                        log.info("Gravado o Chunck - " + contador);
                    }

                    @Override
                    public void afterChunkError(ChunkContext context) {
                        log.error("Problemas ao gravar o Chunck - " + contador);
                    }
                })
                .build();
    }

}
