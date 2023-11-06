package br.com.spedison.primeirobach.tasklets;

import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@Configuration
@Log4j2
public class ApagaArquivoBooksTaskletConfig {

    @StepScope
    @Bean("apagaArquivoBooksTasklet")
    public Tasklet createTasklet(@Value("#{jobParameters['nomeArquivoLivro']}") String nomeArquivo) {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

                Path arquivoProcessado = Paths.get(nomeArquivo);
                File dir = arquivoProcessado.getParent().toFile();

                dir.listFiles((File file) -> {
                    if (file.isFile() && file.toString().toLowerCase().endsWith(".csv")) {
                        log.info("Vou apagar o arquivo :: " + file.toString());
                        file.delete();
                    }
                    return true;
                });
                return RepeatStatus.FINISHED;
            }
        };
    }
}