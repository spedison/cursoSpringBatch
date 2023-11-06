package br.com.spedison.primeirobach.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobExecutaTudoConfig {

    @Autowired
    JobBuilderFactory jbf;

    @Bean("rodaTudo")
    public Job criaTodosOsSteps(
            @Qualifier("descompactaArquivoBooksStep") Step descompactaArquivoBooksTasklet,
            @Qualifier("truncaTabelaStep") Step truncaTabelaStep,
            @Qualifier("parImparStep") Step imprimeParImparStep,
            @Qualifier("processaLivrosStep") Step processaArquivos,
            @Qualifier("apagaArquivoBooksStep") Step apagaArquivos) {
        return jbf
                .get("rodaTudo")
                .start(descompactaArquivoBooksTasklet)
                .next(truncaTabelaStep)
                .next(imprimeParImparStep)
                .next(processaArquivos)
                .next(apagaArquivos)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
