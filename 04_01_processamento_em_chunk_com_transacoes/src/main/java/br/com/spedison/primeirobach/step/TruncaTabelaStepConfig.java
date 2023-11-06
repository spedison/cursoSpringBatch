package br.com.spedison.primeirobach.step;

import br.com.spedison.primeirobach.tasklets.TruncaTabelaTasklet;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TruncaTabelaStepConfig {
    @Autowired
    StepBuilderFactory sbf;

    @Bean("truncaTabelaStep")
    public Step criaStepTruncaTabela(TruncaTabelaTasklet truncaTabelaTasklet) {
        return sbf
                .get("truncaTabelaStep")
                .tasklet(truncaTabelaTasklet)
                .build();
    }
}
