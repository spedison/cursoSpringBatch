package br.com.spedison.primeirobach.step;

import br.com.spedison.primeirobach.tasklets.DescompactaArquivoBooksTaskletConfig;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DescompactaArquivoBooksStepConfig {

    @Autowired
    StepBuilderFactory sbf;

    @Bean("descompactaArquivoBooksStep")
    public Step criaStepDescompactaArquivoBooks(
            @Qualifier("descompactaArquivoBooksTasklet") Tasklet descompacataTasklet) {
        return sbf
                .get("descompactaArquivoBooksStep")
                .tasklet(descompacataTasklet)
                .build();
    }
}
