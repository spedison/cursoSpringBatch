package br.com.spedison.primeirobach.tasklets;

import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@Configuration
@Log4j2
public class DescompactaArquivoBooksTaskletConfig {

    @StepScope
    @Bean("descompactaArquivoBooksTasklet")
    public Tasklet createTasklet(@Value("#{jobParameters['nomeArquivoLivro']}") String nomeArquivo) {

        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                File arquivoCsv = new File(nomeArquivo);
                File dir = Paths.get(arquivoCsv.toString()).getParent().toFile();
                File arquivoCsvZipado = new File(nomeArquivo + ".zip");

                if (!arquivoCsvZipado.exists()) {
                    log.error("Arquivo Zip não existe, vou executar novamente.");
                    return RepeatStatus.CONTINUABLE;
                }

                byte[] buffer = new byte[4096];
                int bytesLidos;
                var zip = new ZipFile(arquivoCsvZipado);
                try {
                    Enumeration e = zip.entries();
                    while (e.hasMoreElements()) {
                        ZipEntry entrada = (ZipEntry) e.nextElement();
                        File arquivo = new File(dir, entrada.getName());
                        if (entrada.getName().endsWith(".csv")) {
                            InputStream is = zip.getInputStream(entrada);
                            OutputStream os = new FileOutputStream(arquivo);
                            while ((bytesLidos = is.read(buffer)) > 0) {
                                os.write(buffer, 0, bytesLidos);
                            }
                            os.close();
                        }
                    }
                    zip.close();
                    log.info("Descompactação realizada com sucesso");
                    return RepeatStatus.FINISHED;
                } catch (IOException ioe) {
                    log.error("Erro ao descompactar aquivo " + nomeArquivo + ".zip - Vamos tentar novamente.");
                    log.error(ioe);
                    return RepeatStatus.CONTINUABLE;
                }
            }
        };
    }
}