package br.com.spedison.primeirobach.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApagaArquivoBooksStepConfig {

    @Bean("apagaArquivoBooksStep")
    public Step criaStepDescompactaArquivoBooks(
            @Qualifier("apagaArquivoBooksTasklet") Tasklet apagaArquivoTasklet,
            StepBuilderFactory sbf) {
        return sbf
                .get("apagaArquivoBooksStep")
                .tasklet(apagaArquivoTasklet)
                .build();
    }
}
