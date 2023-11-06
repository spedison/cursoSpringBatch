package br.com.spedison.primeirobach.job;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.stream.IntStream;

@Configuration
public class BathParImparConfig {

    @Autowired
    JobBuilderFactory jbf;

    @Bean
    Job imprimeParImparJob(Step imprimeParImparStep) {
        return jbf
                .get("imprimeParImparJob")
                .start(imprimeParImparStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }




//    @StepScope
//    @Bean
//    public Tasklet imprimeOlaMundoTasklet(@Value("#{jobParameters['nome']}") String nome) {
//        return new Tasklet() {
//            @Override
//            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                System.out.println((new Date()).toString() + "  -  Olá " + nome);
//                return RepeatStatus.FINISHED;
//            }
//        };
//    }

}
