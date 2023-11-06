package br.com.spedison.primeirobach;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class BathConfig {

    @Autowired
    JobBuilderFactory jbf;

    @Autowired
    StepBuilderFactory sbf;

    @Bean
    Job imprimeOla() {
        return jbf
                .get("imprimeOlaMundo")
                .start(imprimeOlaStep())
                .build();
    }

    Step imprimeOlaStep() {
        return sbf
                .get("imprimeOlaMundoStep")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println((new Date()).toString() + "  -  Olá Mundo");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

}
